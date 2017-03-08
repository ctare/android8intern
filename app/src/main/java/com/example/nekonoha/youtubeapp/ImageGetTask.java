package com.example.nekonoha.youtubeapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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

    public ImageGetTask(ImageView img, ProgressBar p){
        image = img;
        progressBar = p;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap image;
        try {
            Thread.sleep(3000);
            URL imageUrl = new URL(params[0]);
            InputStream imageIs;
            imageIs = imageUrl.openStream();
            image = BitmapFactory.decodeStream(imageIs);
            return image;
        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (InterruptedException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        progressBar.setVisibility(View.INVISIBLE);
        // 取得した画像をImageViewに設定します。
        image.setImageBitmap(result);
    }
}
