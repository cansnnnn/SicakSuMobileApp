package com.example.sicaksumobileapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sicaksumobileapp.R;
import com.example.sicaksumobileapp.SicakSuApp;
import com.example.sicaksumobileapp.models.SicakSuProfile;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        loginButton.setOnClickListener(v->{
            // identify the logged in user to use the information of user in the whole application
            ((SicakSuApp)getApplication()).setUserProfile(new SicakSuProfile("6471dc1fe27cea661daa54b9","John","Doe","https://pbs.twimg.com/media/FjU2lkcWYAgNG6d.jpg"));

            // Create an Intent to open CreateEventActivity
            Intent intent = new Intent(LoginActivity.this, FeedActivity.class);

            // Clear the activity stack and start the new activity (when pressed back button do not come back)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        });

        registerButton.setOnClickListener(v->{

        });
    }
}
