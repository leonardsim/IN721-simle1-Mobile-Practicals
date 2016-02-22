package bit.simle1.intarray;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources resourceResolver = getResources();
        int datesArray[] = resourceResolver.getIntArray(R.array.FebFridays);

        TextView febFriText = (TextView)findViewById(R.id.FebFridayText);

        //String s = "February Fridays are on: ";

        febFriText.setText("February Fridays are on:");

        for (int i = 0; i < datesArray.length; i++)
        {
            String s = String.valueOf(datesArray[i]);
            febFriText.append(" " + s);
        }
        //String dateStr = Arrays.toString(datesArray);

        //s.concat(dateStr);
        //febFriText.setText(dateStr);
    }
}
