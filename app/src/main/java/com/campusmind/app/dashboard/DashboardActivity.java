package com.campusmind.app.dashboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.campusmind.app.R;
import com.campusmind.app.counseling.chat_ai.ChatAIActivity;
import com.campusmind.app.counseling.chat_human.ChatHumanActivity;
import com.campusmind.app.history.HistoryActivity;
import com.campusmind.app.profile.UserProfileActivity;

public class DashboardActivity extends AppCompatActivity {

    private LinearLayout cardAiChat, cardHumanChat;
    private TextView tvGreeting, navHome, navHistory, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Card fitur utama
        cardAiChat = findViewById(R.id.card_ai_chat);
        cardHumanChat = findViewById(R.id.card_human_chat);

        // Bottom nav
        navHome = findViewById(R.id.nav_home);
        navHistory = findViewById(R.id.nav_history);
        navProfile = findViewById(R.id.nav_profile);

        // Greeting text
        tvGreeting = findViewById(R.id.tv_greeting);

        setupGreeting();
        setupClickListeners();
    }

    private void setupGreeting() {
        SharedPreferences prefs = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        String name = prefs.getString("name", "User");

        String greeting = "Halo, " + name + " 👋";
        tvGreeting.setText(greeting);
    }

    private void setupClickListeners() {

        cardAiChat.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ChatAIActivity.class);
            startActivity(intent);
        });

        cardHumanChat.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ChatHumanActivity.class);
            startActivity(intent);
        });

        navHome.setOnClickListener(v -> {
            // Udah di home, bisa di-ignore atau kasih animasi kecil nanti
        });

        navHistory.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, HistoryActivity.class);
            startActivity(intent);
        });

        navProfile.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, UserProfileActivity.class);
            startActivity(intent);
        });
    }
}
