package com.ase.lsharma.loginmodule;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    Button loginbutton, registerbutton, guestbutton;
    EditText login, password, name, newlogin, newpassword, newpassword2;
    private static final String REGISTER_URL = "https://scss.tcd.ie/~lsharma/advancedsoftwareengineering";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loginbutton = (Button) findViewById(R.id.loginbtn);
        registerbutton = (Button) findViewById(R.id.registerbtn);
        guestbutton = (Button) findViewById(R.id.guestbtn);

        login = (EditText) findViewById(R.id.loginemail);
        password = (EditText) findViewById(R.id.loginpassword);
        newlogin = (EditText) findViewById(R.id.signupemail);
        newpassword = (EditText) findViewById(R.id.signuppassword);
        newpassword2 = (EditText) findViewById(R.id.signuppassword2);
        name = (EditText) findViewById(R.id.signupname);

        listenbuttons();
    }


    void listenbuttons() {

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(login.getText().toString(), password.getText().toString());
            }
        });

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(newlogin.getText().toString(), newpassword.getText().toString(), name.getText().toString());
            }
        });
    }


    private void loginUser(final String username, String pass) {

        String urlSuffix = "?checklogin=" + username + "&password=" + pass;

        class LoginUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.equals("1")) {
                    Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL + s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result = bufferedReader.readLine();

                    return result;
                } catch (Exception e) {
                    return null;
                }
            }
        }

        LoginUser ru = new LoginUser();
        ru.execute(urlSuffix);

    }





    private void registerUser(final String username, String pass, String name) {
        String urlSuffix = "?newlogin=" + username + "&password=" + pass + "&name=" + name;

        class LoginUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.equals("1")) {
                    Toast.makeText(MainActivity.this, "Signed up successfully!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Sign up failed!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL + s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result = bufferedReader.readLine();

                    return result;
                } catch (Exception e) {
                    return null;
                }
            }
        }

        LoginUser ru = new LoginUser();
        ru.execute(urlSuffix);
    }





}