package com.example.sicaksumobileapp.activities.profile;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import com.example.sicaksumobileapp.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ProfileActivity extends AppCompatActivity {

    private Button btnCreatedEvents;
    private Button btnJoinedEvents;
    private FrameLayout fragmentContainer;
    private String profileId ;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnCreatedEvents = findViewById(R.id.btnCreatedEvents);
        btnJoinedEvents = findViewById(R.id.btnJoinedEvents);
        fragmentContainer = findViewById(R.id.fragmentContainer);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        String profileId = getIntent().getStringExtra("id");
        // Loginden gelecek
        fetchUserProfile(profileId);





        //NAVIGATION BARRRR BACK  BUTTON!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!




        // Set click listeners for the buttons
        btnCreatedEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreatedEventsFragment();
            }
        });

        btnJoinedEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJoinedEventsFragment();
            }
        });

    }

    private void showCreatedEventsFragment() {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new CreatedEventsFragment())
                .commit();
    }

    private void showJoinedEventsFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new JoinedEventsFragment())
                .commit();
    }

    //username fetch
    private void fetchUserProfile(String profileId) {
        String url = "http://10.0.2.2:8080/sicaksu/profile/" + profileId;

        // Make the API call using a networking library like Retrofit, Volley, or OkHttp
        // Here, I'll demonstrate using the HttpURLConnection as in your existing code

        Thread thread = new Thread(() -> {
            try {
                URL apiUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

                // Set up the connection and make the GET request

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    // Parse the response JSON and extract the user's name
                    JSONObject profileJson = new JSONObject(response.toString());
                    String userName = profileJson.getString("name") +" "+ profileJson.getString("lastname");

                    // Update the UI on the main thread
                    runOnUiThread(() -> {
                        // Find the TextView by its ID
                        TextView tvUserName = findViewById(R.id.profileName);

                        // Set the user's name as the text of the TextView
                        tvUserName.setText(userName);
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle back button click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // Close the current activity and navigate back
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

}

