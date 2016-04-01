package bit.simle1.wtdlistview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ActivitiesActivity extends AppCompatActivity {

    //Declare the class
    FTTD[] funArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
    }

    public class CustomAdapter extends ArrayAdapter<FTTD> {

        //Constructor
        public CustomAdapter(Context context, int resource, FTTD[] objects)
        {
            super(context, resource, objects);
        }

        // Override the parent method
        // Make the method return a View filled up from the data in itemsArray[position]
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Get an inflater from the Activity
            LayoutInflater inflater = LayoutInflater.from(ActivitiesActivity.this);

            // Inflate the custom view
            View customView = inflater.inflate(R.layout.custom_view_layout, parent, false);

            // Grab references to its controls
            ImageView ivCustomIcon = (ImageView) customView.findViewById(R.id.ivCustomIcon);
            TextView tvFTTD = (TextView) customView.findViewById(R.id.tvFTTD);

            // Get the current input item (Fun things isntnace) from the array
            FTTD currentItem = getItem(position);

            // Use the instance data to inititalise the View controls
            ivCustomIcon.setImageDrawable(currentItem.placeImage);
            tvFTTD.setText(currentItem.placeName);

            // Return the view
            return customView;

        }
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

    // Sets up the list view (Wont work yet)
    public void setUpCustomList()
    {
        //Create Adapter
        ArrayAdapter<FTTD> fttdAdapter = new ArrayAdapter<FTTD>(this, R.layout.custom_fun_things_layout, funArray);

        //Bind ListView to the Adapter
        ListView lvFunThings = (ListView) findViewById(R.id.lvFunThings);
        lvFunThings.setAdapter(fttdAdapter);
    }
}
