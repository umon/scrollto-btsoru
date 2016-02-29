package com.example.umut.preferences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ScrollView horizontalScrollView;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        horizontalScrollView = (ScrollView) findViewById(R.id.horizontalScrollView);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Log.e("getScroll", String.valueOf(preferences.getInt("Scroll", 0)));

        horizontalScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("Scroll", horizontalScrollView.getScrollY());
                editor.commit();
                Log.e("setScroll", String.valueOf(horizontalScrollView.getScrollY()));
            }
        });
        horizontalScrollView.post(new Runnable() {
            @Override
            public void run() {
                horizontalScrollView.scrollTo(0,preferences.getInt("Scroll", 0));

            }
        });

    }


}
