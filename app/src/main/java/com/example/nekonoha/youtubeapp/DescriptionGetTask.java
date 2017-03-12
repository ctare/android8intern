package com.example.nekonoha.youtubeapp;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.nekonoha.youtubeapp.SearchFragment.InputStreamToString;

/**
 * Created by c0115114 on 2017/03/07.
 */

public class DescriptionGetTask extends AsyncTask<String, Void, JSONObject> {
    Fragment fragment;
    final private String API_KEY = "AIzaSyAq9hSrzsG34S8nGPciwlOEh9DKIb4c7HU";

    public DescriptionGetTask(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        try {
            URL url = null;
            String videoId = params[0];

            String query;

            //並び替え
            SettingsData settingsData = SettingsDataStatic.getInstance();
            query = "https://www.googleapis.com/youtube/v3/videos?key=" + API_KEY + "&id=" + videoId + "&part=snippet";

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
            Log.d("items", items.toString());
            String description = "";
            description = items.getJSONObject(0).getJSONObject("snippet").getString("description");
            Log.d("TEST", description);
            TextView desc_view = (TextView) fragment.getActivity().findViewById(R.id.description);
            desc_view.setText(description);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
