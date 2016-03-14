package bit.simle1.fragment1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Asus on 14/3/2016.
 */
public class ShowList extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        // LayoutInflater translate the layout into a collection of controls (View
        View fragmentView = inflater.inflate(R.layout.show_list, container, false);

        // Get reference to ListView using fragmentView
        ListView fragList = (ListView) fragmentView.findViewById(R.id.listView);

        // Array String
        String[] listArr = {"Ayy", "Bee", "Ceayy", "Dee"};

        // Set adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listArr);

        // Set adapter to the ListView
        fragList.setAdapter(adapter);

        // Return the fragmentView
        return fragmentView;
    }
}
