package com.example.sicaksumobileapp;

import android.app.Application;

import com.example.sicaksumobileapp.models.SicakSuProfile;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// appin her yerinde ExecutorService kullanmak icin threadleri tek bir yerden yonetmek
// icin application classi icin de tanimlandi
public class SicakSuApp extends Application {
    // fixme: when login page is made change this 
    private SicakSuProfile userProfile = new SicakSuProfile("6471dc1fe27cea661daa54b9","John","Doe","https://pbs.twimg.com/media/FjU2lkcWYAgNG6d.jpg");

    public SicakSuProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(SicakSuProfile userProfile) {
        this.userProfile = userProfile;
    }
    public ExecutorService srv  = Executors.newCachedThreadPool();

}
