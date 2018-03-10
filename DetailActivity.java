package com.pictovie.beta.pictovie;

import android.support.v7.app.ActionBarActivity;

/**
 * Created by ricohelvidadrian on 7/24/2017.
 */

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;


    public class DetailActivity extends AppCompatActivity{

        private ImageView ivMovieIcon;
        private TextView tvMovie;
        private TextView tvTagline;
        private TextView tvYear;
        private TextView tvDuration;
        private TextView tvDirector;
        private RatingBar rbMovieRating;
        private TextView tvCast;
        private TextView tvStory;
        private ProgressBar progressBar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login_screen);

            // Showing and Enabling clicks on the Home/Up button
            if(getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }

            // setting up text views and stuff
            //setUpUIViews();

            // recovering data from MainActivity, sent via intent
            Bundle bundle = getIntent().getExtras();
            if(bundle != null){
                String json = bundle.getString("movieModel"); // getting the model from MainActivity send via extras
                HomePageGetters movieModel = new Gson().fromJson(json, HomePageGetters.class);

                // Then later, when you want to display image

                tvMovie.setText(movieModel.getMovie());
                tvTagline.setText(movieModel.getTagline());
                tvYear.setText("Year: " + movieModel.getYear());
                tvDuration.setText("Duration:" + movieModel.getDuration());
                tvDirector.setText("Director:" + movieModel.getDirector());

                // rating bar
                rbMovieRating.setRating(movieModel.getRating() / 2);

                StringBuffer stringBuffer = new StringBuffer();
                for(HomePageGetters.Cast cast : movieModel.getCastList()){
                    stringBuffer.append(cast.getName() + ", ");
                }

                tvCast.setText("Cast:" + stringBuffer);
                tvStory.setText(movieModel.getStory());

            }

        }

        //private void setUpUIViews() {
            //ivMovieIcon = (ImageView)findViewById(R.id.ivIcon);
            //tvMovie = (TextView)findViewById(R.id.tvMovie);
            //tvTagline = (TextView)findViewById(R.id.tvTagline);
            //tvYear = (TextView)findViewById(R.id.tvYear);
            //tvDuration = (TextView)findViewById(R.id.tvDuration);
            //tvDirector = (TextView)findViewById(R.id.tvDirector);
            //rbMovieRating = (RatingBar)findViewById(R.id.rbMovie);
            //tvCast = (TextView)findViewById(R.id.tvCast);
            //tvStory = (TextView)findViewById(R.id.tvStory);
            //progressBar = (ProgressBar)findViewById(R.id.progressBar);
        //}

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            if (id == android.R.id.home) {
                finish();
            }

            return super.onOptionsItemSelected(item);
        }

    }

