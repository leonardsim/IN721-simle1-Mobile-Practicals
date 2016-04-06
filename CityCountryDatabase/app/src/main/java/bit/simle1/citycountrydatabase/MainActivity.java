package bit.simle1.citycountrydatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
    }

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
}
