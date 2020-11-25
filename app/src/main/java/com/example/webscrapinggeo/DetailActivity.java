package com.example.webscrapinggeo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity {


    private ImageView imageView;
    private TextView titleTExtView, detailTextView;
    private String detailString;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView = findViewById(R.id.imageView);
        titleTExtView = findViewById(R.id.textView);
       // detailTextView = findViewById(R.id.detailTextView);
        webView= findViewById(R.id.webview);
        String detailUrl = getIntent().getStringExtra("detailUrl");
        Toast.makeText(this, "This is "+detailUrl, Toast.LENGTH_SHORT).show();

        titleTExtView.setText(getIntent().getStringExtra("title"));
        Picasso.get().load(getIntent().getStringExtra("image")).into(imageView);
        webView.loadUrl(detailUrl);
//        Content content = new Content();
//        content.execute();
   }
//    private class Content extends AsyncTask<Void,Void,Void> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            detailTextView.setText(detailString);
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            try {
//                String baseUrl = "https://www.geo.tv/category/pakistan";
//                String detailUrl = getIntent().getStringExtra("detailUrl");
//
//                String url = baseUrl + detailUrl;
//
//                Document doc = Jsoup.connect(url).get();
//
//                Elements data = doc.select("p");
//
//                detailString = data.select("class")
//                        .text();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
}