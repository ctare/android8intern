package com.example.nekonoha.youtubeapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class ImageGetTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView image;
    private ProgressBar progressBar;
    boolean isPlayList = false;

    public ImageGetTask(ImageView img, ProgressBar p){
        image = img;
        progressBar = p;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap image;
        try {
            if(params[0] == PlayListFolderData.IDENTIFICATION){
                isPlayList = true;
                return null;
            }
            Log.d("URL",params[0]);
            URL imageUrl = new URL(params[0]);
            InputStream imageIs;
            imageIs = imageUrl.openStream();
            image = BitmapFactory.decodeStream(imageIs);
            return image;
        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        progressBar.setVisibility(View.INVISIBLE);
        // 取得した画像をImageViewに設定します。
        if(isPlayList){
            image.setImageResource(R.drawable.play_list);
        }else if(result == null){
            image.setImageResource(R.drawable.not_found);
        }else{
            image.setImageBitmap(result);
        }
    }
}
