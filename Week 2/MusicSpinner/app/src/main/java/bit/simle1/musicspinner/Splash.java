package bit.simle1.musicspinner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread background = new Thread() {
            public void run() {
                try {
                    //Thread will sleep for 5 seconds
                    sleep(5 * 1000);

                    // After 5 seconds, it will redirect to the main activity
                    Intent splashIntent = new Intent(Splash.this, MainActivity.class);

                    // Start the activity
                    startActivity(splashIntent);

                    // End activity
                    finish();
                }
                catch (InterruptedException e) {
                }

            }
        };

        // Start thread
        background.start();
    }
}
