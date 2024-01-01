package com.example.MY21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TwoApiActivity extends AppCompatActivity {
    private String username =null;
    private TextView textView;
    Button b,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_api);
        setup();


        if (savedInstanceState != null) {
            username = savedInstanceState.getString("username");
        } else {
            username = getIntent().getStringExtra("username");
        }
        textView.setText("       Hi " + username  );
        textView.setTextColor(Color.GRAY);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("username", username);
    }

    public void setup(){
        textView = findViewById(R.id.wle);
        b = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
    }

    public void goToTranslate(View view) {
        Intent intent = new Intent(this, TranslateActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void goToJoke(View view) {
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}