package edu.uic.project3a2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity implements callbackMethod{

    AttractionsFragment attractionsFragment;
    RestaurantsFragment restaurantsFragment;
    FragmentTransaction fragmentTransaction;

    FragmentContainerView fragmentContainerView;

    /**
     * Variables to manage broadcast and receiver*/
    MyReceiver receiver;
    MyReceiver2 receiver2;
    IntentFilter filter1;
    IntentFilter filter2;

    private final static String SHOW_ATTRACTIONS = "edu.uic.project3a2.Attractions";
    private final static String SHOW_RESTAURANTS = "edu.uic.project3a2.Restaurants";


    /** Frame layout that holds webView*/
    FrameLayout frameWebView;
    fragment_webView fragment_webView;

    /**
     * View Model with Live Data
     * */
    MyViewModel myViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        receiver = new MyReceiver(this);
        receiver2 = new MyReceiver2(this);
        filter1 = new IntentFilter(SHOW_ATTRACTIONS);
        filter2 = new IntentFilter(SHOW_RESTAURANTS);
        registerReceiver(receiver, filter1);
        registerReceiver(receiver2, filter2);


            attractionsFragment = new AttractionsFragment();
            restaurantsFragment = new RestaurantsFragment();
            fragmentContainerView = findViewById(R.id.container_fragment);

            fragmentTransaction =  getSupportFragmentManager().beginTransaction();

            fragmentTransaction.add(R.id.container_fragment, restaurantsFragment,"RT");
            fragmentTransaction.add(R.id.container_fragment, attractionsFragment,"TA");

            /**
             * FrameLayout and webview
             * */

            frameWebView = findViewById(R.id.container_web);
            fragment_webView = new fragment_webView();

            fragmentTransaction =  getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container_web, fragment_webView,"WB").commit();

            //attractionsFragment.listViewAttractions.setItemChecked(0, true);

            /**
             * View Model with Live Data
             * */

            myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
            myViewModel.init();
            myViewModel.getLink().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    frameWebView.setVisibility(View.VISIBLE);
                    //Toast.makeText(getApplicationContext(), "Hola de MainActivity "+s, Toast.LENGTH_SHORT).show();
                }
            });



    }// end of onCreate




    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        frameWebView.setVisibility(View.GONE);
        int index = attractionsFragment.indexSelected;
        int index2 = restaurantsFragment.indexSelected;

        if(-1 != index){
            attractionsFragment.listViewAttractions.setItemChecked(index, false);
        }

        if(-1 != index2){
            restaurantsFragment.listViewRestaurant.setItemChecked(index2, false);
        }
    }

    public void onDestroy() {

        super.onDestroy();
        unregisterReceiver(receiver);
        unregisterReceiver(receiver2);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();

        int id = item.getItemId();
        if(id == R.id.item_attractions){
            Toast.makeText(this, "Attractions", Toast.LENGTH_SHORT).show();
            transaction.replace(R.id.container_fragment, attractionsFragment, "TA");

        }else if(id == R.id.item_restaurants){
            Toast.makeText(this, "Restaurants", Toast.LENGTH_SHORT).show();
            transaction.replace(R.id.container_fragment, restaurantsFragment, "RT");
        }

        // Commit the transaction
        transaction.commit();


        return super.onOptionsItemSelected(item);
    }

    /**Method called in receiver 1*/
    @Override
    public void transactionAttraction() {

        AttractionsFragment myFragment = (AttractionsFragment)getSupportFragmentManager().findFragmentByTag("TA");

        if (myFragment == null || !myFragment.isVisible()) {
            // add your code here
            FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container_fragment, attractionsFragment,"TA").commitAllowingStateLoss();
        }
    }

    /**Method called in receiver 2*/
    @Override
    public void transactionRestaurant() {

        RestaurantsFragment myFragment = (RestaurantsFragment)getSupportFragmentManager().findFragmentByTag("RT");

        if (myFragment == null || !myFragment.isVisible()) {
            // add your code here
            FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container_fragment, restaurantsFragment, "RT").commitAllowingStateLoss();
        }
    }
}