package com.har_asha.linearalgebra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by manikaran on 14/2/17.
 */

public class QuestionFragment extends Fragment implements View.OnClickListener {
    private TextView showAns;
    private static final String SET_TAG = "questionSet";
    private static final String NUM_TAG = "question";
    public static QuestionFragment getInstance(int set , int quest) {
        Bundle bundle = new Bundle();
        bundle.putInt(SET_TAG , set);
        bundle.putInt(NUM_TAG , quest);
        QuestionFragment frag = new QuestionFragment();
        frag.setArguments(bundle);
        return frag;
    }

    private WebView solution;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.webview , container , false);
        Bundle bundle = getArguments();
        String folder , file;
        if(bundle != null) {
            folder = SET_TAG + bundle.getInt(SET_TAG, 1);
            file = NUM_TAG + bundle.getInt(NUM_TAG, 1);
        }
        else {
            folder=SET_TAG+"1";
            file=NUM_TAG+"1";
        }
        WebView webView = (WebView) v.findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String path="file:///android_asset/" + folder +"/" + file + ".html";
        webView.loadUrl(path);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                break;
            case R.id.showAns:
                int visibility = solution.getVisibility();
                if(visibility == View.VISIBLE) {
                    showAns.setText("View Solution");
                    solution.setVisibility(View.GONE);
                }
                else {
                    showAns.setText("Hide Solution");
                    solution.setVisibility(View.VISIBLE);
                    String ans = "Ans. 2 x 4<br><br>Number of rows = 2<br>Number of columns = 4<br>Order of matrix is defined as the (number of rows) x (number of columns). So, order of the given matrix is 2 x 4.";
                    solution.loadData(ans, "text/html", "UTF-8");
                }
                break;
        }
    }
}
