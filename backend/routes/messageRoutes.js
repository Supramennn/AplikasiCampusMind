const express = require('express');
const router = express.Router();
const messageController = require('../controllers/messageController');
const authMiddleware = require('../middleware/authMiddleware');

router.get('/session/:sessionId', authMiddleware, messageController.getMessagesBySession);
router.post('/session/:sessionId', authMiddleware, messageController.sendMessage);

module.exports = router;
