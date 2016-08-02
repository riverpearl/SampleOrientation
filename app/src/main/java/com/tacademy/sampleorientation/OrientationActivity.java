package com.tacademy.sampleorientation;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class OrientationActivity extends AppCompatActivity {

    private static final String[] ORIENTATION = {"0", "90", "180", "270"};
    ListView listView;
    ArrayAdapter<String> mAdapter;
    boolean isLand = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();

        Display display = getWindowManager().getDefaultDisplay();
        int rotation = display.getRotation();
        Toast.makeText(this, "rotation : " + ORIENTATION[rotation], Toast.LENGTH_SHORT).show();

        listView = (ListView)findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);

        if (findViewById(R.id.container) != null)
            isLand = true;
        else isLand = false;

        if (isLand) {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, MessageFragment.newInstance("default"))
                        .commit();
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String message = (String)listView.getItemAtPosition(i);

                if (isLand) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, MessageFragment.newInstance(message))
                            .commit();
                }
                else {
                    Intent intent = new Intent(OrientationActivity.this, DetailActivity.class);
                    intent.putExtra("message", message);
                    startActivity(intent);
                }
            }
        });

        initData();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            mAdapter.add("item " + i);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Display display = getWindowManager().getDefaultDisplay();
        int rotation = display.getRotation();
        Toast.makeText(this, "config change rotation : " + ORIENTATION[rotation], Toast.LENGTH_SHORT).show();

    }
}
