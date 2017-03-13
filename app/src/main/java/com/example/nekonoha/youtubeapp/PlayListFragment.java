package com.example.nekonoha.youtubeapp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;


/**
 * Created by c0115114 on 2017/03/08.
 */

public class PlayListFragment extends Fragment{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = TopFragment.inOnCreateView(R.id.playlist_print, this, inflater.inflate(R.layout.fragment_play_list, null), null);

        PlayListFolderData playListFolderData = null;
        if(getArguments() != null){
            Serializable arg = getArguments().getSerializable("videos");
            if(arg != null && arg instanceof PlayListFolderData.AsVideoList){
                playListFolderData = ((PlayListFolderData.AsVideoList) arg).asData();
            }
        }

        PlayListFolderData.select(playListFolderData);
        if(playListFolderData != null){
            final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            final LinearLayout bread = (LinearLayout)  view.findViewById(R.id.play_list_title_bread);

            addBread(bread, layoutParams, playListFolderData, true);
            for (int i = 0; i < 20; i++) {
                playListFolderData = playListFolderData.getParent();
                addBread(bread, layoutParams, playListFolderData);
                if(playListFolderData == null){
                    break;
                }
            }
        }

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void addBread(LinearLayout bread, LinearLayout.LayoutParams layoutParams, final PlayListFolderData data){
        addBread(bread, layoutParams, data, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void addBread(LinearLayout bread, LinearLayout.LayoutParams layoutParams, final PlayListFolderData data, boolean isEnd){
        if(data == null){
            return;
        }
        TextView textView = new TextView(getActivity());
        textView.setTextSize(24);
        textView.setTextColor(getResources().getColor(R.color.colorFontLight, getActivity().getTheme()));

        if(data != null) {
            textView.setText(data.name);
        } else {
            textView.setText("top");
        }
        if(!isEnd){
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(data != null){
                        PlayList.viewPlayList(getActivity(), data, true);
                    }
                }
            });
            addNext(bread);
        }
        bread.addView(textView, 0, layoutParams);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void addNext(LinearLayout bread){
        TextView textView = new TextView(getActivity());
        textView.setText(" > ");
        textView.setTextSize(24);
        textView.setTextColor(getResources().getColor(R.color.colorFontLight, getActivity().getTheme()));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        bread.addView(textView, 0, layoutParams);
    }
}
