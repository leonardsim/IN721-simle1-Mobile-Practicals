package bit.simle1.languagetrainer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.sql.Ref;

/**
 * Created by Asus on 29/3/2016.
 */
public class QuizImageFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        // LayoutInflater translate the layout into a collection of controls (View) and returns the VIew
        View fragmentView = inflater.inflate(R.layout.quiz_image_fragment, container, false);

        return fragmentView;
    }


}
