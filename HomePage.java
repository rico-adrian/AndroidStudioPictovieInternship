package com.pictovie.beta.pictovie;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonWriter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.pictovie.beta.pictovie.R.layout.home_page_screen;
import static com.pictovie.beta.pictovie.R.mipmap.sidebar;

/*
  * Homepage Screen for Pictovie. This class extends AppCompatActivity
  * The layout content for this class is based on home_page_screen.xml
  * The titlebar layout uses title_bar.xml
  * This page has clickable image such as sidebar and plus image.
  * Sidebar will link the page into menu_sidebar_screen.xml, menu sidebar screen has
  * some options to go into home, profile, activity, vr mode, settings, and logout page.
  * and plus will link the page into new_title_bar.xml to add new pictures or posts
  * Methods getSupportActionBar() is to set the title bar and window.setStatusBarColor is to change the
  * very top stuffs(battery percentage,LTE)
*/

public class HomePage extends AppCompatActivity {
    // Within which the entire activity is enclosed
    DrawerLayout mDrawerLayout;
    private final String URL_TO_HIT = "C:\\Users\\ricohelvidadrian\\AndroidStudioProjects\\Pictovie\\src\\mobile\\src\\androidTest\\build\\tmp\\compileDebugJavaWithJavac\\emptySourcePathRef\\test.json";
    private static int RESULT_LOAD_IMAGE = 1;
    // ListView represents Navigation Drawer
    ListView mDrawerList;
    TextView txt;
    //ListView lvMovies;
    private ProgressDialog dialog;

    // ActionBarDrawerToggle indicates the presence of Navigation Drawer in the action bar
    ActionBarDrawerToggle mDrawerToggle;

