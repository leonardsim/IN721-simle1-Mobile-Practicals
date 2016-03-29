package bit.simle1.languagetrainer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class  QuizActivity extends AppCompatActivity {

    //Declare a list of question
    List<Question> questionList;

    //Declare fragment, fragment manager and fragment transaction
    Fragment dynamicFragment;
    FragmentManager fm;
    FragmentTransaction ft;

    //Attributes
    int totalScore;
    int currentQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Initialise all attributes
        questionList = new ArrayList<>();
        totalScore = 0;
        currentQuiz = 0;

        //Set the fragment to replace the container with the quiz image layout
        dynamicFragment = new QuizImageFragment();
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, dynamicFragment);
        ft.commit();

    }

    public void setQuestion()
    {

    }

}
