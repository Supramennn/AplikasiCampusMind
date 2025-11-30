const pool = require('../config/db');

exports.getMessagesBySession = async (req, res) => {
    try {
        const sessionId = req.params.sessionId;
        const userId = req.user.id;

        // cek session milik user
        const [sessions] = await pool.query(
            'SELECT * FROM counseling_sessions WHERE id = ? AND user_id = ?',
            [sessionId, userId]
        );
        if (sessions.length === 0) {
            return res.status(404).json({ success: false, message: 'Session not found or not authorized' });
        }

        const [messages] = await pool.query(
            'SELECT * FROM messages WHERE session_id = ? ORDER BY created_at ASC',
            [sessionId]
        );

        return res.json({ success: true, data: messages });

    } catch (err) {
        console.error('Get messages error:', err);
        return res.status(500).json({ success: false, message: 'Server error on get messages' });
    }
};

exports.sendMessage = async (req, res) => {
    try {
        const sessionId = req.params.sessionId;
        const userId = req.user.id;

        const { senderType, messageType, textContent, audioUrl } = req.body;
        // senderType: 'user' | 'counselor' | 'ai'
        // messageType: 'text' | 'voice'

        if (!senderType || !['user', 'counselor', 'ai'].includes(senderType)) {
            return res.status(400).json({ success: false, message: 'Invalid senderType' });
        }

        if (!messageType || !['text', 'voice'].includes(messageType)) {
            return res.status(400).json({ success: false, message: 'Invalid messageType' });
        }

        // cek session milik user (minimal user)
        const [sessions] = await pool.query(
            'SELECT * FROM counseling_sessions WHERE id = ? AND user_id = ?',
            [sessionId, userId]
        );
        if (sessions.length === 0) {
            return res.status(404).json({ success: false, message: 'Session not found or not authorized' });
        }

        let senderId = null;
        if (senderType === 'user' || senderType === 'counselor') {
            senderId = userId; // untuk sekarang pakai id user login; kalau nanti role konselor beda, bisa diatur lagi
        }

        const [result] = await pool.query(
            'INSERT INTO messages (session_id, sender_type, sender_id, message_type, text_content, audio_url) VALUES (?, ?, ?, ?, ?, ?)',
            [
                sessionId,
                senderType,
                senderId,
                messageType,
                messageType === 'text' ? textContent || null : null,
                messageType === 'voice' ? audioUrl || null : null
            ]
        );

        const [insertedRows] = await pool.query(
            'SELECT * FROM messages WHERE id = ?',
            [result.insertId]
        );

        return res.status(201).json({
            success: true,
            message: 'Message stored',
            data: insertedRows[0]
        });

    } catch (err) {
        console.error('Send message error:', err);
        return res.status(500).json({ success: false, message: 'Server error on send message' });
    }
};
