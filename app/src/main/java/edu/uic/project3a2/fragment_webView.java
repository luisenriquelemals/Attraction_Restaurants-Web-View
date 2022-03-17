package edu.uic.project3a2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class fragment_webView extends Fragment {

    WebView webView;

    /**
     * View Model with Live Data
     * */
    MyViewModel myViewModel;

    public fragment_webView() {
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
        View rootView = inflater.inflate(R.layout.fragment_web_view, container, false);
        webView = rootView.findViewById(R.id.webViewID);

        myViewModel = new ViewModelProvider(getActivity()).get(MyViewModel.class);
        //myViewModel.init();
        myViewModel.getLink().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                webView.loadUrl(s);
            }
        });

        return rootView;
    }


}