package bit.simle1.musicschool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RadioGroup rdoGrpMusic = (RadioGroup) findViewById(R.id.rdGrpMusic);

    }

    // Create inner class
    public class btnConfirmClick implements  View.OnClickListener{

        @Override
        public void onClick(View v) {
            // Create boolean to be sued as checked
            boolean checked = ((RadioButton) (v)).isChecked();

            int viewID = v.getId();
            TextView txtConf = (TextView) findViewById(viewID);

            // Checks which radio button was clicked
            switch(v.getId()) {
                case R.id.rdoAccordion:
                    if (checked)
            }
        }
    }
}
