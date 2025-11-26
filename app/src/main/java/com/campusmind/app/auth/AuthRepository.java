package com.campusmind.app.auth;

import androidx.annotation.NonNull;

import com.campusmind.app.data.api.ApiClient;
import com.campusmind.app.data.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {

    private ApiService apiService;

    public interface AuthCallback {
        void onSuccess(AuthResponse response);
        void onError(String error);
    }

    public AuthRepository() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void login(UserModel user, final AuthCallback callback) {
        apiService.login(user).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call,
                                   @NonNull Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Login failed");
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void signup(UserModel user, final AuthCallback callback) {
        apiService.signup(user).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call,
                                   @NonNull Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Signup failed");
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
