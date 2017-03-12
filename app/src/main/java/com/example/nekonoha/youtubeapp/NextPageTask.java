package com.example.nekonoha.youtubeapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

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

public class NextPageTask extends AsyncTask<String, Void, JSONObject> {
    Fragment fragment;
    private static Fragment oldResult = null;
    final private String API_KEY = "AIzaSyAq9hSrzsG34S8nGPciwlOEh9DKIb4c7HU";
    private String query = null;


    public NextPageTask(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        try {
            query = params[0];
            URL url = new URL(params[0] + "&pageToken=" + params[1]);
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
                    .add(R.id.thumbnails, topFragment)
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
