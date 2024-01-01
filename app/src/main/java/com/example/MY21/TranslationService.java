package com.example.MY21;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class TranslationService {

    private Context context;

    public TranslationService(Context context) {
        this.context = context;
    }
    public interface TranslationCallback {
        void onTranslationComplete(String translatedText);
        void onTranslationError(String errorMessage);
    }
    public void translateText(String sourceLanguage, String targetLanguage, String textToTranslate, final TranslationCallback callback) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://text-translator2.p.rapidapi.com/translate";

        // Request parameters
        final Map<String, String> params = new HashMap<>();
        params.put("source_language", sourceLanguage);
        params.put("target_language", targetLanguage);
        params.put("text", textToTranslate);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onTranslationComplete(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        error.printStackTrace();
                        callback.onTranslationError("Translation error: " + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                // Set headers
                Map<String, String> headers = new HashMap<>();
                headers.put("content-type", "application/x-www-form-urlencoded");
                headers.put("X-RapidAPI-Key", "38cb4a7221mshc19587216ab1fb8p1114d1jsn611d23451fe5");
                headers.put("X-RapidAPI-Host", "text-translator2.p.rapidapi.com");
                return headers;
            }
        };
        queue.add(stringRequest);
    }
}