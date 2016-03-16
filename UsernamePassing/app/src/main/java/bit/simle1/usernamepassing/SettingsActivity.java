package bit.simle1.usernamepassing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new PassDataButtonClickHandler());
    }

    public class PassDataButtonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            Intent changeActivityIntent = new Intent(SettingsActivity.this, MainActivity.class);

            EditText userInput = (EditText) findViewById(R.id.editText);
            String username =  (String) userInput.getText().toString();

            changeActivityIntent.putExtra("userNameKey", username);

            startActivity(changeActivityIntent);
        }
    }
}
