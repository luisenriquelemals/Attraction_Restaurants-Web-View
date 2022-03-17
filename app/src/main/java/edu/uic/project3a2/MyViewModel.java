package edu.uic.project3a2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {

    List<String>  attractionsLinks;
    List<String>  restaurantsLinks;
    private MutableLiveData<String> link;

    public void init(){
        if(link == null){
            link = new MutableLiveData<>();
            loadAttractionsLinks();
            loadRestaurantsLinks();
        }

    }

    public void sendLinkAttraction(int position) {
        link.setValue(attractionsLinks.get(position));
    }

    public void sendLinkRestaurant(int position) {
        link.setValue(restaurantsLinks.get(position));
    }

    public LiveData<String> getLink() {
        return link;
    }


    /**Save all links*/
    private void loadAttractionsLinks() {
        attractionsLinks = new ArrayList<>();
        attractionsLinks.add("https://www.lpzoo.org/");
        attractionsLinks.add("https://navypier.org/");
        attractionsLinks.add("https://www.msichicago.org/");
        attractionsLinks.add("https://www.artic.edu/");
        attractionsLinks.add("https://360chicago.com/tilt");
    }

    private void loadRestaurantsLinks() {
        restaurantsLinks = new ArrayList<>();
        restaurantsLinks.add("https://thepurplepigchicago.com/");
        restaurantsLinks.add("https://www.alinearestaurant.com/");
        restaurantsLinks.add("https://www.oriolechicago.com/");
        restaurantsLinks.add("https://girlandthegoat.com/");
        restaurantsLinks.add("https://www.theberghoff.com/");
    }




}
