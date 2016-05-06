package bit.simle1.usinglocation;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Declare Location class
    Location currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate Location class
        currentLocation = new Location();

        // Create button reference and set listener
        Button btnTeleport = (Button) findViewById(R.id.btnTeleport);
        btnTeleport.setOnClickListener(new SetLocationHandler());
    }

    // Event Handlers
    public class SetLocationHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            AsyncDisplayNearestCityAlways APIThread = new AsyncDisplayNearestCityAlways();
            APIThread.execute();
        }
    }

    // Async Tasks
    public class AsyncAPIShowRawJSON extends AsyncTask<Void, Void, String>
    {
        @Override
        protected String doInBackground(Void... params) {

            // Produces a random value for both Longitude and Latitude
            currentLocation.GenerateRandomValues();

            // URL that will display the location in JSON format
            String urlString = "http://www.geoplugin.net/extras/location.gp?"
                    + "lat=" + currentLocation.getLatVal() + "&"
                    + "long=" + currentLocation.getLongVal() + "&"
                    + "format=json";

            String stringDataFromURL = getStringDataFromURL(urlString);

            return stringDataFromURL;
        }

        @Override
        protected void onPostExecute(String s) {

            // Save the city info
            String currentCity = getCityInfo(s);

            // Set the textviews for Longitude and Latitude
            setLongLatVal();

            // Set the textview for current city
            TextView tvCity = (TextView) findViewById(R.id.tvCity);
            tvCity.setText(currentCity);
        }
    }

    public class AsyncDisplayNearestCityAlways extends AsyncTask<Void, Void, String>
    {
        @Override
        protected String doInBackground(Void... params) {

            // Set a default empty value
            String stringData = "[[]]";

            // While it's empty, randomize the Longitude and Latitude
            while (stringData.equals("[[]]"))
            {
                // Produces a random value for both Longitude and Latitude
                currentLocation.GenerateRandomValues();

                // URL that will display the location in JSON format
                String urlString = "http://www.geoplugin.net/extras/location.gp?"
                        + "lat=" + currentLocation.getLatVal() + "&"
                        + "long=" + currentLocation.getLongVal() + "&"
                        + "format=json";

                // Update the stringData with the new location
                stringData = getStringDataFromURL(urlString);
            }

            return stringData;
        }

        @Override
        protected void onPostExecute(String s) {

            // Save the city info
            String currentCity = getCityInfo(s);

            // Set the textviews for Longitude and Latitude
            setLongLatVal();

            // Set the textview for current city
            TextView tvCity = (TextView) findViewById(R.id.tvCity);
            tvCity.setText(currentCity);
        }
    }

    // Methods
    private void setLongLatVal()
    {
        // Formats the double to have 3 decimal places
        DecimalFormat precision = new DecimalFormat("0.000");

        // Create textView reference
        TextView tvLng = (TextView) findViewById(R.id.tvLng);
        TextView tvLat = (TextView) findViewById(R.id.tvLat);

        // Set the text with the dobule values
        tvLng.setText(precision.format(currentLocation.getLongVal()));
        tvLat.setText(precision.format(currentLocation.getLatVal()));
    }

    private String getStringDataFromURL(String url)
    {
        String data = "";

        try {

            // Convert URL string to URLObject
            URL URLObject = new URL(url);

            // Create HttpURLConnection object via openConnection method of URLObject
            HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();

            // Send the URL
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

                data = stringBuilder.toString();
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    private String getCityInfo(String stringData)
    {
        String closestCity = "";
        String cityName = "";
        String countryCode = "";

        try {

            if (stringData.equals("[[]]"))
            {
                cityName = "No such City";
                countryCode = "??";
            }
            else
            {
                // Convert string data to JSONObject
                JSONObject cityInfo = new JSONObject(stringData);

                // Access the cityInfo to obtain the place and countryCode
                cityName = cityInfo.getString("geoplugin_place");
                countryCode = cityInfo.getString("geoplugin_countryCode");
            }

            // Concat the values obtained from JSON object
            closestCity = cityName + ", " + countryCode;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return closestCity;
    }
}
