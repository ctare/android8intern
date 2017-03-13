package com.example.nekonoha.youtubeapp;

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
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TextView;


public class SettingsFragment extends Fragment {
    SeekBar seekBar;
    TextView results;
    Button s_settings_button;
    TableLayout search_settings;

    SeekBar gyro_sensitivity_seekBar;
    TextView gyro_sensitivity;
    Button sensor_settings_button;
    TableLayout sensor_settings;

    Switch gyro_switch;

    boolean isOpendSearch = true;
    boolean isOpendSensor = true;
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

        //検索結果の件数
        seekBar = (SeekBar) getActivity().findViewById(R.id.resultSeekBar);
        results = (TextView) getActivity().findViewById(R.id.results);

        //初期値
        final SettingsData settingsData = SettingsDataStatic.getInstance();
        Integer now = settingsData.getSearchLimit();
        results.setText(now.toString() + "件");
        seekBar.setProgress(now / 2 - 1);

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

        //ソート

        sort = (Spinner) getActivity().findViewById(R.id.sort);
        now_sort = (TextView) getActivity().findViewById(R.id.now_sort);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_custom_layout, spinnerItems);
        adapter.setDropDownViewResource(R.layout.dialog_custom_layout);
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

        //ジャイロ感度
        gyro_sensitivity_seekBar = (SeekBar) getActivity().findViewById(R.id.gyro_sensitivity);
        gyro_sensitivity = (TextView) getActivity().findViewById(R.id.gyro_sensitivity_text);

        //初期値
        Integer gyro_now = settingsData.getGyro();
        gyro_sensitivity.setText(gyro_now.toString());
        gyro_sensitivity_seekBar.setProgress(gyro_now + 1);

        gyro_sensitivity_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //ドラッグしたとき
                Integer result = progress + 2;
                gyro_sensitivity.setText(result.toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //触ったとき

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Integer result = gyro_sensitivity_seekBar.getProgress() + 2;
                settingsData.gyro = result;
                settingsData.save();

            }
        });

        gyro_switch = (Switch) getActivity().findViewById(R.id.gyro_switch);
        Log.d("activ", settingsData.getGyroOn() == null ? "g null" : "g not null");
        gyro_switch.setChecked(settingsData.getGyroOn());
        gyro_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO: 2017/03/13 スイッチ
                settingsData.gyroOn = isChecked ? 1 : 0;
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
                            .translationY(-search_settings.getHeight() / 2)
                            .scaleY(0f)
                            .setDuration(ANIMATION_DURATION)
                            .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                            .setListener(new ViewPropertyAnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(View view) {
                                    search_settings.setVisibility(View.GONE);
                                }
                            })
                            .start();
                    isOpendSearch = false;
                } else {
                    ViewCompat.animate(search_settings)
                            .alpha(1f)
                            .translationY(0)
                            .scaleY(1f)
                            .setDuration(ANIMATION_DURATION)
                            .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                            .setListener(new ViewPropertyAnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(View view) {
                                    search_settings.setVisibility(View.VISIBLE);
                                }
                            })
                            .start();
                    isOpendSearch = true;
                }

            }
        });


        //センサー設定

        sensor_settings_button = (Button) getActivity().findViewById(R.id.sensor_settings_button);
        sensor_settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensor_settings = (TableLayout) getActivity().findViewById(R.id.sensor_settings);

                if (isOpendSensor) {
                    ViewCompat.animate(sensor_settings)
                            .alpha(0f)
                            .translationY(-sensor_settings.getHeight() / 2)
                            .scaleY(0f)
                            .setDuration(ANIMATION_DURATION)
                            .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                            .setListener(new ViewPropertyAnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(View view) {
                                    sensor_settings.setVisibility(View.GONE);
                                }
                            })
                            .start();
                    isOpendSensor = false;
                } else {
                    ViewCompat.animate(sensor_settings)
                            .alpha(1f)
                            .translationY(0)
                            .scaleY(1f)
                            .setDuration(ANIMATION_DURATION)
                            .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                            .setListener(new ViewPropertyAnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(View view) {
                                    sensor_settings.setVisibility(View.VISIBLE);
                                }
                            })
                            .start();
                    isOpendSensor = true;
                }

            }
        });


    }
}
