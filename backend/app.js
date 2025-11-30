const express = require('express');
const cors = require('cors');
require('dotenv').config();

const pool = require('./config/db');

const authRoutes = require('./routes/authRoutes');
const sessionRoutes = require('./routes/sessionRoutes');
const messageRoutes = require('./routes/messageRoutes');
const assessmentRoutes = require('./routes/assessmentRoutes');

const app = express();

app.use(cors());
app.use(express.json());

pool.getConnection()
    .then(conn => {
        console.log('MySQL connected');
        conn.release();
    })
    .catch(err => {
        console.error('MySQL connection error:', err.message);
    });

app.get('/', (req, res) => {
    res.json({ message: 'CampusMind API is running' });
});

app.use('/auth', authRoutes);
app.use('/sessions', sessionRoutes);
app.use('/messages', messageRoutes);
app.use('/assessments', assessmentRoutes);

const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
    console.log(`Server listening on port ${PORT}`);
});
