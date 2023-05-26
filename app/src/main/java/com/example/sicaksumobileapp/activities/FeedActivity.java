package com.example.sicaksumobileapp.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sicaksumobileapp.R;
import com.example.sicaksumobileapp.SicakSuApp;
import com.example.sicaksumobileapp.models.SicakSuEvent;
import com.example.sicaksumobileapp.repository.EventRepo;

import java.util.List;

// Event feedini gosteren sayfa
public class FeedActivity extends AppCompatActivity {
    RecyclerView recView;
    ProgressBar prgBar;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<SicakSuEvent> data = (List<SicakSuEvent>) msg.obj;
            FeedListAdapter adp
                    = new FeedListAdapter(FeedActivity.this,data);
            recView.setAdapter(adp);

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
        recView.setLayoutManager(new LinearLayoutManager(this));
        prgBar.setVisibility(View.VISIBLE);
        EventRepo repo = new EventRepo();
        repo.getAllEvents(((SicakSuApp)getApplication()).srv,handler);
    }
}
