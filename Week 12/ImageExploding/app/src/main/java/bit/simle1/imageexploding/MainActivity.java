package bit.simle1.imageexploding;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.easyandroidanimations.library.Animation;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.ExplodeAnimation;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView)findViewById(R.id.imageView);
        Button btnExplode = (Button)findViewById(0R.id.btnExplode);
        btnExplode.setOnClickListener(new setImageToExplodeHandler());
    }

    public class setImageToExplodeHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            ExplodeAnimation ea = new ExplodeAnimation(iv);
            ea.setExplodeMatrix(ExplodeAnimation.MATRIX_2X2);
            ea.setInterpolator(new DecelerateInterpolator());
            ea.setDuration(5000);
            ea.animate();
        }
    }
}
