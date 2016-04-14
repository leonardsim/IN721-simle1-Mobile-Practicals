package bit.simle1.topartistimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create button reference and set listener
        Button btnShow = (Button) findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new SetArtistImageHandler());
    }

    public class SetArtistImageHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {

        }
    }
}
