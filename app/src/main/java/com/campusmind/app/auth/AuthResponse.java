package com.campusmind.app.auth;

public class AuthResponse {
    private boolean success;
    private String message;
    private String token;
    private UserModel user;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getToken() { return token; }
    public UserModel getUser() { return user; }
}
