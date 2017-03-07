package com.example.nekonoha.youtubeapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;


public class ImageFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_image, null);

        new Thread(new Runnable() {
            @Override
            public void run() {
                ImageView iview = (ImageView)view.findViewById(R.id.imageView);

                InputStream istream;
                try {
                    URL url = new URL("https://i.ytimg.com/vi/ZGl8vrceu1E/hqdefault.jpg");
                    //インプットストリームで画像を読み込む
                    istream = url.openStream();
                    //読み込んだファイルをビットマップに変換
                    Bitmap bmp = BitmapFactory.decodeStream(istream);
                    //ビットマップをImageViewに設定
                    iview.setImageBitmap(bmp);
                    //インプットストリームを閉じる
                    istream.close();
                } catch (Exception e) {
                    Toast.makeText(FragmentTestActivity.created, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
        Toast.makeText(FragmentTestActivity.created, "success", Toast.LENGTH_LONG).show();

        return view;
    }


}
