package bit.simle1.simplefileio;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> cityNameArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityNameArray = new ArrayList<String>();

        // Create reference to button and set listener
        Button btnFill = (Button) findViewById(R.id.btnFill);
        btnFill.setOnClickListener(new SetListViewCitiesHandler());
    }

    //Event Handler
    public class SetListViewCitiesHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            fillList();

            ArrayAdapter<String> citiesAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, cityNameArray);

            ListView lvCitites = (ListView) findViewById(R.id.lvCitites);
            lvCitites.setAdapter(citiesAdapter);
        }
    }

    //Method
    public void fillList()
    {
        String assetFileName = "MalaysianCities.txt";

        AssetManager am = getAssets();

        try {

            InputStream is = am.open(assetFileName);
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(ir);

            String newCity;
            while ((newCity = br.readLine()) != null)
            {
                cityNameArray.add(newCity);
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
