package com.example.MY21;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class JokeService {

    private Context context;

    public JokeService(Context context) {
        this.context = context;
    }

    public interface JokeCallback {
        void onJokeSuccess(JSONObject responseData);
        void onJokeError(String errorMessage);
    }

    public void getJoke(final JokeCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://official-joke-api.appspot.com/random_joke";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response here
                        callback.onJokeSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        error.printStackTrace();
                        callback.onJokeError("Joke error: " + error.getMessage());
                    }
                });
        queue.add(jsonObjectRequest);
    }
}