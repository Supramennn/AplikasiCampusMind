package com.campusmind.app.data.api;

import com.campusmind.app.data.model.LoginRequest;
import com.campusmind.app.data.model.LoginResponse;
import com.campusmind.app.data.model.RegisterRequest;
import com.campusmind.app.data.model.RegisterResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    // ---------- AUTH ----------
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest body);

    @POST("auth/register")
    Call<RegisterResponse> register(@Body RegisterRequest body);

    // ---------- AI CHAT ----------
    @POST("/api/ai/message")
    Call<ResponseBody> sendAiMessage(@Body RequestBody body);

    // ---------- HUMAN CHAT TEXT ----------
    @POST("chat/human/text")
    Call<ResponseBody> sendHumanMessage(@Body RequestBody body);

    // ---------- HUMAN CHAT VOICE ----------
    @Multipart
    @POST("chat/human/voice")
    Call<ResponseBody> sendHumanVoice(
            @Part MultipartBody.Part audio,
            @Part("sessionId") RequestBody sessionId,
            @Part("sender") RequestBody sender
    );
}
