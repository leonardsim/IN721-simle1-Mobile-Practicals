package bit.simle1.jsonpractical;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create reference for button and set the listener
        Button btnFill = (Button) findViewById(R.id.btnFill);
        btnFill.setOnClickListener(new SetListEventHandler());
    }

    //Event Handlers
    public class SetListEventHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {

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
}
