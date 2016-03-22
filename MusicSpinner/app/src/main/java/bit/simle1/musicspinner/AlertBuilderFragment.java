package bit.simle1.musicspinner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Asus on 22/3/2016.
 */
public class AlertBuilderFragment extends DialogFragment {

    // Empty constructor
    public AlertBuilderFragment(){}

    public class YesButtonHandler implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            MainActivity myActivity = (MainActivity) getActivity();
            myActivity.giveMeMyData(true);
        }
    }

    public class NoButtonHandler implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            MainActivity myActivity = (MainActivity) getActivity();
            myActivity.giveMeMyData(false);
        }
    }
}
