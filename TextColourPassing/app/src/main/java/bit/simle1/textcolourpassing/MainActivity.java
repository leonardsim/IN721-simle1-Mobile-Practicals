package bit.simle1.textcolourpassing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get reference to button
        Button btnColour = (Button) findViewById(R.id.btnColour);
    }

    public class RetrieveDataOnClickHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            
        }
    }
}
