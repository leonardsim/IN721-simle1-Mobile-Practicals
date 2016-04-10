package bit.simle1.multiactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create reference to button
        Button btnChangeA = (Button) findViewById(R.id.btnChngA);
        btnChangeA.setOnClickListener(new btnClickHandler());
    }

    public class btnClickHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent changeActivityIntent = new Intent(MainActivity.this, ActivityB.class);
            startActivity(changeActivityIntent);
        }
    }
}
