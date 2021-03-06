package com.devStereo.owls.diary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.devStereo.owls.R;


/**
 * Created by ihyunbeom on 2017-03-03.
 */
public class IntroActivity extends Activity {
    private Handler handler;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        init();

        handler.postDelayed(runnable, 1000);
    }

    public void init() {
        handler = new Handler();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        handler.removeCallbacks(runnable);
    }
}

