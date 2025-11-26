package com.campusmind.app.data.api;

import com.campusmind.app.auth.AuthResponse;
import com.campusmind.app.counseling.chat_ai.AIMessageModel;
import com.campusmind.app.counseling.chat_human.MessageModel;
import com.campusmind.app.assessment.AssessmentModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("auth/signup")
    Call<AuthResponse> signup(@Body com.campusmind.app.auth.UserModel user);

    @POST("auth/login")
    Call<AuthResponse> login(@Body com.campusmind.app.auth.UserModel user);

    @POST("session/{id}/send")
    Call<MessageModel> sendHumanMessage(
            @Path("id") int sessionId,
            @Body MessageModel message
    );

    @POST("ai/chat")
    Call<AIMessageModel> sendAIMessage(@Body AIMessageModel message);

    @POST("assessment/submit")
    Call<AssessmentModel> submitAssessment(@Body AssessmentModel assessment);
}
