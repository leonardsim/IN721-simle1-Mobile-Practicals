package bit.simle1.wtdnavdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupNavDraw();

        ListView dunedinGroupDrawList = (ListView) findViewById(R.id.lv_drawer);
        dunedinGroupDrawList.setOnItemClickListener(new DunedinGroupNavListClickHandler());
    }

    //Event Handlers
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

    //Methods
    public void setupNavDraw()
    {
        String[] groups = {"Activities", "Shopping", "Dining", "Services"};

        ArrayAdapter<String> dunedinGroupAdapter = new ArrayAdapter<String>(this, R.layout.dunedin_group_list_item, groups);

        ListView dunedinGroupListView = (ListView) findViewById(R.id.lv_drawer);

        dunedinGroupListView.setAdapter(dunedinGroupAdapter);
    }
}
