package com.example.nekonoha.youtubeapp;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.LoopViewPager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TabActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, PageFragment.OnFragmentInteractionListener {
    LoopViewPager loopViewPager;
    TextView[] tab = new TextView[3];
    ColorStateList[] defaultColors = new ColorStateList[3];

    final String[] pageTitle = {"Settings", "Search", "PlayLists"};
    private ViewPager viewPager;

    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        LoopViewPager viewPager = (LoopViewPager) findViewById(R.id.pager);
        final String[] pageTitle = {"Settings", "Search", "PlayLists"};


        tab[0] = (TextView) findViewById(R.id.test_setting);
        tab[1] = (TextView) findViewById(R.id.test_top);
        tab[2] = (TextView) findViewById(R.id.test_playlist);

        for (int i = 0; i < tab.length; i++) {
            defaultColors[i] = tab[i].getTextColors();
        }


        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return PageFragment.newInstance(position + 1);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return pageTitle[position];
            }

            @Override
            public int getCount() {
                return pageTitle.length;
            }

        };

        // ViewPagerにページを設定
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        loopViewPager = viewPager;
    }





    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        for (int i = 0; i < 3; i++) {
            if(i == position) {
                tab[i].setTextColor(Color.RED);
            } else {
                tab[i].setTextColor(defaultColors[i]);
            }
        }
        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }


    @Override
    protected void onResume() {
        super.onResume();
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(!sensors.isEmpty()){
            Sensor s = sensors.get(0);
            sensorManager.registerListener(new SensorEventListener() {
                private float beforeX = 0;
                private boolean once = true;
                private int count = 0;
                private final int INTERVAL = 20;
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
                        float x = sensorEvent.values[0];

                        if(once && beforeX - x > 10){
                            loopViewPager.setCurrentItem((loopViewPager.getCurrentItem() + 1 ) %3, true);
                            once = false;
                            count = 0;
                        } else if(once && beforeX - x < -10){
                            int pos = loopViewPager.getCurrentItem() - 1;
                            loopViewPager.setCurrentItem(pos == -1 ? 2 : pos, true);
                            once = false;
                            count = 0;
                        } else {
                            if(count > INTERVAL) {
                                once = true;
                            }
                        }
                        beforeX = x;
                    }
                    if(count <= INTERVAL) count++;
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {
                }
            }, s, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}
