package bit.simle1.wtdlistview;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivitiesActivity extends AppCompatActivity {

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
}
