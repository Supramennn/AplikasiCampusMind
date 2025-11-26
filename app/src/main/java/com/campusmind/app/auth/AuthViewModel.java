package com.campusmind.app.auth;

public class AuthViewModel {

    private AuthRepository repository;

    public AuthViewModel() {
        repository = new AuthRepository();
    }

    public void login(UserModel user, AuthRepository.AuthCallback callback) {
        repository.login(user, callback);
    }

    public void signup(UserModel user, AuthRepository.AuthCallback callback) {
        repository.signup(user, callback);
    }
}
