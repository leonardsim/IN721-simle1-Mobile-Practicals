package bit.simle1.usernamepassing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSettings = (Button) findViewById(R.id.btnSetting);
        btnSettings.setOnClickListener(new DestinationButtonClickHandler());

        retrieveData();
    }

    public class DestinationButtonClickHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent changeActivityIntent = new Intent(MainActivity.this, SettingsActivity.class);

            startActivity(changeActivityIntent);
        }
    }

    public void retrieveData()
    {
        // Fetch intent
        Intent launchIntent = getIntent();

        // Retrieve the data via its key
        String username = launchIntent.getStringExtra("userNameKey");

        // Use the data
        TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvUsername.setText(username);
    }
}
