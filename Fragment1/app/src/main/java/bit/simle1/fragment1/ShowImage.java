package bit.simle1.fragment1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bit.simle1.fragment1.R;

/**
 * Created by simle1 on 11/03/2016.
 */
public class ShowImage extends Fragment {

    // LayoutInflater inflater: a system object that knows how to translate XML into controls
    // ViewGroup container: Activity who is instantiating the fragment
    // Bundle savedInstanceState: state information if this Fragment is coming back from Pause or Stop
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        // LayoutInflater translate the layout into a collection of controls (View) and returns the VIew
        View fragmentView = inflater.inflate(R.layout.show_image, container, false);

        return fragmentView;
    }
}
