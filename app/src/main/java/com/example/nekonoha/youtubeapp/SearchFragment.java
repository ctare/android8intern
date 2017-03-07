package com.example.nekonoha.youtubeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class SearchFragment extends Fragment{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, null);
        SearchView sview = (SearchView) view.findViewById(R.id.search);
        Log.d("a","aaaaaa");
        sview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("a",query);
                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return view;

    }


    public void search(final String term){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://www.googleapis.com/youtube/v3/search?key=AIzaSyAq9hSrzsG34S8nGPciwlOEh9DKIb4c7HU&q="+term+"&part=id,snippet&maxResults=12");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    String str = InputStreamToString(con.getInputStream());
                    Log.d("HTTP", str);

                    JSONObject json = new JSONObject(str);
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
