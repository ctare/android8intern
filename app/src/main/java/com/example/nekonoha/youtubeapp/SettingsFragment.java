package com.example.nekonoha.youtubeapp;

import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;


public class SettingsFragment extends Fragment {
    SeekBar seekBar;
    TextView results;
    Button s_settings_button;
    TableLayout search_settings;
    boolean isOpendSearch = true;
    private int ANIMATION_DURATION = 500;
    static final Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new FastOutSlowInInterpolator();
    private Spinner sort;
    private String spinnerItems[] = SettingsDataStatic.sortJa;
    private TextView now_sort;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, null);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        seekBar = (SeekBar) getActivity().findViewById(R.id.resultSeekBar);
        results = (TextView) getActivity().findViewById(R.id.results);

        //初期値
        final SettingsData settingsData = SettingsDataStatic.getInstance();
        Integer now = settingsData.searchLimit;
        if(now == null){
            results.setText(SettingsDataStatic.DEFAULT_SEARCH_LIMIT.toString() + "件");
        } else {
            results.setText(now.toString() + "件");
            seekBar.setProgress(now / 2  - 1);
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //ドラッグしたとき
                Integer result = (progress + 1) * 2;
                results.setText(result.toString() + "件");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //触ったとき

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Integer result = (seekBar.getProgress() + 1) * 2;
                settingsData.searchLimit = result;
                settingsData.save();

            }
        });

        //検索設定

        s_settings_button = (Button) getActivity().findViewById(R.id.s_settings_button);
        s_settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_settings = (TableLayout) getActivity().findViewById(R.id.search_settings);

                if (isOpendSearch) {
                    ViewCompat.animate(search_settings)
                            .alpha(0f)
                            .translationY(-search_settings.getHeight())
                            .scaleX(1f)
                            .setDuration(ANIMATION_DURATION)
                            .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                            .setListener(new ViewPropertyAnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(View view) {
                                    search_settings.setVisibility(View.GONE);
                                }
                            })
                            .start();
                } else {
                    ViewCompat.animate(search_settings)
                            .alpha(1f)
                            .translationY(0)
                            .scaleX(1f)
                            .setDuration(ANIMATION_DURATION)
                            .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                            .setListener(new ViewPropertyAnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(View view) {
                                    search_settings.setVisibility(View.VISIBLE);
                                }
                            })
                            .start();
                }
                isOpendSearch = !isOpendSearch;
            }
        });

        sort = (Spinner)getActivity().findViewById(R.id.sort);
        now_sort = (TextView) getActivity().findViewById(R.id.now_sort);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sort.setAdapter(adapter);
        sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) sort.getSelectedItem();

                now_sort.setText(item);
                settingsData.sortType = SettingsDataStatic.toEn.get(item);
                settingsData.save();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sort.setSelection(SettingsDataStatic.getItemPosition(settingsData.sortType));
    }
}
