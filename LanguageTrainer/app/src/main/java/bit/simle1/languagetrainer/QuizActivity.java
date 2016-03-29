package bit.simle1.languagetrainer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

public class  QuizActivity extends AppCompatActivity {

    //Declare a list of question
    List<Question> questionList;

    //Declare fragment and fragment manager
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

        //Set the fragment
        dynamicFragment = new QuizImageFragment();
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, dynamicFragment);
        ft.commit();

    }

    
}
