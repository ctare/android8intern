package com.example.nekonoha.youtubeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ollie.query.Select;

/**
 * Created by c0115114 on 2017/03/12.
 */

public class TopWrapFragment extends Fragment{
    View created;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_wrap, null);
        created = view;
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        created.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchView search = (SearchView) getActivity().findViewById(R.id.search);
                search.setIconified(!search.isIconified());
            }
        });
    }
}
