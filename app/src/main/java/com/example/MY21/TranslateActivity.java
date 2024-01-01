package com.example.MY21;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;

public class TranslateActivity extends AppCompatActivity {
    Button btn;
    EditText et;
    TextView tv;
    Spinner sp;
    Spinner sp2;
    TranslationService translationService = new TranslationService(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        btn = findViewById(R.id.btn_tran);
        et = findViewById(R.id.ed_s);
        tv = findViewById(R.id.tv);
        sp = findViewById(R.id.sp);
        sp2 = findViewById(R.id.sp2);

        List<LanguageOption> languageOptions = new ArrayList<>();
        languageOptions.add(new LanguageOption("en", "English"));
        languageOptions.add(new LanguageOption("fr", "French"));
        languageOptions.add(new LanguageOption("de", "German"));
        languageOptions.add(new LanguageOption("es", "Spanish"));
        languageOptions.add(new LanguageOption("it", "Italian"));
        languageOptions.add(new LanguageOption("ru", "Russian"));
        languageOptions.add(new LanguageOption("ar", "Arabic"));
        languageOptions.add(new LanguageOption("zh", "Chinese"));
        languageOptions.add(new LanguageOption("ja", "Japanese"));
        languageOptions.add(new LanguageOption("ko", "Korean"));
        languageOptions.add(new LanguageOption("pt", "Portuguese"));
        languageOptions.add(new LanguageOption("tr", "Turkish"));



        ArrayAdapter<LanguageOption> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languageOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(adapter);
        sp2.setAdapter(adapter);

    }
    public void translate(View view){
        final String text = et.getText().toString();

        LanguageOption selectedLanguage = (LanguageOption) sp.getSelectedItem();
        final String surce = selectedLanguage.getCode();
        LanguageOption selectedLanguage2 = (LanguageOption) sp2.getSelectedItem();
        final String target = selectedLanguage2.getCode();
        translationService.translateText(surce, target, text, new TranslationService.TranslationCallback() {
            @Override
            public void onTranslationComplete(String jsonResponse) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    String status = jsonObject.getString("status");

                    if ("success".equals(status)) {
                        JSONObject dataObject = jsonObject.getJSONObject("data");
                        String translatedText = dataObject.getString("translatedText");
                        System.out.println("Translated Text: " + translatedText);
                        tv.setText(translatedText);
                    } else {
                        System.out.println("Translation unsuccessful. Status: " + status);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("Error parsing JSON response: " + e.getMessage());
                }
            }

            @Override
            public void onTranslationError(String errorMessage) {
                // Handle error
                System.out.println("Translation error: " + errorMessage);
            }
        });

    }
}