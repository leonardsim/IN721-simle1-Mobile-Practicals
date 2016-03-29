package bit.simle1.languagetrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

public class  QuizActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Set the fragment

    }


    public void setQuizImage()
    {
        //Attributes
        int[] images = new int[]
                {
                        R.drawable.das_auto, R.drawable.das_haus, R.drawable.das_schaf, R.drawable.der_apfel, R.drawable.der_baum,
                        R.drawable.der_stuhl, R.drawable.die_ente, R.drawable.die_hexe, R.drawable.die_kuh, R.drawable.die_milch,
                        R.drawable.die_strasse
                };

        // Get ImageView & TextView
        setContentView(R.layout.quiz_image_fragment);
        ImageView imageQuiz = (ImageView) findViewById(R.id.imgQuiz);
        TextView txtNoun = (TextView) findViewById(R.id.txtNoun);

        // Get random number between 0 and images.length -1
        Random rand = new Random();
        int imageIndex = rand.nextInt(images.length);

        // Set image
        imageQuiz.setBackgroundResource(images[imageIndex]);

        // Set textview
        switch(imageIndex)
        {
            case 0:
                txtNoun.setText("Auto (Car)");
                break;
            case 1:
                txtNoun.setText("Haus (House)");
                break;
            case 2:
                txtNoun.setText("Schaf (Sheep)");
                break;
            case 3:
                txtNoun.setText("Apfel (Apple)");
                break;
            case 4:
                txtNoun.setText("Stuhl (Chair)");
                break;
            case 5:
                txtNoun.setText("Baum (Tree)");
                break;
            case 6:
                txtNoun.setText("Ente (Duck)");
                break;
            case 7:
                txtNoun.setText("Hexe (Witch)");
                break;
            case 8:
                txtNoun.setText("Kuh (Cow)");
                break;
            case 9:
                txtNoun.setText("Milch (Milk)");
                break;
            case 10:
                txtNoun.setText("Strasse (Street)");
                break;
        }
    }
}
