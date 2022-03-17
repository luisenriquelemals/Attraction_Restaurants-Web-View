package edu.uic.project3a2;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class AttractionsFragment extends Fragment {

    ListView listViewAttractions;
    int indexSelected = -1;

    /**
     * View Model with Live Data
     * */
    MyViewModel myViewModel;


    public AttractionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_attractions, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        listViewAttractions = rootView.findViewById(R.id.listAttractions);
        listViewAttractions = view.findViewById(R.id.listAttractions);


        String list[] = { " The Lincoln Park Zoo", "Navy Pier",
                "The Museum of Science and Industry", "The Art Institute",
                "The TILT!"};
        listViewAttractions.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> arr = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_checked,list);
        listViewAttractions.setAdapter(arr);
        //listViewAttractions.setItemChecked(2, true);

        myViewModel = new ViewModelProvider(getActivity()).get(MyViewModel.class);
        /**
         * When a item is selected in my Attraction List  */

        listViewAttractions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), list[position], Toast.LENGTH_SHORT).show();
                if(indexSelected != position){
                    indexSelected = position;
                    myViewModel.sendLinkAttraction(position);

                }

                //Toast.makeText(getActivity(), "Result: "+myViewModel.getLink(), Toast.LENGTH_SHORT).show();

            }
        });

        // If an item has been selected, set its checked state
        if (-1 != indexSelected) {
            listViewAttractions.setItemChecked(indexSelected, true);
            //viewItem.setBackgroundColor(Color.GREEN);
        }


    }


}