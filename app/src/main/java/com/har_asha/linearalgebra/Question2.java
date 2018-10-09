package com.har_asha.linearalgebra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by manikaran on 8/3/17.
 */

public class Question2 extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question1);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        int questionSet = 1;
        if(intent != null) {
            questionSet = intent.getIntExtra("questionSet" , 1);
        }
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        WebView webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String path="file:///android_asset/questionSet/questionSet"+ questionSet +".html";
        webView.loadUrl(path);
        webView.setWebViewClient(new MyWebClient());
    }
    class MyWebClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);
            super.onPageFinished(view, url);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }
        return true;
    }
}
