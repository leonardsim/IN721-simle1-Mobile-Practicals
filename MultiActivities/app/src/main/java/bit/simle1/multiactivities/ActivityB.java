package bit.simle1.multiactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_b);

        // Create reference to button
        Button btnChangeB = (Button) findViewById(R.id.btnChngB);
        btnChangeB.setOnClickListener(new btnClickHandler());
    }

    public class btnClickHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent changeActivityIntent = new Intent(ActivityB.this, ActivityC.class);
            startActivity(changeActivityIntent);
        }
    }
}
