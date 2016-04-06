package bit.simle1.citycountrydatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Declare database
    SQLiteDatabase citiesDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create Database
        citiesDB = openOrCreateDatabase("citiesDB", MODE_PRIVATE, null);

        // Create city table and populate it
        createCityTable();
        populateTable();

        // Populates the spinner with country name
        populateSpinner();

        //Create button reference and set listener
        Button btnShow = (Button) findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new SetCitiesClickHandler());
    }

    //Event Handler
    public class SetCitiesClickHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            // Create spinner reference
            Spinner spnCountries = (Spinner) findViewById(R.id.spnCountries);

            // Save the country name depending on what was selected from the spinner
            String selectedCountry = spnCountries.getSelectedItem().toString();

            // Gets cities name
            String[] cities = getCities(selectedCountry);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, cities);

            // Set the city name to be on the  list view
            ListView lvCities = (ListView) findViewById(R.id.lvCities);
            lvCities.setAdapter(adapter);
        }
    }

    //Methods
    private void createCityTable()
    {
        // Drops the city table
        String dropQuery = "DROP TABLE IF EXISTS tblCity";
        citiesDB.execSQL(dropQuery);

        // Create Table
        String createQuery = "CREATE TABLE IF NOT EXISTS tblCity(" +
                            "cityID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "cityName TEXT NOT NULL, " +
                            "countryName TEXT NOT NULL);";

        citiesDB.execSQL(createQuery);
    }

    private void populateTable()
    {
        // Insert records
        citiesDB.execSQL("INSERT INTO tblCity VALUES(null, 'Amsterdam', 'Netherlands')");
        citiesDB.execSQL("INSERT INTO tblCity VALUES(null, 'Berlin', 'Germany')");
        citiesDB.execSQL("INSERT INTO tblCity VALUES(null, 'Cairo', 'Egypt')");
        citiesDB.execSQL("INSERT INTO tblCity VALUES(null, 'Duendin', 'New Zealand')");
    }


    private void populateSpinner()
    {
        String[] countryArray = getCountries();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, countryArray);

        Spinner spnCountries = (Spinner) findViewById(R.id.spnCountries);
        spnCountries.setAdapter(adapter);
    }

    private String[] getCountries()
    {
        // Obtain all unique country name
        String selectQuery = "SELECT DISTINCT countryName FROM tblCity";
        Cursor recordSet = citiesDB.rawQuery(selectQuery, null);

        // Gets the number of record
        int recordCount = recordSet.getCount();

        // Declare array and set the array size
        String[] displayCountryArray = new String[recordCount];

        // Get the column index of countryName
        int countryNameIndex = recordSet.getColumnIndex("countryName");

        // Move to the first of the record
        recordSet.moveToFirst();

        for (int r = 0; r < recordCount; r++)
        {
            // Saved country name
            displayCountryArray[r] = recordSet.getString(countryNameIndex);

            // Move to the next record
            recordSet.moveToNext();
        }

        // Return the country name array
        return displayCountryArray;
    }

    private String[] getCities(String selectedCountry)
    {
        // Obtain all unique country name
        String selectQuery = "SELECT DISTINCT cityName FROM tblCity WHERE countryName = " + "\"" + selectedCountry + "\"";
        Cursor recordSet = citiesDB.rawQuery(selectQuery, null);

        // Gets the number of record
        int recordCount = recordSet.getCount();

        // Declare array and set the array size
        String[] displayCityArray = new String[recordCount];

        // Get the column index of cityName
        int cityNameIndex = recordSet.getColumnIndex("cityName");

        // Move to the first of the record
        recordSet.moveToFirst();

        for (int r = 0; r < recordCount; r++)
        {
            // Saved city name
            displayCityArray[r] = recordSet.getString(cityNameIndex);

            // Move to the next record
            recordSet.moveToNext();
        }

        // Return the country name array
        return displayCityArray;
    }
}
