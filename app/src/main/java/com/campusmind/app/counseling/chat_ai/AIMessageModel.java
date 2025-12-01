package com.campusmind.app.counseling.chat_ai;

public class AIMessageModel {
    private String role;    // "user" atau "ai"
    private String message; // isi pesan

    public AIMessageModel(String role, String message) {
        this.role = role;
        this.message = message;
    }

    public String getRole() { return role; }
    public String getMessage() { return message; }

    public void setRole(String role) { this.role = role; }
    public void setMessage(String message) { this.message = message; }
}
