package bit.simle1.textcolourpassing;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get reference to button
        Button btnColour = (Button) findViewById(R.id.btnColour);
        btnColour.setOnClickListener(new RetrieveDataOnClickHandler());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // Use the requestCode
        if ((requestCode == 0) && (resultCode == Activity.RESULT_OK))
        {
            // Fetch result
            int colour = data.getIntExtra("txtColour", 0);

            //Set textView's colour
            TextView mainText = (TextView)findViewById(R.id.tvMain);

            mainText.setTextColor(colour);
        }
    }

    public class RetrieveDataOnClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            Intent changeActivity = new Intent(MainActivity.this, SettingsActivity.class);

            startActivityForResult(changeActivity, 0);
        }
    }
}
