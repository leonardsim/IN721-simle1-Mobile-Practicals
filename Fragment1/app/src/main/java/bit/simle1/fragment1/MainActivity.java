package bit.simle1.fragment1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain references from both buttons
        Button btnImage = (Button) findViewById(R.id.btnImage);
        Button btnList = (Button) findViewById(R.id.btnList);

        // Button creates the handler on click
        btnImage.setOnClickListener(new onClickBtnImgHandler());
    }

    public class onClickBtnImgHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // Create instance of image fragment class
            Fragment dynamicFragment =  new ShowImage();
            // Fragment Manager used to replace the placeholder with the Fragment
            FragmentManager fm = getFragmentManager();

            // Begin transaction by obtaining FragmentTransaction object
            FragmentTransaction ft = fm.beginTransaction();

            //Replaces the fragment_container with the dynamicFragment object
            ft.replace(R.id.fragment_container, dynamicFragment);

            //Commits the changes
            ft.commit();
        }
    }
}
