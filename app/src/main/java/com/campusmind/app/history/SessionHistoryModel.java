package com.campusmind.app.history;

public class SessionHistoryModel {
    private String sessionType;      // "Chat Human" / "Chat AI"
    private String time;
    private String lastMessage;

    public SessionHistoryModel(String sessionType, String time, String lastMessage) {
        this.sessionType = sessionType;
        this.time = time;
        this.lastMessage = lastMessage;
    }

    public String getSessionType() { return sessionType; }
    public String getTime() { return time; }
    public String getLastMessage() { return lastMessage; }
}
