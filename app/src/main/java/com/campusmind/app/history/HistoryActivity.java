package com.campusmind.app.history;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.campusmind.app.R;
import com.campusmind.app.counseling.chat_ai.SimpleChatAdapter;

import java.util.Arrays;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView rvHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        rvHistory = findViewById(R.id.rv_history);

        SimpleChatAdapter adapter = new SimpleChatAdapter(Arrays.asList(
                "AI Chat - 30 Nov 2025",
                "Human Chat - 28 Nov 2025",
                "AI Chat - 25 Nov 2025"
        ));
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(adapter);
    }
}
