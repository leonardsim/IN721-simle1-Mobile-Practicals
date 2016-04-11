package bit.simle1.jsonpractical;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public String JSONInput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise JSONInput
        JSONInput = readEntireJSON("dunedin_events.json");

        // Create reference for button and set the listener
        Button btnFill = (Button) findViewById(R.id.btnFill);
        btnFill.setOnClickListener(new SetListEventHandler());

        // Create reference for list view and set the listener
        ListView lvEvents = (ListView) findViewById(R.id.lvEvents);
        lvEvents.setOnItemClickListener(new SetListViewClickHandler());
    }

    //Event Handlers
    public class SetListEventHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {

            // Declare ArrayList and initialise
            ArrayList<String> eventDisplayArray = populateArrayList(JSONInput);

            // Create adapter
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, eventDisplayArray);

            // Create ListView reference and bind the adapter to the list
            ListView lvEvents = (ListView) findViewById(R.id.lvEvents);
            lvEvents.setAdapter(adapter);
        }
    }

    public class SetListViewClickHandler implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Call display method
            displayEventDescription(JSONInput, position);
        }
    }

    //Methods
    public String readEntireJSON(String filename) {
        // Get an asset manager
        AssetManager am = getAssets();

        // Initialise JSONInput which will be used to save the JSON data
        String JSONInput = null;

        try {

            // Create an input stream from the JSON file
            InputStream inputStream = am.open(filename);

            // Determine number of bytes in file, and prepare buffer array for read
            int fileSizeInBytes = inputStream.available();
            byte[] JSONBuffer = new byte[fileSizeInBytes];

            // Read the stream into the buffer and close it
            inputStream.read(JSONBuffer);
            inputStream.close();

            // Create a string from the byte[]
            JSONInput = new String(JSONBuffer);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return JSONInput;
    }

    public ArrayList<String> populateArrayList(String input)
    {
        // Declare and instantiate ArrayList
        ArrayList<String> eventDisplayArray = new ArrayList<String>();

        try {

            // Convert file string to JSON Object
            JSONObject eventData = new JSONObject(input);

            // Grab the value part of the data: key-value pair (Which is an object called events)
            JSONObject eventObject = eventData.getJSONObject("events");

            // Grab the value part of the data: key-value pair (Which is an array called event)
            JSONArray eventArray = eventObject.getJSONArray("event");

            // Loop through eventArray
            for (int i = 0; i < eventArray.length(); i++)
            {
                // Get an element from the array
                JSONObject currentEventObject = eventArray.getJSONObject(i);

                // Access the title value
                String currentEventTitle = currentEventObject.getString("title");

                // Add to the list
                eventDisplayArray.add(currentEventTitle);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return eventDisplayArray;
    }

    public void displayEventDescription(String input, int position)
    {

        // Create reference to list view
        ListView lvEvents = (ListView) findViewById(R.id.lvEvents);

        try {

            // Convert file string to JSON Object
            JSONObject eventData = new JSONObject(input);

            // Grab the value part of the data: key-value pair (Which is an object called events)
            JSONObject eventObject = eventData.getJSONObject("events");

            // Grab the value part of the data: key-value pair (Which is an array called event)
            JSONArray eventArray = eventObject.getJSONArray("event");

            // Loop through eventArray
            for (int i = 0; i < eventArray.length(); i++)
            {
                // Get an element from the array
                JSONObject currentEventObject = eventArray.getJSONObject(i);

                // Access the title value
                String currentEventTitle = currentEventObject.getString("title");

                // Checks to see if the title and the item selected in the list view are equal
                if(currentEventTitle.equals(lvEvents.getItemAtPosition(position).toString()))
                {
                    // Access the description value
                    String currentEventDescription = currentEventObject.getString("description");

                    // Call the toast to display the description value
                    Toast.makeText(MainActivity.this, currentEventDescription, Toast.LENGTH_LONG).show();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
