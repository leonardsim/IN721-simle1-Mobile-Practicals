package bit.simle1.currentlocationv2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Placement currentLocation;

    double lat;
    double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentLocation = new Placement();

        // Create button reference and set listener
        Button btnTeleport = (Button) findViewById(R.id.btnTeleport);
        btnTeleport.setOnClickListener(new SetLocationHandler());
    }

    // Event Handlers
    public class CustomLocationListener implements LocationListener
    {

        @Override
        public void onLocationChanged(Location location) {
            lat = location.getLatitude();
            lng = location.getLongitude();

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    public class SetLocationHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            AsyncDisplayNearestCityAlways APIThread = new AsyncDisplayNearestCityAlways();
            APIThread.execute();
        }
    }

    public class AsyncDisplayNearestCityAlways extends AsyncTask<Void, Void, Placement>
    {
        // Declare progress dialog
        ProgressDialog pd;

        @Override
        protected void onPreExecute()
        {
            // Instantiate Progress Dialog
            pd = new ProgressDialog(MainActivity.this);

            // Will display progress dialog message
            pd = ProgressDialog.show(MainActivity.this, "Searching For Closest City", "Please wait....");
        }

        @Override
        protected Placement doInBackground(Void... params) {

            // Set a default empty value
            String stringData = null;

            String checkCity = getCityInfo(stringData);

            // While it's empty, randomize the Longitude and Latitude
            while (checkCity == null)
            {

                // URL that will display the location in JSON format
                String urlString = "http://www.geoplugin.net/extras/location.gp?"
                        + "lat=" + lat + "&"
                        + "long=" + lng + "&"
                        + "format=json";

                // Update the stringData with the new location
                stringData = getStringDataFromURL(urlString);

                checkCity = getCityInfo(stringData);
            }

            // Set the property with the city info
            currentLocation.setClosestCity(checkCity);

            String[] cCity = checkCity.split(",");

            // Get the image URL and use it to get the bitmap
            String imageURL = getImageURL(cCity[0]);

            // If the imageURL is equal to "No such photo" then set the flag to be false
            // else, set the city image property with the bitmap and set the flag to be true
            if (imageURL.equals("No such photo") || imageURL == null)
            {
                currentLocation.setImageURLFlag(false);
            }
            else
            {
                currentLocation.setImageURLFlag(true);

                Bitmap cityImage = getBitmapImage(imageURL);

                currentLocation.setCityImage(cityImage);
            }

            return currentLocation;
        }

        @Override
        protected void onPostExecute(Placement l) {

            if (pd.isShowing())
            {
                pd.dismiss();
            }

            // Set the textviews for Longitude and Latitude
            setLongLatVal();

            // Set the textview for current city
            TextView tvCity = (TextView) findViewById(R.id.tvCity);
            tvCity.setText("Closest City: " + l.getClosestCity());

            // Create reference for image view and text view
            ImageView ivLocationImage = (ImageView) findViewById(R.id.ivLocationImage);
            TextView tvFeedback = (TextView) findViewById(R.id.tvFeedback);

            // If the flag is true, then set the image
            // else set the text to give feedback that there are no available pictures
            if (l.isImageURLFlag() == true)
            {
                tvFeedback.setText("");
                ivLocationImage.setImageBitmap(l.getCityImage());
            }
            else
            {
                ivLocationImage.setImageResource(android.R.color.transparent);
                tvFeedback.setText("This city has no images in Flickr");
            }

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

        // Set the text with the double values
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
        String closestCity = null;
        String cityName;
        String countryCode;

        try {

            if (stringData == null)
            {
                return null;
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

    private String getImageURL(String cityName)
    {
        String imageURL = "";

        String flickrJSONURL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&"
                + "api_key=5f55323ed742a6d1f89c2448fa59eb6f&"
                + "tags=" + cityName
                + "&format=json&nojsoncallback=1";

        // Grab the raw JSON string
        String flickrJSONString = getStringDataFromURL(flickrJSONURL);

        try {

            // Convert the raw data to JSON Object
            JSONObject flickrData = new JSONObject(flickrJSONString);

            // Grab the value part of the data (Object called photos)
            JSONObject flickrObject = flickrData.getJSONObject("photos");

            // Grab the value part of the data (Array called photo)
            JSONArray flickrArray = flickrObject.getJSONArray("photo");

            if (flickrArray.length() > 0)
            {
                // Get first Flickr photo object
                JSONObject firstFlickrPhoto = flickrArray.getJSONObject(0);

                // Get the farm id
                String farmID = firstFlickrPhoto.getString("farm");

                // Get the server id
                String serverID = firstFlickrPhoto.getString("server");

                // Get the photo id
                String photoID = firstFlickrPhoto.getString("id");

                // Get the secret value
                String secret = firstFlickrPhoto.getString("secret");

                // Form the url for the image
                imageURL = "https://farm" + farmID + ".staticflickr.com/" + serverID + "/" + photoID + "_" + secret + ".jpg";
            }
            else
            {
                imageURL = "No such photo";
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return imageURL;
    }

    private Bitmap getBitmapImage(String imageURL)
    {
        Bitmap cityImage = null;

        try {

            // Convert URL string to URLObject
            URL URLObject = new URL(imageURL);

            // Create HttpURLConnection object via openConnection method of URLObject
            HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();

            // Send the URL
            connection.connect();

            // If it doesn't return 200, no data has been received
            int responseCode = connection.getResponseCode();

            if (responseCode == 200)
            {
                // Get an InputStream from connection
                InputStream inputStream = connection.getInputStream();

                //Converts the inputStream
                cityImage = BitmapFactory.decodeStream(inputStream);
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return cityImage;
    }
}
