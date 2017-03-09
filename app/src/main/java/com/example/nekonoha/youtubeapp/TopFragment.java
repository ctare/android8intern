package com.example.nekonoha.youtubeapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by c0115114 on 2017/03/09.
 */

public class TopFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, null);

        LinearLayout.LayoutParams outer = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams inner = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        LinearLayout thumbnails_wrap = (LinearLayout) view.findViewById(R.id.thumbnails);



        for (int j = 0; j < 0; j++) {
            LinearLayout thumbnails = new LinearLayout(getActivity());
            thumbnails.setId(100000 + j);
            thumbnails.setLayoutParams(inner);
            thumbnails.setOrientation(LinearLayout.HORIZONTAL);
            thumbnails.removeAllViews();

            for (int i = 0; i < 1; i++) {
                Fragment t_fragment = new ThumbnailFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(thumbnails.getId(),t_fragment).commit();

                //View thumbnail = new ThumbnailFragment().onCreateView(inflater, container, savedInstanceState);
//                thumbnail.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
//                thumbnail.setWidth(0);
                //thumbnails.addView(thumbnail, inner);
            }

            thumbnails_wrap.addView(thumbnails, outer);
        }
        return view;
    }
}
