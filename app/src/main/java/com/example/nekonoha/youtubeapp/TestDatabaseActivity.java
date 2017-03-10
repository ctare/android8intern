package com.example.nekonoha.youtubeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ollie.Ollie;
import ollie.query.Select;

public class TestDatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_database);
        Ollie.with(getApplicationContext())
                .setName("test_db")
                .setVersion(1)
                .setLogLevel(Ollie.LogLevel.FULL)
                .init();

        TestData td = Select.from(TestData.class).fetchSingle();
        TextView tv = new TextView(this);
        tv.setText(td.name);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.data_view);
        linearLayout.addView(tv);

        ((View) findViewById(R.id.data_push)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestData testData = new TestData();
                try{
                    testData.name = ((TextView) findViewById(R.id.data_edit_name)).getText().toString();
                    testData.email = ((TextView) findViewById(R.id.data_edit_email)).getText().toString();
                    testData.age = Integer.parseInt(((TextView) findViewById(R.id.data_edit_age)).getText().toString());
                    testData.save();
                }catch (Exception e){
                    Toast.makeText(TestDatabaseActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