    // Title of the action bar
    String mTitle = "";
    String staticObject = "{\"name1\":\"~Jason Michael\",\"minago2\":\"2 min ago\",\"notice\":\"I notice her!\"}";
    private List<? extends Map<String,?>> countryList;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(home_page_screen);
        mTitle = (String) getTitle();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_bar);
        Window window = this.getWindow();
        Resources res = getResources();
        //AsyncHTTPClient client;

        //String text = String.format(res.getString(R.string.nav_notifications),username,home_page_screen);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00dbde")));
        ImageView sidebar = (ImageView) findViewById(R.id.sidebar);
        final ListView mListView=(ListView) findViewById(R.id.listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String)mListView.getItemAtPosition(position);
                Intent intent = new Intent(HomePage.this,MainActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
        TextView test=(TextView) findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                final Intent u = new Intent(HomePage.this,MainActivity.class);
                HomePage.this.startActivity(u);
            }
        });
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        String temp="";
        try {
            br = new BufferedReader(new InputStreamReader(getAssets().open(
                    "content.json")));

            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close(); // stop reading
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        TextView output = (TextView) findViewById(R.id.fourtytwo);
        TextView timeago = (TextView) findViewById(R.id.minago2);
        String myjsonstring = sb.toString();
        String strJson="";
        String data = "";
        String time="";
        //String cacheDir = context.getCacheDir();
        //File imageFile = new File(cacheDir, "content.json");
        //FileOutputStream out = new FileOutputStream(imageFile);
        //out.write(imagebuffer, 0, imagebufferlength);
        try {
            // Create the root JSONObject from the JSON string.
            JSONObject jsonRootObject = new JSONObject(myjsonstring);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.getJSONArray("Postdetails");

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //Collections.sort(jsonValues);
                String text = jsonObject.optString("number_of_likes").toString();
                String minute = jsonObject.optString("min_ago").toString();
                //float salary = Float.parseFloat(jsonObject.optString("salary").toString());
                time +=minute+"\n"+"\n";
                data += text+"\n"+"\n";

            }

            output.setText(data);
            timeago.setText(time);
        } catch (JSONException e) {e.printStackTrace();}
        final Intent l = new Intent(HomePage.this, MenuSidebarScreen.class);
             sidebar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    setContentView(R.layout.menu_sidebar_screen);
                    HomePage.this.startActivity(l);
                    try {
                        asdsd(staticObject);
                        JSONObject jsonObj = new JSONObject(getIntent().getStringExtra("product"));
                        Intent i = new Intent(HomePage.this, MenuSidebarScreen.class);
                        //l.putExtra("name1", jsonObj.getJSONObject("name1").opt("val").toString());
                        //l.putExtra("minago2", jsonObj.getJSONObject("minago2").opt("val").toString());
                        //l.putExtra("notice", jsonObj.getJSONObject("notice").opt("val").toString());
                        //i.putExtra("mRating", jsonObj.getJSONObject("MPAA").getJSONObject("Rating").opt("val").toString());
                        //i.putExtra("mActors", jsonObj.getJSONObject("Actors").opt("val").toString());
                        //i.putExtra("mImage", jsonObj.getJSONObject("Image").opt("val").toString());
                        startActivity(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


        LinearLayout rootLayout = new LinearLayout(getApplicationContext());
        txt = new TextView(getApplicationContext());
        rootLayout.addView(txt);
        //setContentView(rootLayout);
        // Set the text and call the connect function.
        txt.setText("Connecting...");
        //new JSONTask().execute(URL_TO_HIT);
        //call the method to run the data retreival
        //txt.setText(getServerData(KEY_121));



        ImageView buttonLoadImage = (ImageView) findViewById(R.id.plus);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(HomePage.this,NewForPlusScreen.class);
                //Intent i = new Intent(
                        //Intent.ACTION_PICK,
                        //android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                //startActivityForResult(i, RESULT_LOAD_IMAGE);
                HomePage.this.startActivity(i);
            }
        });


        // Getting reference to the DrawerLayout
        //mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        // Setting DrawerToggle on DrawerLayout
        //mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Creating an ArrayAdapter to add items to the listview mDrawerList
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getBaseContext(),
                R.layout.drawer_list_item,
                getResources().getStringArray(R.array.nav_item_activity_titles)
        );



        // Setting item click listener for the listview mDrawerList
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {

                // Getting an array of rivers
                String[] rivers = getResources().getStringArray(R.array.nav_item_activity_titles);

                //Currently selected river
                mTitle = rivers[position];
                // Creating a Bundle object
                Bundle data = new Bundle();

                // Setting the index of the currently selected item of mDrawerList
                data.putInt("position", position);
                //rFragment.setArguments(data);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                //ft.replace(R.id.content_frame, rFragment);
                ft.commit();
                //mDrawerLayout.closeDrawer(mDrawerList);
            }
        });


}






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.plus);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }



    }


    void buildObject()
    {
        try
        {
            JSONObject obj = new JSONObject(staticObject);
            String x = obj.get("name1").toString() + " " + obj.get("minago2").toString() + " has " + obj.getInt("notice") + " Android phones.";
            setStatus(x);
        }
        catch (JSONException je)
        {
            setStatus("Error occured " + je.getMessage());
        }
    }

    void setStatus(String x)
    {
        TextView tv = (TextView) findViewById(R.id.name1);
        tv.setText(x);
    }
    void buildObjectFromFile()
    {
        try
        {
            String x = "";
            InputStream is = this.getResources().openRawResource(R.raw.jsontwitter);
            byte [] buffer = new byte[is.available()];
            while (is.read(buffer) != -1);
            String json = new String(buffer);
            JSONObject obj = new JSONObject(json);
            x = obj.getString("firstname") + " " + obj.getString("lastname") + "\n";
            x += obj.getString("occupation") + "\n";

            JSONObject interview =  obj.getJSONObject("interview");
            x += "Interview source:" + interview.getString("source")  + "\n";

            JSONArray questions = interview.getJSONArray("questions");
            x += "Number of questions: " + questions.length()  + "\n\n";

            int i;
            for (i=0;i<questions.length();i++)
            {
                JSONObject qa = questions.getJSONObject(i);
                x += "------------\n";
                x += "Q" + (i+1) + ". " + qa.getString("Question") + "\n\n";
                x += "A" + (i+1) + ". " + qa.getString("Answer") + "\n";
            }
            setStatus(x);
        }
        catch (Exception je)
        {
            setStatus("Error w/file: " + je.getMessage());
        }
    }
    public static void asdsd(String addr) throws Exception
    {
        // build a URL
        String s = "http://maps.google.com/maps/api/geocode/json?" +
                "sensor=false&addres=";
        s += URLEncoder.encode(addr, "UTF-8");
        URL url = new URL(s);

        String asd = "{ \"name1\": \"Alice\", \"age\": 20 }";
        JSONObject abc = new JSONObject(asd);
        String n = abc.getString("name1");
        int a = abc.getInt("age");
        System.out.println(n + " " + a);  // prints "Alice 20"
        JSONObject asdd = new JSONObject(" .... ");
        String pageName = asdd.getJSONObject("pageInfo").getString("pageName");

        JSONArray arr = asdd.getJSONArray("posts");
        for (int i = 0; i < arr.length(); i++)
        {

            String post_id = arr.getJSONObject(i).getString("post_id");
            JSONObject json_data = arr.getJSONObject(i);
            Log.i("log_tag","id: "+json_data.getString("post_id")+
                    ", name: "+abc.getString("name")+
                    ", sex: "+json_data.getInt("sex")+
                    ", birthyear: "+json_data.getInt("birthyear"));
        }


        // read from the URL
        Scanner scan = new Scanner(url.openStream());
        String str = new String();
        while (scan.hasNext())
            str += scan.nextLine();
        scan.close();
        JSONObject name = new JSONObject("{\"name\": \"John\"}");

        System.out.println(name.getString("name")); //John
        // build a JSON object
        JSONObject obj = new JSONObject(str);
        if (! obj.getString("status").equals("OK"))
            return;

        // get the first result
        JSONObject res = obj.getJSONArray("results").getJSONObject(0);
        System.out.println(res.getString("formatted_address"));
        JSONObject loc =
                res.getJSONObject("geometry").getJSONObject("location");
        System.out.println("lat: " + loc.getDouble("lat") +
                ", lng: " + loc.getDouble("lng"));
    }

    public class JSONTask extends AsyncTask<String,String, List<HomePageGetters> > {

        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        protected List<HomePageGetters> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("movies");

                List<HomePageGetters> movieModelList = new ArrayList<>();

                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    /**
                     * below single line of code from Gson saves you from writing the json parsing yourself
                     * which is commented below
                     */
                    HomePageGetters details = gson.fromJson(finalObject.toString(), HomePageGetters.class);
                    details.setMovie(finalObject.getString("movie"));
                    details.setYear(finalObject.getInt("year"));
                    details.setRating((float) finalObject.getDouble("rating"));
                    details.setDirector(finalObject.getString("director"));

                    details.setDuration(finalObject.getString("duration"));
                    details.setTagline(finalObject.getString("tagline"));
                    details.setImage(finalObject.getString("image"));
                    details.setStory(finalObject.getString("story"));

                    List<HomePageGetters.Cast> castList = new ArrayList<>();
                    for (int j = 0; j < finalObject.getJSONArray("cast").length(); j++) {
                        HomePageGetters.Cast cast = new HomePageGetters.Cast();
                        cast.setName(finalObject.getJSONArray("cast").getJSONObject(j).getString("name"));
                        castList.add(cast);
                    }
                    details.setCastList(castList);
                    // adding the final object in the list
                    movieModelList.add(details);
                }
                return movieModelList;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        protected void onPostExecute(final List<HomePageGetters> result) {
            super.onPostExecute(result);
            dialog.dismiss();
            if(result != null) {
                //MovieAdapter adapter = new MovieAdapter(getApplicationContext(), R.layout.home_page_screen, result);
                //lvMovies.setAdapter(adapter);
                Button lvMovies = (Button) findViewById(R.id.test);
                lvMovies.setOnClickListener(new View.OnClickListener() {  // list item click opens a new detailed activity

                    public void onClick(View v) {
                        //HomePageGetters movieModel = result.get(position); // getting the model
                        //Intent intent = new Intent(HomePage.this, DetailActivity.class);
                        //intent.putExtra("movieModel", new Gson().toJson(movieModel)); // converting model json into string type and sending it via intent
                        //startActivity(intent);
                        new JSONTask().execute();
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public class MovieAdapter extends ArrayAdapter{

        private List<HomePageGetters> movieModelList;
        private int resource;
        private LayoutInflater inflater;
        public MovieAdapter(Context context, int resource, List<HomePageGetters> objects) {
            super(context, resource, objects);
            movieModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }


        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if(convertView == null){
                holder = new ViewHolder();
                convertView = inflater.inflate(resource, null);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            //final ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar);

            // Then later, when you want to display image
            final ViewHolder finalHolder = holder;
            //ImageLoader.getInstance().displayImage(movieModelList.get(position).getImage(), holder.ivMovieIcon, new ImageLoadingListener() {

            //});

            holder.tvMovie.setText(movieModelList.get(position).getMovie());
            holder.tvTagline.setText(movieModelList.get(position).getTagline());
            holder.tvYear.setText("Year: " + movieModelList.get(position).getYear());
            holder.tvDuration.setText("Duration:" + movieModelList.get(position).getDuration());
            holder.tvDirector.setText("Director:" + movieModelList.get(position).getDirector());

            // rating bar
            //holder.rbMovieRating.setRating(movieModelList.get(position).getRating()/2);

            StringBuffer stringBuffer = new StringBuffer();
            for(HomePageGetters.Cast cast : movieModelList.get(position).getCastList()){
                stringBuffer.append(cast.getName() + ", ");
            }

            holder.tvCast.setText("Cast:" + stringBuffer);
            holder.tvStory.setText(movieModelList.get(position).getStory());
            return convertView;
        }


        class ViewHolder{
            private ImageView ivMovieIcon;
            private TextView tvMovie;
            private TextView tvTagline;
            private TextView tvYear;
            private TextView tvDuration;
            private TextView tvDirector;
            //private RatingBar rbMovieRating;
            private TextView tvCast;
            private TextView tvStory;
        }

    }
    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("test.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

}







