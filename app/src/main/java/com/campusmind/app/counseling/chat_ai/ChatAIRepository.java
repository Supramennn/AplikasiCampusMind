package com.campusmind.app.counseling.chat_ai;

import com.campusmind.app.data.api.ApiClient;
import com.campusmind.app.data.api.ApiService;
import com.google.gson.JsonObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class ChatAIRepository {

    private final ApiService apiService;

    public ChatAIRepository() {
        apiService = ApiClient.getApiService();
    }

    // Dipakai ViewModel
    public Call<ResponseBody> sendAiMessage(String sessionId, String message) {
        return sendAIMessage(sessionId, message);
    }

    // Method utama
    public Call<ResponseBody> sendAIMessage(String sessionId, String message) {
        JsonObject bodyJson = new JsonObject();
        bodyJson.addProperty("sessionId", sessionId);
        bodyJson.addProperty("message", message);

        RequestBody requestBody = RequestBody.create(
                bodyJson.toString(),
                MediaType.parse("application/json")
        );

        return apiService.sendAiMessage(requestBody);
    }
}
