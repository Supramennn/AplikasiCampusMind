package com.campusmind.app.counseling.chat_ai;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * ViewModel sederhana untuk fitur Chat AI.
 * Untuk sekarang belum pakai LiveData biar project tetap simple dan gampang di-maintain.
 */
public class ChatAIViewModel {

    private final ChatAIRepository repository;

    public ChatAIViewModel() {
        repository = new ChatAIRepository();
    }

    /**
     * Kirim pesan ke AI melalui Repository.
     *
     * @param userId  id user (bisa ambil dari prefs / backend, sementara boleh hardcode)
     * @param message pesan yang dikirim user
     * @return Call<ResponseBody> supaya bisa di-enqueue dari Activity
     */
    public Call<ResponseBody> sendAiMessage(String userId, String message) {
        return repository.sendAiMessage(userId, message);
    }
}
