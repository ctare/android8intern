package com.example.nekonoha.youtubeapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.nekonoha.youtubeapp.SearchFragment.InputStreamToString;

/**
 * Created by c0115114 on 2017/03/07.
 */

public class SearchTask extends AsyncTask<String, Void, JSONObject> {
    Fragment fragment;
    private static Fragment oldResult = null;
    final private String API_KEY = "AIzaSyAq9hSrzsG34S8nGPciwlOEh9DKIb4c7HU";

    public SearchTask(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        try {
            URL url = null;
            String term = "";

            for (String param : params) {
                term += param + " ";
            }

            String query;

            //並び替え

            SettingsData settingsData = SettingsData.getInstance();
            query = "https://www.googleapis.com/youtube/v3/search?key=" + API_KEY + "&q=" + term + "&part=id,snippet&maxResults=";
            query += settingsData.getSearchLimit();
            query += "&order=" + settingsData.getSortType() + "&type=video";

            query += "&type=video&maxResults=12";

            // TODO: 2017/03/12  maxResults="" 追加して

            url = new URL(query);
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

            List<Video> videos = new ArrayList<>();
            for (int i = 0; i < items.length(); i++) {

                JSONObject item = items.getJSONObject(i);
                videos.add(new NormalVideo(item));
//
//
//                String videoId = item.getJSONObject("id").getString("videoId");
//                String title = item.getJSONObject("snippet").getString("title");
//                String thumbnail = item.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("high").getString("url");
//
//                Log.d("videoId", videoId);
//                Log.d("title", title);
//                Log.d("url", thumbnail);
            }

            NormalVideoList normalVideoList = new NormalVideoList(videos);
            Bundle args = new Bundle();
            args.putSerializable("videos", normalVideoList);

            FragmentManager fragmentManager = fragment.getActivity().getSupportFragmentManager();
            Fragment topFragment = new TopFragment();
            topFragment.setArguments(args);
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.thumbnails, topFragment)
                    .commit();
            oldResult = topFragment;

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static Fragment oldResult() {
        return oldResult;
    }

    public static void reset() {
        oldResult = null;
    }
}
