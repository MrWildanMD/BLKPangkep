package com.blk.blkpangkep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private long DELAY_MILLS = 3000;

    SharedPreferences sp;
    SharedPreferences.Editor sharedEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_splash);

        sp = getSharedPreferences("com.blk.blkpangkep", MODE_PRIVATE);
        sharedEditor = sp.edit();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isFirstRun()) {
                    Intent i = new Intent(SplashActivity.this, FirstActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        }, DELAY_MILLS);
    }

    public boolean isFirstRun() {
        if (sp.getBoolean("firstTime", true)) {
            sharedEditor.putBoolean("firstTime", false);
            sharedEditor.commit();
            sharedEditor.apply();
            return true;
        } else {
            return false;
        }
    }
}