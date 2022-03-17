package edu.uic.project3a2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class RestaurantsFragment extends Fragment {

    ListView listViewRestaurant;
    ListView listViewAttractions;
    int indexSelected = -1;

    /**
     * View Model with Live Data
     * */
    MyViewModel myViewModel;

    public RestaurantsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_restaurants, container, false);
        listViewRestaurant = rootView.findViewById(R.id.listRestaurants);

        String list[]
                = { "The Purple Pig Restaurant", "Alinea",
                "Oriole", "Girl & The Goat",
                "The Berghoff Restaurant"};

        listViewRestaurant.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> arr = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_checked,list);
        listViewRestaurant.setAdapter(arr);

        myViewModel = new ViewModelProvider(getActivity()).get(MyViewModel.class);
        myViewModel.init();

        listViewRestaurant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), list[position], Toast.LENGTH_SHORT).show();

                if(indexSelected != position){
                    indexSelected = position;
                    myViewModel.sendLinkRestaurant(position);

                }
            }
        });

        // If an item has been selected, set its checked state
        if (-1 != indexSelected) {
            listViewRestaurant.setItemChecked(indexSelected, true);

            //viewItem.setBackgroundColor(Color.GREEN);
        }

        return rootView;
    }
}