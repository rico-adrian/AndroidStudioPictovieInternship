package com.pictovie.beta.pictovie;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ricohelvidadrian on 7/24/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        List<String> questions = new ArrayList();
        File file = new File(getFilesDir() + "/" + "content.json");
        // Reading json file from assets folder
        String dir = getFilesDir().getAbsolutePath();
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        String temp="";
        try {
            br = new BufferedReader(new InputStreamReader(getAssets().open(
                    "animals.json")));

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
        TextView output = (TextView) findViewById(R.id.textView1);
        String myjsonstring = sb.toString();
        String strJson="";
        String data = "";
        //String cacheDir = context.getCacheDir();
        //File imageFile = new File(cacheDir, "content.json");
        //FileOutputStream out = new FileOutputStream(imageFile);
        //out.write(imagebuffer, 0, imagebufferlength);
        try {
            // Create the root JSONObject from the JSON string.
            JSONObject jsonRootObject = new JSONObject(myjsonstring);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.getJSONArray("Employee");

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String text = jsonObject.optString("text").toString();
                String name = jsonObject.optString("name").toString();
                float salary = Float.parseFloat(jsonObject.optString("salary").toString());

                data += "Data"+i+" : \n text= "+ text +" \n Name= "+ name +" \n Salary= "+ salary +" \n ";
            }
            output.setText(data);
        } catch (JSONException e) {e.printStackTrace();}
        TextView back = (TextView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(MainActivity.this, HomePage.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                startActivity(intent);
            }
        });
        //songList = (ListView)findViewById(R.id.listView);
        //ArrayList<String> asd=new ArrayList<String>();
        //AsynDataClass jsonAsync = new AsynDataClass();
        //SimpleAdapter simpleAdapter = new SimpleAdapter(this, songList, android.R.layout.activity_list_item, new String[] {"country"}, new int[] {android.R.id.text1});
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                //this,
                //android.R.layout.simple_list_item_1,
                //asd );

        //songList.setAdapter(arrayAdapter);
        //jsonAsync.execute("");

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

// Handle action bar item clicks here. The action bar will

// automatically handle clicks on the Home/Up button, so long

// as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

//noinspection SimplifiableIfStatement

        if (id == R.id.action_settings) {

            return true;

        }
        return super.onOptionsItemSelected(item);

    }

    private class AsynDataClass extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

// TODO Auto-generated method stub

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            HttpPost httpPost = new HttpPost("http://toscanyacademy.com/blog/download/content.json");

            String jsonResult = "";


            System.out.println("Returned Json object " + jsonResult.toString());

            return jsonResult;

        }

        @Override

        protected void onPreExecute() {

// TODO Auto-generated method stub

            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);

            System.out.println("Resulted Value: " + result);

            List<ItemObject> parsedObject = returnParsedJsonObject(result);

            CustomAdapter jsonCustomAdapter = new CustomAdapter(MainActivity.this, parsedObject);

            songList.setAdapter(jsonCustomAdapter);

        }

        private StringBuilder inputStreamToString(InputStream is) {

            String rLine = "";

            StringBuilder answer = new StringBuilder();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            try {

                while ((rLine = br.readLine()) != null) {

                    answer.append(rLine);

                }

            } catch (IOException e) {

// TODO Auto-generated catch block

                e.printStackTrace();

            }

            return answer;

        }

    }
    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }




    private List<ItemObject> returnParsedJsonObject(String result){

        List<ItemObject> jsonObject = new ArrayList<ItemObject>();

        JSONObject resultObject = null;

        JSONArray jsonArray = null;

        ItemObject newItemObject = null;

        try {

            resultObject = new JSONObject(result);

            System.out.println("Testing the water " + resultObject.toString());

            jsonArray = resultObject.optJSONArray("African_Music");

        } catch (JSONException e) {

            e.printStackTrace();

        }

        for(int i = 0; i < jsonArray.length(); i++){

            JSONObject jsonChildNode = null;

            try {

                jsonChildNode = jsonArray.getJSONObject(i);

                String songName = jsonChildNode.getString("song_name");

                String songYear = jsonChildNode.getString("song_id");

                String songAuthor = jsonChildNode.getString("artist_name");

                newItemObject = new ItemObject(songName, songYear, songAuthor);

                jsonObject.add(newItemObject);

            } catch (JSONException e) {

                e.printStackTrace();

            }

        }

        return jsonObject;

    }

}