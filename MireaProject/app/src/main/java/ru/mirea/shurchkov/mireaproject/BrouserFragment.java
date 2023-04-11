package ru.mirea.shurchkov.mireaproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BrouserFragment extends Fragment {

    private WebView webView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_brouser, container, false);
        webView = view.findViewById(R.id.webBrowser);
        webView.setWebViewClient(new WebViewClient() );
        webView.loadUrl("https://www.avito.ru/");
        return view;
    }
}