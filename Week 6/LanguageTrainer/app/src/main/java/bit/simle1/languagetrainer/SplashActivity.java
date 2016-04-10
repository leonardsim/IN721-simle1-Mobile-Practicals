package bit.simle1.languagetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Create button reference and the listener
        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new btnClickHandler());
    }


    public class btnClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            Intent quizIntent = new Intent(SplashActivity.this, QuizActivity.class);
            startActivity(quizIntent);
        }
    }
}
