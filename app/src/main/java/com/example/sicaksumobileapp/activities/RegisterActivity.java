package com.example.sicaksumobileapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sicaksumobileapp.R;
import com.example.sicaksumobileapp.SicakSuApp;
import com.example.sicaksumobileapp.models.AuthenticationPayload;
import com.example.sicaksumobileapp.repository.EventRepo;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText imageUrl;
    private EditText username;
    private EditText password;
    private Button buttonRegister;
    private ProgressBar progressBar;

    Handler registerHandler = new Handler(message -> {
        AuthenticationPayload payload = (AuthenticationPayload)message.obj;

        if ("authenticated".equals(payload.getStatus())) {
            showSnackbar("Registered");
            // initilaize user's profile
            ((SicakSuApp)getApplication()).setUserProfile(payload.getProfile());

            // Create an Intent to open CreateEventActivity
            Intent intent = new Intent(RegisterActivity.this, FeedActivity.class);
            // Clear the activity stack and start the new activity (when pressed back button do not come back)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if ("notAuthenticated".equals(payload.getStatus())) {
            // The register event failed
            showSnackbar("Not registered");
        }
        progressBar.setVisibility(View.INVISIBLE);
        buttonRegister.setVisibility(View.VISIBLE);
        return true;
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstName = findViewById(R.id.registerName);
        lastName = findViewById(R.id.registerLastName);
        imageUrl = findViewById(R.id.registerImageUrl);
        username = findViewById(R.id.registerUsername);
        password = findViewById(R.id.registerPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        progressBar = findViewById(R.id.registerProgressbar);
        progressBar.setVisibility(View.INVISIBLE);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                buttonRegister.setVisibility(View.INVISIBLE);
                register();
                //Snackbar.make(v, "Register button pressed", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
    // Function to show a Snackbar
    private void showSnackbar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

    private void register() {
        String simageUrl = imageUrl.getText().toString().trim();
        String susername = username.getText().toString().trim();
        String spassword = password.getText().toString().trim();
        String sfirstName = firstName.getText().toString().trim();
        String slastName = lastName.getText().toString().trim();

        if (TextUtils.isEmpty(susername) || TextUtils.isEmpty(spassword) || TextUtils.isEmpty(sfirstName) || TextUtils.isEmpty(slastName) ) {
            showSnackbar("There is empty fields");
            return;
        }
        if(TextUtils.isEmpty(simageUrl)){
            simageUrl = "data:image/jpeg;base64,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";
        }
        EventRepo repo = new EventRepo();
        SicakSuApp app = (SicakSuApp)getApplication();
        repo.register(app.srv,registerHandler,susername,spassword,simageUrl,sfirstName,slastName);
    }
}
