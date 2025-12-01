package com.campusmind.app.auth;

import com.campusmind.app.data.api.ApiClient;
import com.campusmind.app.data.api.ApiService;

public class AuthRepository {

    private ApiService apiService;

    public AuthRepository() {
        apiService = ApiClient.getApiService();
    }

    public ApiService getApi() {
        return apiService;
    }
}
