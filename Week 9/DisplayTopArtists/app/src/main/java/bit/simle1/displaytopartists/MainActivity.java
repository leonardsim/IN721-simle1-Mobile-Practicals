package bit.simle1.displaytopartists;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        // Create reference for button and set listener
        Button btnFill = (Button) findViewById(R.id.btnFill);
        btnFill.setOnClickListener(new SetListArtistHandler());
    }

    // Event Handler
    public class SetListArtistHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {

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

        }
    }
}
