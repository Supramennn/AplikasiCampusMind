package com.campusmind.app.counseling.chat_ai;

public class AIResponse {
    private boolean success;
    private AIMessageModel reply;

    public boolean isSuccess() { return success; }
    public AIMessageModel getReply() { return reply; }
}
