package bit.simle1.searchartists;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Create reference for button and set listener
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new SetListArtistHandler());
    }

    //Event Handler
    public class SetListArtistHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {

            EditText etInput = (EditText) findViewById(R.id.etInput);

            // Create reference to edit text and get the text saved into a string
            String userInput = etInput.getText().toString();

            AsyncAPIShowRawJSON APIThread = new AsyncAPIShowRawJSON();
            APIThread.execute(userInput);
        }
    }

    // Create inner class AsyncTask
    public class AsyncAPIShowRawJSON extends AsyncTask<String,Void,String>
    {
        @Override
        protected String doInBackground(String... params) {

            String JSONString = null;

            try {

                // URL that will display 10 artists similair to what the user typed in JSON format
                // params[0] is the first string input fro mthe arguement
                String urlString = "http://ws.audioscrobbler.com/2.0/?"
                        + "method=artist.getsimilar&artist=" + params[0] + "&limit=10&"
                        + "api_key=5cff6bd4a02a240bbfef15567f21c45d&"
                        + "format=json";


                // Convert URL string to URLObject
                URL URLObject = new URL(urlString);

                // Create HttpURLConnection object via openConnection method of URLObject
                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();

                // Send the IRL
                connection.connect();

                // If it doesn't return 200, no data has been received
                int responseCode = connection.getResponseCode();

                if (responseCode == 200)
                {
                    // Get an InputStream from the HttpURLConnection object and set up a Buffered Reader
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    // Read the input
                    String responseString;
                    StringBuilder stringBuilder = new StringBuilder();

                    while((responseString = bufferedReader.readLine()) != null)
                    {
                        stringBuilder = stringBuilder.append(responseString);
                    }

                    // Get the string from the string builder
                    JSONString = stringBuilder.toString();
                }

            } catch (java.io.IOException e) {
                e.printStackTrace();
            }

            return JSONString;
        }

        @Override
        protected void onPostExecute(String s) {
            // Declare and instantiate ArrayList<String>
            ArrayList<String> arrayList = new ArrayList<String>();

            try {

                // Convert the raw data to JSON Object
                JSONObject artistData = new JSONObject(s);

                // Grab the value part of the data: key-value pair (Object called similarartists)
                JSONObject similarArtistsObject = artistData.getJSONObject("similarartists");

                // Grab the value part of the data: key-value pair (Object called artists)
                JSONArray artistArray = similarArtistsObject.getJSONArray("artist");

                // Loop through artistArray
                for (int i = 0; i < artistArray.length(); i++)
                {
                    // Get an element from the array
                    JSONObject currentArtistObject = artistArray.getJSONObject(i);

                    // Access the artist name
                    String currentArtistName = currentArtistObject.getString("name");

                    arrayList.add(currentArtistName);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Create adapter
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, arrayList);

            // Bind adapter to list view
            ListView lvArtists = (ListView) findViewById(R.id.lvArtist);
            lvArtists.setAdapter(adapter);
        }
    }
}
