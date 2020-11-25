package com.example.webscrapinggeo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class ParseAdaptor extends SliderViewAdapter<ParseAdaptor.ViewHolder> {

    private ArrayList<ParseItem> parseItems;
    private Context context;

    public ParseAdaptor(ArrayList<ParseItem> parseItems, Context context) {
        this.parseItems = parseItems;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ParseItem sliderItem = parseItems.get(position);

        viewHolder.textView.setText(sliderItem.getTitle());
        viewHolder.textView.setTextSize(16);
        viewHolder.textView.setTextColor(Color.WHITE);
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getImgUrl())
                .fitCenter()
                .into(viewHolder.imageView);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseItem parseItem = parseItems.get(position);

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("title", parseItem.getTitle());
                intent.putExtra("image", parseItem.getImgUrl());
                intent.putExtra("detailUrl", parseItem.getDetailUrl());
                context.startActivity(intent);
                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });

        ParseItem parseItem = parseItems.get(position);
        viewHolder.textView.setText(parseItem.getTitle());
        Picasso.get().load(parseItem.getImgUrl()).into(viewHolder.imageView);

    }

    @Override
    public int getCount() {
        return parseItems.size();
    }

    public class ViewHolder extends SliderViewAdapter.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.imageview);
            textView = view.findViewById(R.id.textview);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public void setFilter (ArrayList<ParseItem> newList) {
        parseItems = new ArrayList<>();
        parseItems.addAll(newList);
        notifyDataSetChanged();
    }
}
