package com.example.webscrapinggeo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SliderView sliderView;
    private ParseAdaptor adapter;
    private ArrayList<ParseItem> parseItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sliderView = findViewById(R.id.imageSlider);
        adapter = new ParseAdaptor(parseItems,this);

        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        Content content = new Content();
        content.execute();
    }
    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
          }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {

//            try {
//                String url = "https://www.geo.tv/category/pakistan";
//
//                Document doc = Jsoup.connect(url).get();
//
//               // Elements data = doc.select("div.pic");
//                Elements data= doc.select("li.border-box");
//                int size = data.size();
//                Log.d("doc", "doc: "+doc);
//                Log.d("data", "data: "+data);
//                Log.d("size", ""+size);
//                for (int i = 0; i < size; i++) {
//                    String imgUrl = data.select("div.pic")
//                            .select("img")
//                            .eq(i)
//                            .attr("src");
//
//                    String title = data.select("div.pic")
//                            .select("img")
//                            .eq(i)
//                            .attr("title");
//
//
//                   String detailUrl = data.select("li.border-box")
//                            .select("a")
//                            .eq(i)
//                            .attr("href");
//
//                    parseItems.add(new ParseItem(imgUrl, title, detailUrl));
//                    Log.d("items", "img: " + imgUrl + " . title: " + title+"Detail Url: "+detailUrl);
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            try {
                String url = "https://www.dawnnews.tv/latest-news";

                Document doc = Jsoup.connect(url).get();

                // Elements data = doc.select("div.pic");
                Elements data= doc.select("article.box");
                int size = data.size();
                Log.d("doc", "doc: "+doc);
                Log.d("data", "data: "+data);
                Log.d("size", ""+size);
                for (int i = 0; i < 15; i++) {
                    String imgUrl = data.select("div.media__item")
                            .select("img")
                            .eq(i)
                            .attr("src");

                    String title = data.select("h2.story__title")
                            .select("a.story__link")
                            .eq(i)
                            .text();

                    String detailUrl = data.select("h2.story__title")
                            .select("a")
                            .eq(i)
                            .attr("href");

                    parseItems.add(new ParseItem(imgUrl, title, detailUrl));
                    Log.d("items", "img: " + imgUrl + " . title: " + title+"Detail Url: "+detailUrl);
               }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}