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

        Button btnCorrect = (Button) findViewById(R.id.btnCorrectNext);
        Button btnIncorrect = (Button) findViewById(R.id.btnIncorrectNext);
    }

    public class onClickBtnCorrectHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {

        }
    }

    public void retrieveData()
    {
        // Fetch intent
        Intent launchIntent = getIntent();

        // Retrieve the data via key
        boolean userAnswer = launchIntent.getBooleanExtra("userAnswer", true);

        if (userAnswer == true)
        {

        }
    }

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
