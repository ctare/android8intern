package com.example.nekonoha.youtubeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;


public class ImageFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_image, null);
        ProgressBar p = (ProgressBar)view.findViewById(R.id.progressBar);
        ImageView image = (ImageView) view.findViewById(R.id.imageView);
        //画像取得スレッド起動
        ImageGetTask task = new ImageGetTask(image,p);
        task.execute("https://www.gstatic.com/android/market_images/web/play_logo_x2.png");

        return view;
    }


}
