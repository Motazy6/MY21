package com.example.MY21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView T , travel;
    private Handler handler;
    private static final int DELAY_TIME = 1500;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        T = findViewById(R.id.logo_T) ;
        travel = findViewById(R.id.logo_travel) ;

        Animation a = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animlogo);
        Animation b = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animlogo2);

        T.startAnimation(a);
        travel.startAnimation(b);


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                moveToNextActivity();
            }
        }, DELAY_TIME);
    }

    private void moveToNextActivity() {
        Intent intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);
        finish(); // Optional: Close this activity so the user can't navigate back to it using the back button
    }

}