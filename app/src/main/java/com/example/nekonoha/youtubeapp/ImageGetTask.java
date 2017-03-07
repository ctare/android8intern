package com.example.nekonoha.youtubeapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by c0115114 on 2017/03/07.
 */

public class ImageGetTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView image;

    public ImageGetTask(ImageView img){
        image = img;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap image;
        try {
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
        // 取得した画像をImageViewに設定します。
        image.setImageBitmap(result);
    }
}
