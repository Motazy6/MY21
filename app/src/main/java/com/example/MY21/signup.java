package com.example.MY21;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.Arrays;

public class signup extends AppCompatActivity {

    EditText firstNameEditText, lastNameEditText, emailEditText, passwordEditText;
    Button end_signup;
    SharedPreferences sharedPreferences;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupuser);

        firstNameEditText = findViewById(R.id.ed_firstname);
        lastNameEditText = findViewById(R.id.ed_lastname);
        emailEditText = findViewById(R.id.ed_email);
        passwordEditText = findViewById(R.id.ed_password);
        end_signup = findViewById(R.id.endsignup);

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);


        end_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(signup.this);
                String usersJson = preferences.getString("users", "[]");
                user[] usersArray = new Gson().fromJson(usersJson, user[].class);
                user[] updatedUsersArray = Arrays.copyOf(usersArray, usersArray.length + 1);
                updatedUsersArray[usersArray.length] = new user(firstName, lastName, email, password);
                String updatedUsersJson = new Gson().toJson(updatedUsersArray);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("users", updatedUsersJson);
                editor.apply();


                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
                finish();
            }
        });






    }




}
