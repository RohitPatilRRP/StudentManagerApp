package com.example.dell.studentmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Resource extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);

        Intent intent = getIntent();

        String crop = intent.getStringExtra("crop");

        String cropUrl = "https://www.youtube.com/results?search_query=engineering+computer+science+course";
        mWebView = findViewById(R.id.videoView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());

        mWebView.loadUrl(cropUrl);

    }
}