package bit.simle1.standupanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {


    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.imageView);

        Button btnAnimate = (Button) findViewById(R.id.btnAnimate);
        btnAnimate.setOnClickListener(new setStandUpImageHandler());
    }

    public class setStandUpImageHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            YoYo.with(Techniques.StandUp).duration(5000).playOn(iv);
        }
    }
}
