package com.har_asha.linearalgebra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by manikaran on 14/2/17.
 */

public class DetailsFragment extends Fragment {

    private WebView webView;
    private View view;
    private ProgressBar progressBar;
    public static DetailsFragment getInstance(int pos , int option) {
        Bundle args = new Bundle();
        args.putInt("pos" , pos);
        args.putInt("option" ,option);
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(args);
        return detailsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.text , container , false);
        Bundle args = getArguments();
        int pos = (int) args.get("pos");
        int option = args.getInt("option");
        webView = (WebView) v.findViewById(R.id.webView);
        view = v.findViewById(R.id.space);
        progressBar = (ProgressBar) v.findViewById(R.id.wheel);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String path="file:///android_asset/";
        webView.setWebViewClient(new MyWebViewClient());
        if(option == 1) {
            path += "product/";
            if(pos == 0)
                webView.loadUrl(path + "basic.html");
            else if(pos == 1)
                webView.loadUrl(path + "properties.html");
            else if(pos == 2)
                webView.loadUrl(path + "Example1.html");
            else if(pos == 3)
                webView.loadUrl(path + "Example2.html");
            else if(pos == 4)
                webView.loadUrl(path + "Example3.html");
        }
        else {
            if(pos == 0)
                webView.loadUrl(path + "matrix1.html");
            else if(pos == 1)
                webView.loadUrl(path + "operations.html");
            else if(pos == 2)
                webView.loadUrl(path + "typesOfMatrix.html");
            else if(pos == 3)
                webView.loadUrl(path + "transpose.html");
            else if(pos == 4)
                webView.loadUrl(path + "symmetric.html");
        }
        return v;
    }

    public class MyWebViewClient extends WebViewClient {
        public MyWebViewClient() {
            super();
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            view.loadUrl(url);
        }

        @Override
        public void onPageFinished(WebView v, String url) {
            super.onPageFinished(v, url);
            progressBar.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }

    }
}
