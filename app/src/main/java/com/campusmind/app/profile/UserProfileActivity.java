package com.campusmind.app.profile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.campusmind.app.R;
import com.campusmind.app.auth.LoginActivity;

public class UserProfileActivity extends AppCompatActivity {

    private TextView txtUserName, txtUserEmail, txtUserNim;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //txtUserName = findViewById(R.id.txt_user_name);
        //txtUserEmail = findViewById(R.id.txt_user_email);
        //txtUserNim = findViewById(R.id.txt_user_nim);
        btnLogout = findViewById(R.id.btn_logout);

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            finishAffinity();
        });
    }
}
