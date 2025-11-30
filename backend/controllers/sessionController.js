const pool = require('../config/db');

exports.createSession = async (req, res) => {
    try {
        const userId = req.user.id;  // dari JWT
        const { sessionType, counselorId } = req.body; // 'human' atau 'ai'

        if (!sessionType || !['human', 'ai'].includes(sessionType)) {
            return res.status(400).json({ success: false, message: 'Invalid sessionType' });
        }

        let counselor_id_value = null;
        if (sessionType === 'human') {
            if (!counselorId) {
                return res.status(400).json({ success: false, message: 'counselorId required for human session' });
            }
            counselor_id_value = counselorId;
        }

        const [result] = await pool.query(
            'INSERT INTO counseling_sessions (user_id, counselor_id, session_type) VALUES (?, ?, ?)',
            [userId, counselor_id_value, sessionType]
        );

        return res.status(201).json({
            success: true,
            message: 'Session created',
            sessionId: result.insertId
        });

    } catch (err) {
        console.error('Create session error:', err);
        return res.status(500).json({ success: false, message: 'Server error on create session' });
    }
};

exports.getSessionDetail = async (req, res) => {
    try {
        const sessionId = req.params.id;
        const userId = req.user.id;

        const [sessions] = await pool.query(
            'SELECT * FROM counseling_sessions WHERE id = ? AND user_id = ?',
            [sessionId, userId]
        );

        if (sessions.length === 0) {
            return res.status(404).json({ success: false, message: 'Session not found' });
        }

        const session = sessions[0];

        const [messages] = await pool.query(
            'SELECT * FROM messages WHERE session_id = ? ORDER BY created_at ASC',
            [sessionId]
        );

        return res.json({
            success: true,
            session,
            messages
        });

    } catch (err) {
        console.error('Get session detail error:', err);
        return res.status(500).json({ success: false, message: 'Server error on get session' });
    }
};
