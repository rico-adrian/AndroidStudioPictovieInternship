package com.pictovie.beta.pictovie;

import android.app.ActionBar;
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
  * Profile page Screen/activity for Pictovie. This class extends AppCompatActivity
  * The layout content for this class is based on profile_screen.xml. To link into profile
  * page, the user have to choose menu sidebar on the homepage and click the profile textview
  * This page has a specific title bar that includes clickable image such as back image and plus image.
  * back image will link the page into previous activity opened. plus image will
  * link the page into new_screen.xml and/or NewForPlusScreen.java to add new pictures or posts
  * Methods getSupportActionBar() is to set the title bar and window.setStatusBarColor is to change the
  * very top stuffs(battery percentage,LTE).
*/

public class ProfilePage extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_screen);
        final Intent i = new Intent(ProfilePage.this, HomePage.class);
        TextView b = (TextView) findViewById(R.id.profile);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                setContentView(R.layout.home_page_screen);
                ProfilePage.this.startActivity(i);
            }
        });


        String mTitle = "";
        mTitle = (String) getTitle();
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.profile_title_bar);
        ActionBar actionBar = getActionBar();
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00dbde")));
        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        ImageView buttonLoadImage = (ImageView) findViewById(R.id.plus);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //Intent i = new Intent(
                        //Intent.ACTION_PICK,
                        //android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Intent i = new Intent(ProfilePage.this,NewForPlusScreen.class);
                //Intent i = new Intent(
                //Intent.ACTION_PICK,
                //android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                //startActivityForResult(i, RESULT_LOAD_IMAGE);
                ProfilePage.this.startActivity(i);

                //startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        // getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.profile_screen);
        ImageView back = (ImageView) findViewById(R.id.backs);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(ProfilePage.this, MenuSidebarScreen.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                startActivity(intent);
            }
        });
    }

    //public boolean onOptionsItemSelected(MenuItem item) {
        //switch (item.getItemId()) {
           // case R.id.backs:
                //app icon in action bar clicked; go home
               // Intent intent = new Intent(this, HomePage.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent);
               // return true;
           // default:
               // return super.onOptionsItemSelected(item);
            //}
            //}
        //}
    //}
}
