package com.pictovie.beta.pictovie;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
  * Screen/activity for Pictovie to add new posts/pictures. This class extends AppCompatActivity
  * The user have to type in some information about the new post such as title, description, adding
  * pictures, collaborators/admin and the duration of the video
  * The layout content for this class is based on new_screen.xml. To link into this
  * page, the user have to click plus image which is included in some pages such as homepage, profile, and activity
  * This page has a specific title bar that includes clickable image such as back image and checkmark image.
  * back image will link the page into previous activity opened. checkmark image will
  * add the posts into some pages and is clicked when the user is done creating the post. Plus image below
  * pictos and collaborators text will link the page into select screen to select or take a picture
  * Methods getSupportActionBar() is to set the title bar and window.setStatusBarColor() is to change the
  * very top stuffs(battery percentage,LTE). setText("") method is to set email,password text to blank when clicked
*/

public class NewForPlusScreen extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_screen);
        final EditText pictovieTitle = (EditText) findViewById(R.id.pictovietitle);
        final EditText description = (EditText) findViewById(R.id.description);
        final EditText pictos = (EditText) findViewById(R.id.pictos);
        final EditText collaborators = (EditText) findViewById(R.id.collaborators);
        final EditText duration = (EditText) findViewById(R.id.durationtext);


        pictovieTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pictovieTitle.setText("");
            }
        });

        pictos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pictos.setText("");
            }
        });
        collaborators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collaborators.setText("");
            }
        });
        duration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duration.setText("");
            }
        });
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description.setText("");
            }
        });
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.new_title_bar);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00dbde")));
        ImageView addPicTo = (ImageView) findViewById(R.id.addpicto);
        addPicTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(NewForPlusScreen.this, SelectScreen.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                startActivity(intent);
            }
        });
        ImageView back = (ImageView) findViewById(R.id.backs);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(NewForPlusScreen.this, HomePage.class);
                finish();
                intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                //startActivity(intent);
            }
        });

    }
}
