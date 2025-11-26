package com.campusmind.app.auth;

public class UserModel {
    private int id;
    private String name;
    private String email;
    private String password;
    private String nim;

    public UserModel(String name, String email, String password, String nim) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.nim = nim;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getNim() { return nim; }
}
