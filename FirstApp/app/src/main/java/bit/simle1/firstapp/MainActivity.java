package bit.simle1.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView dogTextRandom = (TextView)findViewById(R.id.dogBreedText);

        Random randNum = new Random();
        int dogValue = randNum.nextInt(4);

        switch (dogValue)
        {
            case 0:
                dogTextRandom.setText("Poodle");
                break;
            case 1:
                dogTextRandom.setText("Labrador");
                break;
            case 2:
                dogTextRandom.setText("Shar Pei");
                break;
            case 3:
                dogTextRandom.setText("Newfoundland");
                break;
        }
    }
}
