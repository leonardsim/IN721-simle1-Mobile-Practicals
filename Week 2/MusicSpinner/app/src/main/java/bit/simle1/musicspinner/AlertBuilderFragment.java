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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setIcon(R.drawable.music);
        builder.setTitle("Do you want to enroll?");
        builder.setPositiveButton("Yes", new YesButtonHandler());
        builder.setNegativeButton("No", new NoButtonHandler());

        Dialog customDialog = builder.create();

        return customDialog;
    }

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
