package com.example.nekonoha.youtubeapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    // HTTPボタン押下
    public void onBtnHttpClicked(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://www.googleapis.com/youtube/v3/search?key=AIzaSyAq9hSrzsG34S8nGPciwlOEh9DKIb4c7HU&q=猫&part=id&maxResults=3");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    String str = InputStreamToString(con.getInputStream());
                    Log.d("HTTP", str);

                    JSONObject json = new JSONObject(str);
                    JSONArray items = json.getJSONArray("items");

                    for (int i = 0; i < items.length(); i++) {
                        JSONObject item = items.getJSONObject(i);

                        String videoId = item.getJSONObject("id").getString("videoId");
                        Log.d("videoId",videoId);
                    }



                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }).start();
    }

    // InputStream -> String
    static String InputStreamToString(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }


}