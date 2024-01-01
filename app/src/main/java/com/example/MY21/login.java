package com.example.MY21;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class login extends AppCompatActivity {


    Button signin, signup, showpass;

    public static final String EMAIL = "EMAIL";
    public static final String PASS = "PASS";
    public static final String FLAG = "FLAG";
    private boolean flag = false;

    private EditText emailEditText, passwordEditText;
    private CheckBox rememberMeCheckbox;
    private SharedPreferences Shardprefs;
    private SharedPreferences.Editor ed;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);


        signin = findViewById(R.id.signIN);
        showpass = findViewById(R.id.showpass);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        rememberMeCheckbox = findViewById(R.id.rememberMeCheckbox);
        signup=findViewById(R.id.signUP);

        Shardprefs= PreferenceManager.getDefaultSharedPreferences(this);
        ed = Shardprefs.edit();

        checkPrefs();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this,signup.class);
                startActivity(i);


            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!emailEditText.getText().toString().isEmpty() || !passwordEditText.getText().toString().isEmpty()) {
                    String enteredEmail = emailEditText.getText().toString().trim();
                    String enteredPassword = passwordEditText.getText().toString().trim();
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(login.this);
                    String usersJson = preferences.getString("users", "[]");

                    user[] usersArray = new Gson().fromJson(usersJson, user[].class);
                    boolean credentialsMatch = false;
                    for (user storedUser : usersArray) {
                        if (enteredEmail.equals(storedUser.getEmail()) && enteredPassword.equals(storedUser.getPassword())) {
                            credentialsMatch = true;
                            username = storedUser.getFirstname() +" "+ storedUser.getLastname();
                            break;
                        }
                    }

                    if (credentialsMatch) {
                        Intent intent = new Intent(login.this, TwoApiActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    } else {
                        Toast.makeText(login.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(login.this, "Please enter your Email and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility();

            }
        });


    }
    public void rememberMe(View view) {
        String name = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(rememberMeCheckbox.isChecked()){
            if(!flag) {
                ed.putString(EMAIL, name);
                ed.putString(PASS, password);
                ed.putBoolean(FLAG, true);
                ed.commit();
            }

        }
    }

    private void checkPrefs() {
        flag = Shardprefs.getBoolean(FLAG, false);

        if(flag) {
            String name = Shardprefs.getString(EMAIL, "");
            String password = Shardprefs.getString(PASS, "");
            emailEditText.setText(name);
            passwordEditText.setText(password);
            rememberMeCheckbox.setChecked(true);

        }

    }

    private void togglePasswordVisibility() {
        if (passwordEditText.getTransformationMethod() == null) {
            passwordEditText.setTransformationMethod(new PasswordTransformationMethod());
        } else {
            passwordEditText.setTransformationMethod(null);
        }
        passwordEditText.setSelection(passwordEditText.getText().length());
    }




}
