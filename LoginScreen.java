package com.pictovie.beta.pictovie;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/*
  * Login Screen for Pictovie. This page is launched right after splash screen
  * This class extends Activity
  * The layout content for this class is based on login_screen.xml. The user have to type the email and password
  * when logging in. This page has 2 layout, which are login screen and signup screen.
  * Signup TextView on the top side of the screen will link to sign_up_screen.xml
  * Login button on the bottom side of the screen will link the page into homepage
  * Methods getSupportActionBar() is to set the title bar and window.setStatusBarColor is to change the
  * very top stuffs(battery percentage,LTE). setText("") method is to set email,password text to blank when clicked
*/

public class LoginScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set content view AFTER ABOVE sequence (to avoid crash)
        setContentView(R.layout.login_screen);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final TextView login=(TextView) findViewById(R.id.text3);
        String styledText = "<u><font color='#00dbde'>LOGIN</font></u>";
        login.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
        Button loginButton = (Button) findViewById(R.id.loginbutton);
       loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the Data", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "E-Mail - " + email.getText().toString() + " \n" + "Password -  " + password.getText().toString()
                            ,Toast.LENGTH_SHORT).show();
                }
                email.setText("");
                password.setText("");
                Intent mainIntent = new Intent(LoginScreen.this.getApplicationContext(), HomePage.class);
                LoginScreen.this.startActivity(mainIntent);
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.setText("");
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setText("");
            }
        });


        TextView c = (TextView) findViewById(R.id.text4);
        c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                final Intent i = new Intent(LoginScreen.this,SignUpScreen.class);
                LoginScreen.this.startActivity(i);
            }
        });








    }


        //Button button = (Button) findViewById(R.id.loginbutton);
        // button.setOnClickListener(new View.OnClickListener();
    }