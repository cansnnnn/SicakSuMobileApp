package com.example.sicaksumobileapp.repository;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.sicaksumobileapp.models.SicakSuEvent;
import com.example.sicaksumobileapp.models.SicakSuProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

// Eventle ilgili api requestleri gonderecegimiz repository
public class EventRepo {
    // todo :su an hocanin verdigi apiden planet bilgileri cekip gosteriyor daha sonra duzenlenir
    // todo :backend duzenlenip duzenlenecek
    public void getAllEvents(ExecutorService srv, Handler uiHandler){
        srv.submit(()->{
            try {

                List<SicakSuEvent> data = new ArrayList<>();
                //todo : rastgele bir url buldum bunu degistirmek gerek
                URL url =
                        new URL("https://jsonplaceholder.typicode.com/posts");


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader
                        = new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));
                StringBuilder buffer = new StringBuilder();
                String line ="";
                while((line=reader.readLine())!=null){
                    buffer.append(line);
                }

                JSONArray arr = new JSONArray(buffer.toString());

                for (int i = 0; i <arr.length() ; i++) {

                    JSONObject current = arr.getJSONObject(i);

                    SicakSuEvent sicakEvent = new SicakSuEvent(
                            current.getString("id"),
                            current.getString("title"),
                            current.getString("body"),
                            5,
                            5,
                            new ArrayList<SicakSuProfile>());

                    data.add(sicakEvent);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (JSONException | IOException e) {
                Log.e("DEV",e.getMessage());
            }
        });

    }
}
