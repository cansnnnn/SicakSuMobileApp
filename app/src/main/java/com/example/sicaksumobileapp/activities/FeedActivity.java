package com.example.sicaksumobileapp.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
            fab.setVisibility(View.VISIBLE);
            prgBar.setVisibility(View.INVISIBLE);
            return true;
        }
    });
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
                // Perform your desired action here
                // This code will be executed when the FloatingActionButton is clicked
                // Add your custom logic or functionality

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
