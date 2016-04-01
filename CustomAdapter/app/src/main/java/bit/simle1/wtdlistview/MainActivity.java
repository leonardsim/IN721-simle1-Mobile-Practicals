package bit.simle1.wtdlistview;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FTTD[] funArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpDunedinGroupList();

        initialiseDataArray();

        ListView dunedinGroupListView = (ListView) findViewById(R.id.listViewDunedin);
        dunedinGroupListView.setOnItemClickListener(new ListViewWithToastHandler());
        dunedinGroupListView.setOnItemClickListener(new DunedinGroupNavListClickHandler());
    }

    // Event Handlers
    public class ListViewWithToastHandler implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String clickedItemString = (String) parent.getItemAtPosition(position).toString();

            Toast.makeText(MainActivity.this, clickedItemString, Toast.LENGTH_LONG).show();
        }
    }

    public class DunedinGroupNavListClickHandler implements AdapterView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String clickedItem = (String) parent.getItemAtPosition(position).toString();
            Intent goToIntent;

            switch (clickedItem)
            {
                case "Activities":
                    goToIntent = new Intent(MainActivity.this, ActivitiesActivity.class);
                    break;
                case "Shopping":
                    goToIntent = new Intent(MainActivity.this, ShoppingActivity.class);
                    break;
                case "Dining":
                    goToIntent = new Intent(MainActivity.this, DiningActivity.class);
                    break;
                case "Services":
                    goToIntent = new Intent(MainActivity.this, ServicesActivity.class);
                    break;
                default:
                    goToIntent = null;
            }

            if (goToIntent != null)
                startActivity(goToIntent);
        }
    }

    // Method
    public void setUpDunedinGroupList()
    {
        String[] groups = {"Activities", "Shopping", "Dining", "Services"};

        ArrayAdapter<String> dunedinGroupAdapter = new ArrayAdapter<String>( this, R.layout.dunedin_group_list_item, groups);

        ListView dunedinGroupListView = (ListView) findViewById(R.id.listViewDunedin);

        dunedinGroupListView.setAdapter(dunedinGroupAdapter);
    }

    public void initialiseDataArray()
    {
        // Fetch drawables
        Resources resourceMachine = getResources();

        Drawable larnachImage = resourceMachine.getDrawable(R.drawable.larnach_castle);
        Drawable moanaImage = resourceMachine.getDrawable(R.drawable.moana_pool);
        Drawable monarchImage = resourceMachine.getDrawable(R.drawable.monarch);
        Drawable octagonImage = resourceMachine.getDrawable(R.drawable.octagon);
        Drawable olvestonImage = resourceMachine.getDrawable(R.drawable.olveston);
        Drawable peninsulaImage = resourceMachine.getDrawable(R.drawable.peninsula);
        Drawable saltImage = resourceMachine.getDrawable(R.drawable.salt_water_pool);
        Drawable speightsImage = resourceMachine.getDrawable(R.drawable.speights_brewery);
        Drawable kildaImage = resourceMachine.getDrawable(R.drawable.st_kilda_beach);
        Drawable taeriImage = resourceMachine.getDrawable(R.drawable.taeri_gorge_railway);

        // Inititalise the data array
        funArray = new FTTD[10];
        funArray[0] = new FTTD(larnachImage, "Larnach Castle");
        funArray[1] = new FTTD(moanaImage, "Moana Pool");
        funArray[2] = new FTTD(monarchImage, "Monarch Cruise");
        funArray[3] = new FTTD(octagonImage, "Octagon");
        funArray[4] = new FTTD(olvestonImage, "Olveston");
        funArray[5] = new FTTD(peninsulaImage, "Peninsula");
        funArray[6] = new FTTD(saltImage, "Salt Water Pool");
        funArray[7] = new FTTD(speightsImage, "Speights Brewery Factory");
        funArray[8] = new FTTD(kildaImage, "St. Kilda Beach");
        funArray[9] = new FTTD(taeriImage, "Taeri Gorge Railway");
    }
}
