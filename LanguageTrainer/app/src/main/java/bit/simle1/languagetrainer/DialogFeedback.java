package bit.simle1.languagetrainer;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Asus on 30/3/2016.
 */
public class DialogFeedback extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
    {
        // Retrieve arguement from passed bundle
        String feedback = getArguments().getString("Feedback");

        // Set title of dialog
        getDialog().setTitle("You are...");

        // Inflate xml
        View v = inflater.inflate(R.layout.dialog_fragment, container);

        // Create textView reference and set it
        TextView tvFeedback = (TextView) v.findViewById(R.id.tvDialogFeedback);
        tvFeedback.setText(feedback);

        // Create Button reference and set it
        Button btnNext = (Button) v.findViewById(R.id.btnDialogFeedback);
        btnNext.setOnClickListener(new onClickBtnNextHandler());

        return v;
    }

    public class onClickBtnNextHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            QuizActivity quizActivity = (QuizActivity) getActivity();
            quizActivity.nextQuestion();
        }
    }
}
