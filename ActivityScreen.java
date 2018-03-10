package com.pictovie.beta.pictovie;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/*
  * Activity page screen/activity for Pictovie. This class extends AppCompatActivity
  * The layout content for this class is based on activity_screen.xml. To link into activity
  * page, the user have to choose menu sidebar on the homepage and click the Activity textview
  * This page has a specific title bar that includes clickable image such as back image and plus image.
  * back image will link the page into previous activity opened. plus image will
  * link the page into new_screen.xml and/or NewForPlusScreen.java to add new pictures, videos or posts
  * Methods getSupportActionBar() is to set the title bar and window.setStatusBarColor is to change the
  * very top stuffs(battery percentage,LTE)
*/

public class ActivityScreen extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        final Intent i = new Intent(ActivityScreen.this, HomePage.class);
        //for back button removed
        //TextView b = (TextView) findViewById(R.id.profile);
       // b.setOnClickListener(new View.OnClickListener() {
            //public void onClick(View arg0) {

                //setContentView(R.layout.home_page_screen);
               // ActivityScreen.this.startActivity(i);
            //}
        //});
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.activity_title_bar);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00dbde")));
        ImageView back = (ImageView) findViewById(R.id.backs);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(ActivityScreen.this, MenuSidebarScreen.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                startActivity(intent);
            }
        });
        ImageView buttonLoadImage = (ImageView) findViewById(R.id.plus);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //Intent i = new Intent(
                //Intent.ACTION_PICK,
                //android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Intent i = new Intent(ActivityScreen.this,NewForPlusScreen.class);
                //Intent i = new Intent(
                //Intent.ACTION_PICK,
                //android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                //startActivityForResult(i, RESULT_LOAD_IMAGE);
                ActivityScreen.this.startActivity(i);

                //startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }
}
