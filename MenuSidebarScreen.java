package com.pictovie.beta.pictovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/*
  * MenuSideBar Screen for Pictovie. This class extends AppCompatActivity
  * The layout content for this class is based on menu_sidebar_screen.xml
  * This page has clickable links to go into home, profile, activity, vr mode, settings, and logout page.
  * Method setFlags() is to set the page into fullscreen without title bar
*/

public class MenuSidebarScreen extends AppCompatActivity {
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //Remove notification bar
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.menu_sidebar_screen);
            final Intent i = new Intent(MenuSidebarScreen.this, HomePage.class);
            TextView b = (TextView) findViewById(R.id.hometextlink);
            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {

                    setContentView(R.layout.home_page_screen);
                    MenuSidebarScreen.this.startActivity(i);
                }
            });

            final Intent j = new Intent(MenuSidebarScreen.this, ProfilePage.class);
            TextView c = (TextView) findViewById(R.id.profiletextlink);
            c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {

                    setContentView(R.layout.profile_screen);
                    MenuSidebarScreen.this.startActivity(j);
                }
            });
            final Intent k = new Intent(MenuSidebarScreen.this, ActivityScreen.class);
            TextView d = (TextView) findViewById(R.id.activitytextlink);
            d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {

                    setContentView(R.layout.activity_screen);
                    MenuSidebarScreen.this.startActivity(k);
                }
            });
            final Intent l = new Intent(MenuSidebarScreen.this, HomePage.class);
            TextView e= (TextView) findViewById(R.id.vrmodetextlink);
            e.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {

                    setContentView(R.layout.home_page_screen);
                    MenuSidebarScreen.this.startActivity(l);
                }
            });
            //settings and vrmode not yet created
            final Intent m = new Intent(MenuSidebarScreen.this, HomePage.class);
            TextView f = (TextView) findViewById(R.id.settingstextlink);
            f.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {

                    setContentView(R.layout.home_page_screen);
                    MenuSidebarScreen.this.startActivity(m);
                }
            });
            final Intent n = new Intent(MenuSidebarScreen.this, LoginScreen.class);
            TextView g = (TextView) findViewById(R.id.logouttextlink);
            g.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {

                    setContentView(R.layout.login_screen);
                    MenuSidebarScreen.this.startActivity(n);
                }
            });

            //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

            // getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.profile_screen);

        }
}
