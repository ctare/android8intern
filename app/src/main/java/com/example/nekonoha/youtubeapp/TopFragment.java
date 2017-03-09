package com.example.nekonoha.youtubeapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by c0115114 on 2017/03/09.
 */

public class TopFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, null);
        LinearLayout.LayoutParams outer = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams inner = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        LinearLayout thumbnails_wrap = (LinearLayout) view.findViewById(R.id.thumbnails);
        for (int j = 0; j < 10; j++) {
            LinearLayout thumbnails = new LinearLayout(getActivity());
            thumbnails.setOrientation(LinearLayout.HORIZONTAL);
            for (int i = 0; i < 2; i++) {
                View thumbnail = new ThumbnailFragment().onCreateView(inflater, container, savedInstanceState);
//                thumbnail.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
//                thumbnail.setWidth(0);
                thumbnails.addView(thumbnail, inner);
            }
            thumbnails_wrap.addView(thumbnails, outer);
        }
        return view;
    }
}
