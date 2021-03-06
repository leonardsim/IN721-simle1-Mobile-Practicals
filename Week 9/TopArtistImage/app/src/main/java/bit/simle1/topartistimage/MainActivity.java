package bit.simle1.topartistimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create button reference and set listener
        Button btnShow = (Button) findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new SetArtistImageHandler());
    }

    // Event Handler
    public class SetArtistImageHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            AsyncAPIShowRawJSON APIThread = new AsyncAPIShowRawJSON();
            APIThread.execute();
        }
    }

    //Create inner class AsyncTask
    public class AsyncAPIShowRawJSON extends AsyncTask<Void, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(Void... params) {

            // URL that will display the top artist in JSON format
            String urlBitmap= "http://ws.audioscrobbler.com/2.0/?"
                    + "method=chart.getTopArtists&"
                    + "limit=1&"
                    + "api_key=5cff6bd4a02a240bbfef15567f21c45d&"
                    + "format=json";

            String stringDataFromUrl = getStringDataFromURL(urlBitmap);
            String imageURL = getImageURL(stringDataFromUrl);
            Bitmap artistImage = getBitmapImage(imageURL);

            return artistImage;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            // Get image view reference and set the image
            ImageView ivArtist = (ImageView) findViewById(R.id.ivArtist);
            ivArtist.setImageBitmap(bitmap);
        }
    }


    //Methods
    public String getStringDataFromURL(String url)
    {
        String data = "";

        try {

            // Convert URL string to URLObject
            URL URLObject = new URL(url);

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

                data = stringBuilder.toString();
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return data;
    }


    public String getImageURL(String JSONString)
    {
        String imageURL = "";
        try {
            // Convert the raw data to JSON Object
            JSONObject artistData = new JSONObject(JSONString);

            // Grab the value part of the data: key-value pair (Object called artists)
            JSONObject artistObject = artistData.getJSONObject("artists");

            // Grab the value part of the data: key-value pair (Object called artists)
            JSONArray artistArray = artistObject.getJSONArray("artist");

            // Get the first artist
            JSONObject topArtist = artistArray.getJSONObject(0);

            // Grab the value part of the data: key-value pair (Object called image)
            JSONArray imageArray = topArtist.getJSONArray("image");

            JSONObject largeArtistObject = imageArray.getJSONObject(2);

            imageURL = largeArtistObject.getString("#text");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return imageURL;
    }

    public Bitmap getBitmapImage(String imageURL)
    {
        Bitmap artistImage = null;

        try {
            // Convert URL string to URLObject
            URL URLObject = new URL(imageURL);

            // Create HttpURLConnection object via openConnection method of URLObject
            HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();

            // Send the IRL
            connection.connect();

            // If it doesn't return 200, no data has been received
            int responseCode = connection.getResponseCode();

            if (responseCode == 200)
            {
                // Get an InputStream from connection
                InputStream inputStream = connection.getInputStream();

                // Converts the inputStream into a Bitmap
                artistImage = BitmapFactory.decodeStream(inputStream);
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return artistImage;
    }
}
