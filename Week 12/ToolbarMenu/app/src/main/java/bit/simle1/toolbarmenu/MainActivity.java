package bit.simle1.toolbarmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int count = 0;
    TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCount = (TextView) findViewById(R.id.tvCount);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.custom_menu_list, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int itemID = item.getItemId();
        String itemTitle = (String) item.getTitle();

        switch(itemTitle)
        {
            case "Info":
                Toast.makeText(getApplicationContext(), "This is the info", Toast.LENGTH_SHORT).show();
                break;
            case "Increase":
                count++;
                tvCount.setText("Count: " + count);
                break;
            case "Refresh":
                count = 0;
                tvCount.setText("Count: " + count);
                Toast.makeText(getApplicationContext(), "Count has been restarted", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
