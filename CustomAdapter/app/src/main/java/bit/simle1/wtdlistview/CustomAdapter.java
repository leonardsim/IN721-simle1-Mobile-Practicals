package bit.simle1.wtdlistview;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by simle1 on 1/04/2016.
 */
public class CustomAdapter extends ArrayAdapter<FTTD>{

    //Constructor
    public CustomAdapter(Context context, int resource, FTTD[] objects)
    {
        super(context, resource, objects);
    }

}
