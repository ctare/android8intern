package com.example.nekonoha.youtubeapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.nekonoha.youtubeapp.SearchFragment.InputStreamToString;

/**
 * Created by c0115114 on 2017/03/07.
 */

public class SearchTask extends AsyncTask<String, Void, JSONObject> {
    @Override
    protected JSONObject doInBackground(String... params) {
        try {
            URL url = null;
            url = new URL("https://www.googleapis.com/youtube/v3/search?key=AIzaSyAq9hSrzsG34S8nGPciwlOEh9DKIb4c7HU&q="+params[0]+"&part=id,snippet&maxResults=12");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            String str = InputStreamToString(con.getInputStream());
            Log.d("HTTP", str);

            return new JSONObject(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        try {
            JSONArray items = json.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {

                JSONObject item = items.getJSONObject(i);

                String videoId = item.getJSONObject("id").getString("videoId");
                String title = item.getJSONObject("snippet").getString("title");
                String thumbnail = item.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("high").getString("url");

                Log.d("videoId", videoId);
                Log.d("title", title);
                Log.d("url", thumbnail);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
