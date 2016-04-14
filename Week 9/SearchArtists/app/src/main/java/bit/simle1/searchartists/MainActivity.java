package bit.simle1.searchartists;

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
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener();
    }

    //Event Handler
    public class SetListArtistHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {

        }
    }

    // Create inner class AsyncTask
    
}
