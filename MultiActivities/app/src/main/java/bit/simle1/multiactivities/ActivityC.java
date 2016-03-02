package bit.simle1.multiactivities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_c);

        Button btnChangeC = (Button) findViewById(R.id.btnChngC);
        btnChangeC.setOnClickListener(new btnClickHandler());
    }

    public class btnClickHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            Uri memes = Uri.parse("https://niceme.me/");

            Intent memeIntent = new Intent(Intent.ACTION_VIEW, memes);

            startActivity(memeIntent);
        }
    }
}
