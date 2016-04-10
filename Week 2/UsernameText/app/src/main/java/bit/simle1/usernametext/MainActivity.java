package bit.simle1.usernametext;

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

        EditText userNameText = (EditText) findViewById(R.id.textUserName);

        userNameText.setOnKeyListener(new UserNameKeyHandler());
    }


    public class UserNameKeyHandler implements View.OnKeyListener{

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            EditText userInput = (EditText) findViewById(v.getId());

            String userName = userInput.getText().toString();

            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){

                if (userName.length() == 8){
                    Toast statusToast = Toast.makeText(MainActivity.this, "Thank you " + userName, Toast.LENGTH_LONG);
                    statusToast.show();
                }
                else{
                    Toast statusToast = Toast.makeText(MainActivity.this, "Username must be 8 characters, " + userName, Toast.LENGTH_LONG);
                    statusToast.show();
                }

                return true;
            }

            return false;
        }
    }
}
