package com.pictovie.beta.pictovie;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import java.io.File;

/*
  * Select Screen/activity for Pictovie. This class extends AppCompatActivity
  * The layout content for this class is based on select_screen.xml. To link into this
  * page, the user have to click the plus image below pictos and collaborators text on NewForPlusScreen class.
  * Then, it will link the page into this screen to select or take a picture. The camera image on the very top left
  * of the screen is the one that is used to take a picture
  * This page has a specific title bar that includes clickable image such as back image and checkmark image.
  * back image will link the page into previous activity opened. checkmark image will
  * add the posts into some pages and is clicked when the user is done creating the post.
  * Methods getSupportActionBar() is to set the title bar and window.setStatusBarColor is to change the
  * very top stuffs(battery percentage,LTE). Modified OnActivityResult() method is to get the taken pictures
  * and startActivityForResult(cameraIntent, CAMERA_REQUEST); is to enable the camera
*/

public class SelectScreen extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    private static final int CAMERA_REQUEST = 1888;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_screen);

        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.select_title_bar);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00dbde")));
        ImageView back = (ImageView) findViewById(R.id.backs);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(SelectScreen.this, NewForPlusScreen.class);
                finish();
                intent.addFlags(Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               // intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                //startActivity(intent);
            }
        });
        ImageView pictureSelect = (ImageView) findViewById(R.id.opencamera);
        pictureSelect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //Intent i = new Intent(
                //Intent.ACTION_PICK,
                //android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                //startActivityForResult(i, RESULT_LOAD_IMAGE);
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK && null != data) {
            Uri selectedImage = data.getData();

            ImageView imageView = (ImageView) findViewById(R.id.pic03);
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);


        }


    }
}
