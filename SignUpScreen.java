package com.pictovie.beta.pictovie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
  * SignUp Screen for Pictovie. This page is launched right after splash screen
  * The layout content for this class is based on sign_up_screen.xml
  * This page has 2 layout, which are login screen and signup screen.
  * Login TextView on the top side of the screen will link to log_in_screen.xml. The user can
  * move back and forth between the login and signup page. The user have to type the email and password
  * and retype password when signing up. Signup button on the bottom side of the screen will link the page into homepage
  * Methods getSupportActionBar() is to set the title bar and window.setStatusBarColor is to change the
  * very top stuffs(battery percentage,LTE). setText("") method is to set email,password text to blank when clicked
*/

public class SignUpScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sign_up_screen);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText repeatPassword = (EditText) findViewById(R.id.repeatPassword);
        final TextView signUp=(TextView) findViewById(R.id.text2);
        String styledText = " <u><font color='#00dbde'>SIGN UP</font></u>";
        signUp.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
        Button signUpButton = (Button) findViewById(R.id.signupbutton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()|| repeatPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the Data", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "E-Mail - " + email.getText().toString() + " \n" + "Password -  " + password.getText().toString()
                            + " \n" + "Repeat Password -  " + repeatPassword.getText().toString(),Toast.LENGTH_SHORT).show();
                }
                email.setText("");
                password.setText("");
                repeatPassword.setText("");
                username.setText("");
                Intent mainIntent = new Intent(SignUpScreen.this.getApplicationContext(), HomePage.class);
                SignUpScreen.this.startActivity(mainIntent);
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
        repeatPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeatPassword.setText("");
            }
        });
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
            }
        });

        TextView b = (TextView) findViewById(R.id.text1);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                final Intent i = new Intent(SignUpScreen.this, LoginScreen.class);
                SignUpScreen.this.startActivity(i);
            }
        });




    }

    //Button button = (Button) findViewById(R.id.loginbutton);
    // button.setOnClickListener(new View.OnClickListener();
}

