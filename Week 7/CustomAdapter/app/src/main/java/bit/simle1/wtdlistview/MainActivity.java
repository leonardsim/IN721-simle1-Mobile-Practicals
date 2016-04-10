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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpDunedinGroupList();

        ListView dunedinGroupListView = (ListView) findViewById(R.id.listViewDunedin);
        dunedinGroupListView.setOnItemClickListener(new DunedinGroupNavListClickHandler());
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
}
