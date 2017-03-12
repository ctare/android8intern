package com.example.nekonoha.youtubeapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
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
    private String query = null;

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

            //並び替え
            SettingsData settingsData = SettingsDataStatic.getInstance();
            query = "https://www.googleapis.com/youtube/v3/search?key=" + API_KEY + "&q=" + term + "&part=id,snippet&maxResults=";
            query += settingsData.getSearchLimit();
            query += "&order=" + settingsData.getSortType() + "&type=video";

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
            }

            NormalVideoList normalVideoList = new NormalVideoList(videos);
            try{
                String nextToken = json.getString("nextPageToken");
                normalVideoList.setNextPage(query, nextToken);
            }catch (JSONException ignored){
            }
            Bundle args = new Bundle();
            args.putSerializable("videos", normalVideoList);

            FragmentManager fragmentManager = fragment.getActivity().getSupportFragmentManager();
            Fragment topFragment = new TopFragment();
            topFragment.setArguments(args);
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.top_wrap, topFragment)
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
