package bit.simle1.languagetrainer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
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

    //Declare Resource
    Resources resources;

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
        resources = getResources();

        //Set the fragment to replace the container with the quiz image layout
        dynamicFragment = new QuizImageFragment();
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, dynamicFragment);
        ft.commit();

    }

    // Adds questions to the questionList
    public void setQuestion()
    {
        // Masculine Article
        questionList.add(new Question("Der", "Apfel (Apple)", resources.getDrawable(R.drawable.der_apfel)));
        questionList.add(new Question("Der", "Baum (Tree)", resources.getDrawable(R.drawable.der_baum)));
        questionList.add(new Question("Der", "Stuhl (Chair)", resources.getDrawable(R.drawable.der_stuhl)));

        // Feminine Article
        questionList.add(new Question("Die", "Ente (Duck)", resources.getDrawable(R.drawable.die_ente)));
        questionList.add(new Question("Die", "Hexe (Witch)", resources.getDrawable(R.drawable.die_hexe)));
        questionList.add(new Question("Die", "Kuh (Cow)", resources.getDrawable(R.drawable.die_kuh)));
        questionList.add(new Question("Die", "Milch (Milk)", resources.getDrawable(R.drawable.die_milch)));
        questionList.add(new Question("Die", "Strasse (Street)", resources.getDrawable(R.drawable.die_strasse)));

        // Neutral Arcticle
        questionList.add(new Question("Das", "Auto (Car)", resources.getDrawable(R.drawable.das_auto)));
        questionList.add(new Question("Das", "Haus (House)", resources.getDrawable(R.drawable.das_haus)));
        questionList.add(new Question("Das", "Schaf (Sheep)", resources.getDrawable(R.drawable.das_schaf)));
    }

}
