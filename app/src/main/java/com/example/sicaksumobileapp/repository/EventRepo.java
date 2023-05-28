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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

// Eventle ilgili api requestleri gonderecegimiz repository
public class EventRepo {
    // kendi bilgisayarinzda calistirdiginiz dockerdaki database erismek icin emulator kullanarak,
    // burdaki ipyi kendi ipiniz ile degistirin
    String yourIp = "10.51.14.11";
    // todo :su an hocanin verdigi apiden planet bilgileri cekip gosteriyor daha sonra duzenlenir
    // todo :backend duzenlenip duzenlenecek
    public void getAllEvents(ExecutorService srv, Handler uiHandler){
        Log.e("Noluyo","Baslangic");
        srv.submit(()->{
            try {

                List<SicakSuEvent> data = new ArrayList<>();
                // bu emulatorde calistigi icin local hosta baglanmasi icin lazimmis
                URL url =
                        new URL("http://"+yourIp+":8080/sicaksu/event");


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                Log.e("Noluyo","1");

                BufferedReader reader
                        = new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));
                Log.e("Noluyo","2");

                StringBuilder buffer = new StringBuilder();
                String line ="";
                while((line=reader.readLine())!=null){
                    Log.e("Noluyo",line);
                    buffer.append(line);
                }

                JSONArray arr = new JSONArray(buffer.toString());

                for (int i = 0; i <arr.length() ; i++) {

                    JSONObject current = arr.getJSONObject(i);
                    JSONArray joinedPeopleJson = current.getJSONArray("joinedPeople");
                    List<SicakSuProfile> joinedPeople = new ArrayList<>();
                    //turn joined people objects into java class
                    for(int j = 0; j < joinedPeopleJson.length() ; j++){
                        JSONObject currentPeople = joinedPeopleJson.getJSONObject(j);
                        SicakSuProfile currentProfile = new SicakSuProfile(
                                currentPeople.getString("id"),
                                currentPeople.getString("name"),
                                currentPeople.getString("lastname"),
                                currentPeople.getString("imageUrl")
                        );
                    }
                    //create event class with taken informations from request
                    SicakSuEvent sicakEvent = new SicakSuEvent(
                            current.getString("id"),
                            current.getString("content"),
                            current.getString("headline"),
                            current.getInt("limit"),
                            current.getInt("joinCount"),
                            joinedPeople,
                            StringToDate(current.getString("requestDate")));

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

    public LocalDateTime StringToDate(String dateString){
        String pattern = "yyyy-MM-dd'T'HH:mm:ss";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        return dateTime;
    }
}


/*Example event list
[
    {
        "id": "64720cbeae0bd83f1f643874",
        "content": "Hell yeaaaaa",
        "headline": "Event 4",
        "limit": 100,
        "joinCount": 6,
        "requestDate": "2023-05-28T09:45:00",
        "joinedPeople": [
            {
                "id": "64720c90ae0bd83f1f643872",
                "name": "Koyu",
                "lastname": "Kante",
                "imageUrl": "https://example.com/profile_images/johndoe.jpg"
            }
        ],
        "createdBy": {
            "id": "6471dc1fe27cea661daa54b9",
            "name": "John",
            "lastname": "Doe",
            "imageUrl": "https://example.com/profile_images/johndoe.jpg"
        }
    },
    {
        "id": "64720cf0ae0bd83f1f643875",
        "content": "Jojo Season 8 Episode 5",
        "headline": "Anime Watching",
        "limit": 100,
        "joinCount": 6,
        "requestDate": "2023-05-28T09:45:00",
        "joinedPeople": [
            {
                "id": "64720c90ae0bd83f1f643872",
                "name": "Koyu",
                "lastname": "Kante",
                "imageUrl": "https://example.com/profile_images/johndoe.jpg"
            }
        ],
        "createdBy": {
            "id": "64720c80ae0bd83f1f643870",
            "name": "Jonathan",
            "lastname": "Joestar",
            "imageUrl": "https://example.com/profile_images/johndoe.jpg"
        }
    },
    {
        "id": "64720d0dae0bd83f1f643876",
        "content": "Dabbe bir cin vakasi",
        "headline": "Horror movie night",
        "limit": 100,
        "joinCount": 5,
        "requestDate": "2023-05-28T09:45:00",
        "joinedPeople": [],
        "createdBy": {
            "id": "64720c90ae0bd83f1f643872",
            "name": "Koyu",
            "lastname": "Kante",
            "imageUrl": "https://example.com/profile_images/johndoe.jpg"
        }
    },
    {
        "id": "64721352a2d2ec66ca21bea8",
        "content": "Hizli and Ofkeli",
        "headline": "Adventure Movie Night",
        "limit": 100,
        "joinCount": 0,
        "requestDate": "2023-05-28T09:45:00",
        "joinedPeople": [],
        "createdBy": {
            "id": "64720c90ae0bd83f1f643872",
            "name": "Koyu",
            "lastname": "Kante",
            "imageUrl": "https://example.com/profile_images/johndoe.jpg"
        }
    }
]
* */