package bit.simle1.languagetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Fetch intent
        Intent launchedIntent = getIntent();

        // Retrieve data via key
        int totalScore = launchedIntent.getIntExtra("userTotalScore", 0);

        //Get reference and set textView
        TextView tvScore = (TextView) findViewById(R.id.tvScore);
        tvScore.setText(totalScore + "/11");

        //Get reference and set button
        Button btnAgain = (Button) findViewById(R.id.btnAgain);
        btnAgain.setOnClickListener(new onClickBtnAgainHandler());
    }

    public class onClickBtnAgainHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            Intent splashIntent = new Intent(ScoreActivity.this, SplashActivity.class);
            startActivity(splashIntent);
        }
    }
}
