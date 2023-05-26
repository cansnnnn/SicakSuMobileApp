package com.example.sicaksumobileapp;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// appin her yerinde ExecutorService kullanmak icin threadleri tek bir yerden yonetmek
// icin application classi icin de tanimlandi
public class SicakSuApp extends Application {
    public ExecutorService srv  = Executors.newCachedThreadPool();

}
