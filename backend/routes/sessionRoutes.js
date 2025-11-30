const express = require('express');
const router = express.Router();
const sessionController = require('../controllers/sessionController');
const authMiddleware = require('../middleware/authMiddleware');

router.post('/', authMiddleware, sessionController.createSession);
router.get('/:id', authMiddleware, sessionController.getSessionDetail);

module.exports = router;
