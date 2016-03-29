package bit.simle1.languagetrainer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        retrieveData();

        Button btnCorrect = (Button) findViewById(R.id.btnCorrectNext);
        Button btnIncorrect = (Button) findViewById(R.id.btnIncorrectNext);

        btnCorrect.setOnClickListener(new onClickBtnHandler());
        btnIncorrect.setOnClickListener(new onClickBtnHandler());
    }

    public class onClickBtnHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // Fetch intent
            Intent launchIntent = getIntent();

            // Retrieve the data via key
            int userScore = launchIntent.getIntExtra("userScore", 0);

            if (userScore > 10)
            {
                //Setup intent to move to the results screen
                Intent scoreIntent = new Intent(FeedbackActivity.this,ScoreActivity.class);

                //Load extra data about user score into the intent
                scoreIntent.putExtra("userTotalScore", userScore);

                //Give control to result screen
                startActivity(scoreIntent);
            }
            else
            {
                // Goes back to Quiz Activity if userScore is still less than 10
                Intent quizIntent = new Intent(FeedbackActivity.this, QuizActivity.class);
                startActivity(quizIntent);
            }
        }
    }

    // Retrieves the data from QuizActivity based on keyword and will create the appropriate fragment
    public void retrieveData()
    {
        // Fetch intent
        Intent launchIntent = getIntent();

        // Retrieve the data via key
        boolean userAnswer = launchIntent.getBooleanExtra("userAnswer", true);

        if (userAnswer == true)
        {
            correctFragment();
        }
        else
        {
            incorrectFragment();
        }
    }

    // Replaces the container with the correct fragment
    public void correctFragment()
    {
        // Create instance of correct feedback fragment class
        Fragment dynamicFragment = new CorrectFeedbackFragment();

        // Fragment Manager used to replace the placeholder with Fragment
        FragmentManager fm = getFragmentManager();

        // Begin transaction by obtaining FragmentTransaction object
        FragmentTransaction ft = fm.beginTransaction();

        // Replaces the feedback container with dynamicFragment
        ft.replace(R.id.feedback_container, dynamicFragment);

        // Commits the changes
        ft.commit();
    }

    // Replaces the container with the incorrect fragment
    public void incorrectFragment()
    {
        // Create instance of correct feedback fragment class
        Fragment dynamicFragment = new InCorrectFeedbackFragment();

        // Fragment Manager used to replace the placeholder with Fragment
        FragmentManager fm = getFragmentManager();

        // Begin transaction by obtaining FragmentTransaction object
        FragmentTransaction ft = fm.beginTransaction();

        // Replaces the feedback container with dynamicFragment
        ft.replace(R.id.feedback_container, dynamicFragment);

        // Commits the changes
        ft.commit();
    }
}
