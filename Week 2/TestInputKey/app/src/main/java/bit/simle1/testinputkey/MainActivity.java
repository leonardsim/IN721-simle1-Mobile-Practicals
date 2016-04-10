package bit.simle1.testinputkey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText textAt = (EditText)findViewById(R.id.editTextAtSign);

        textAt.setOnKeyListener(new TextAtHandler());
    }

    public class TextAtHandler implements View.OnKeyListener {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event){

            if (event.getAction() == KeyEvent.ACTION_DOWN){

                if (keyCode == KeyEvent.KEYCODE_AT)
                {
                    Toast statusToast = Toast.makeText(MainActivity.this, "Don't Type @!!", Toast.LENGTH_SHORT);

                    statusToast.show();
                }
            }

            return false;
        }
    }
}
