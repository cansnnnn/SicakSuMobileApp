package com.example.sicaksumobileapp.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sicaksumobileapp.R;

public class ProfileActivity extends AppCompatActivity {

    private TextView textView;
    private Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textView = findViewById(R.id.profiletextView);
        backButton = findViewById(R.id.profileBackButton);

        // Get the ID from the intent extras
        String id = getIntent().getStringExtra("id");

        // Set the text of the TextView with the ID
        if (id != null) {
            textView.setText(id);
        }
        backButton.setOnClickListener(v->{
            finish();
        });
    }

}
