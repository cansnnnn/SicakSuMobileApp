package com.example.sicaksumobileapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sicaksumobileapp.R;
import com.example.sicaksumobileapp.SicakSuApp;
import com.example.sicaksumobileapp.models.SicakSuEvent;
import com.example.sicaksumobileapp.models.SicakSuProfile;
import com.example.sicaksumobileapp.repository.EventRepo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

// Event feedini gosteren sayfa
public class FeedActivity extends AppCompatActivity {
    RecyclerView recView;
    ProgressBar prgBar;
    FloatingActionButton fab;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<SicakSuEvent> data = (List<SicakSuEvent>) msg.obj;
            FeedListAdapter adp
                    = new FeedListAdapter(FeedActivity.this,data);
            recView.setAdapter(adp);
            recView.setVisibility(View.VISIBLE);
            fab.setVisibility(View.VISIBLE);
            prgBar.setVisibility(View.INVISIBLE);
            return true;
        }
    });

    // when this page is opened again fetch data again
    @Override
    protected void onResume() {
        super.onResume();
        recView.setVisibility(View.INVISIBLE);
        fab.setVisibility(View.INVISIBLE);
        prgBar.setVisibility(View.VISIBLE);
        EventRepo repo = new EventRepo();
        repo.getAllEvents(((SicakSuApp)getApplication()).srv,handler);

        // Example: Show a toast message
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        prgBar = findViewById(R.id.feedProgresBar);
        recView = findViewById(R.id.recyclerViewFeed);
        fab = findViewById(R.id.createEventButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to open CreateEventActivity
                Intent intent = new Intent(FeedActivity.this, CreateEventActivity.class);
                //todo: change this with global yourProfile
                SicakSuProfile yourProfile = new SicakSuProfile("6471dc1fe27cea661daa54b9","John","Doe","https://pbs.twimg.com/media/FjU2lkcWYAgNG6d.jpg");
                // Pass the profile information to the intent
                intent.putExtra("profileId", yourProfile.getId());

                // Start the CreateEventActivity
                startActivity(intent);
                // Show a Snackbar with a message
                Snackbar.make(v, "Create Activity", Snackbar.LENGTH_SHORT).show();
            }
        });
        recView.setLayoutManager(new LinearLayoutManager(this));
        fab.setVisibility(View.INVISIBLE);
        prgBar.setVisibility(View.VISIBLE);
        EventRepo repo = new EventRepo();
        repo.getAllEvents(((SicakSuApp)getApplication()).srv,handler);
    }
}
