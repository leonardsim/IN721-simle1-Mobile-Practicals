package bit.simle1.displaytopartists;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        }

        @Override
        protected void onPostExecute(String fetchedString)
        {

        }
    }
}
