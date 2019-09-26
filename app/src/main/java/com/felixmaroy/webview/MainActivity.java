package com.felixmaroy.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
//import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private WebView mywebView;
    ProgressBar progressBar;
    String url = "https://felixmaroy.com"; //Modifier ce lien par le vôtre

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ajouter ces deux lignes pour affichage à plein écran
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //Rendre visible le ProgressBar
        //getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);


        mywebView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);

//        mywebView.setWebChromeClient(new WebChromeClient() {
//            public void onProgressChanged(WebView view, int progress)
//            {
//                //Afficher un texte pendant le chargement de la page...
//                setTitle("Chargement en cours...");
//                setProgress(progress * 100); //Fait disparaîre le texte après le chargement de la page
//
//                // Afficher le nom de l'application après le chargement complet de la page
//                if(progress == 100)
//                    setTitle(R.string.app_name);
//            }
//        });

//        mywebView.setWebViewClient(new WebViewClient());
        mywebView.setWebViewClient(new notreWebView()); //Je viens d'extendre la fonction WebViewClient
        mywebView.getSettings().setJavaScriptEnabled(true);
        mywebView.getSettings().setBuiltInZoomControls(true);
        mywebView.getSettings().setDisplayZoomControls(false);
        mywebView.loadUrl(url);

    }

    public  class notreWebView extends WebViewClient{

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);

            progressBar.setVisibility(View.GONE);
        }

    }


    @Override
    public void onBackPressed() {
        if(mywebView.canGoBack ()){
            mywebView.goBack ();
        }else{
            super.onBackPressed ();

        }

    }
}
