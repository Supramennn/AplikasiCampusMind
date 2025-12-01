package com.campusmind.app.counseling.chat_ai;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.campusmind.app.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatAIActivity extends AppCompatActivity {

    private RecyclerView rvChatAI;
    private EditText etMessageAI;
    private ImageButton btnSendAI;

    private SimpleChatAdapter adapter;
    private final List<String> messages = new ArrayList<>();

    private ChatAIViewModel viewModel;
    private String userId = "1"; // default kalau belum ada dari prefs

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_ai);

        // View
        rvChatAI = findViewById(R.id.rv_chat_ai);
        etMessageAI = findViewById(R.id.et_message_ai);
        btnSendAI = findViewById(R.id.btn_send_ai);

        // ViewModel
        viewModel = new ChatAIViewModel();

        // Ambil userId kalau suatu saat lo simpan di prefs
        SharedPreferences prefs = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        String savedId = prefs.getString("userId", null);
        if (savedId != null && !savedId.isEmpty()) {
            userId = savedId;
        }

        // Setup RecyclerView
        adapter = new SimpleChatAdapter(messages);
        rvChatAI.setLayoutManager(new LinearLayoutManager(this));
        rvChatAI.setAdapter(adapter);

        // Listener tombol kirim
        btnSendAI.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String text = etMessageAI.getText().toString().trim();
        if (TextUtils.isEmpty(text)) return;

        // Tambah pesan user ke UI
        messages.add("You: " + text);
        adapter.notifyItemInserted(messages.size() - 1);
        rvChatAI.scrollToPosition(messages.size() - 1);
        etMessageAI.setText("");

        // Panggil backend lewat ViewModel
        viewModel.sendAiMessage(userId, text).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {

                String replyText;

                if (response.isSuccessful() && response.body() != null) {
                    try {
                        // Kalau backend lo nanti kirim JSON, di sini bisa lo parse.
                        // Untuk sekarang, kita ambil raw string aja.
                        String raw = response.body().string();
                        replyText = "AI: " + raw;
                    } catch (IOException e) {
                        replyText = "AI: Terima kasih sudah cerita, gue dengerin ya.";
                    }
                } else {
                    replyText = "AI: Terima kasih sudah cerita, gue dengerin ya.";
                }

                addAiReply(replyText);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Kalau request gagal (no internet / server down), tetap kasih feedback ke user
                String replyText = "AI: Maaf, koneksi lagi bermasalah. Tapi kamu tetap boleh cerita di sini.";
                addAiReply(replyText);
            }
        });
    }

    private void addAiReply(String replyText) {
        messages.add(replyText);
        runOnUiThread(() -> {
            adapter.notifyItemInserted(messages.size() - 1);
            rvChatAI.scrollToPosition(messages.size() - 1);
        });
    }
}
