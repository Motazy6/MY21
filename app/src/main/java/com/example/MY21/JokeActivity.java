package com.example.MY21;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

public class JokeActivity extends AppCompatActivity {

    Button btn;

    TextView tv, tv2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.res1);
        tv2 = findViewById(R.id.res2);



    }
    public void generateJoke(View view){
        JokeService jokeService = new JokeService(this);

        jokeService.getJoke(new JokeService.JokeCallback() {
            @Override
            public void onJokeSuccess(JSONObject responseData) {
                System.out.println("Joke: " + responseData.toString());
                String setup = responseData.optString("setup");
                String punchline = responseData.optString("punchline");
                System.out.println("Setup: " + setup);
                System.out.println("Punchline: " + punchline);
                tv.setText("Setup: " + setup);
                tv2.setText("Punchline: " + punchline);
            }

            @Override
            public void onJokeError(String errorMessage) {
                System.out.println("Joke Error: " + errorMessage);
            }
        });
    }
}