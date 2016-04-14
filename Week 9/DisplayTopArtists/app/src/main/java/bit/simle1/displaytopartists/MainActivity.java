package bit.simle1.displaytopartists;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Artist[] artists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create reference for button and set listener
        Button btnFill = (Button) findViewById(R.id.btnFill);
        btnFill.setOnClickListener(new SetListArtistHandler());
    }

    // Event Handler
    public class SetListArtistHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            AsyncAPIShowRawJSON APIThread = new AsyncAPIShowRawJSON();
            APIThread.execute();
        }
    }

    // Create inner class AsyncTask
    public class AsyncAPIShowRawJSON extends AsyncTask<Void,Void,String>
    {
        @Override
        protected String doInBackground(Void... params)
        {
            // String that will return the JSON data
            String JSONString = null;

            try {

                // URL that will display all the top artists in JSON format
                String urlString = "http://ws.audioscrobbler.com/2.0/?"
                        + "method=chart.getTopArtists&limit=20&"
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

                    while ((responseString = bufferedReader.readLine()) != null)
                    {
                        stringBuilder = stringBuilder.append(responseString);
                    }

                    // Get the string from the stringBuilder
                    JSONString = stringBuilder.toString();
                }

            } catch (java.io.IOException e) {
                e.printStackTrace();
            }

            return JSONString;
        }

        @Override
        protected void onPostExecute(String fetchedString)
        {
            // Declare and instantiate ArrayList<String>
            ArrayList<String> arrayList = new ArrayList<>();

            try {

                // Convert the raw data to JSON Object
                JSONObject artistData = new JSONObject(fetchedString);

                // Grab the value part of the data: key-value pair (Object called artists)
                JSONObject artistObject = artistData.getJSONObject("artists");

                // Grab the value part of the data: key-value pair (Array called artist)
                JSONArray artistArray = artistObject.getJSONArray("artist");

                // Loop through the artistArray
                for (int i = 0; i < artistArray.length(); i++)
                {
                    // Get an element from the array
                    JSONObject currentArtistObject = artistArray.getJSONObject(i);

                    // Access the artist name and listener value
                    String currentArtistName = currentArtistObject.getString("name");
                    String currentArtistListeners = currentArtistObject.getString("listeners");

                    artists[i] = new Artist(currentArtistName, currentArtistListeners);

                    // Concat both the current artists values and add them to the array list
                    String nameListener = currentArtistName + ", \t" + currentArtistListeners;
                    arrayList.add(nameListener);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Create adapter
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.custom_items, arrayList);

            // Bind adapter to list view
            ListView lvArtists = (ListView) findViewById(R.id.lvArtists);
            lvArtists.setAdapter(adapter);
        }

        public class CustomAdapter extends ArrayAdapter<Artist>
        {
            //Constructor
            public CustomAdapter(Context context, int resource, Artist[] objects) {
                super(context, resource, objects);
            }

            // Override the parent method
            // Make the method return a View filled up from the data in itemsArray[position]
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                //Get an inflater from the Activity
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);

                // Inflate the custom view
                View customView = inflater.inflate(R.layout.custom_items, parent, false);

                // Grab references to its controls
                TextView tvName = (TextView) findViewById(R.id.tvName);
                TextView tvListener = (TextView) findViewById(R.id.tvListener);

                // Get the current input item from the array
                Artist currentArtist = getItem(position);

                // Use the instance data to initialise the View control
                tvName.setText(currentArtist.name);
                tvListener.setText(currentArtist.listener);

                //Return the view
                return customView;
            }
        }

        
    }
}
