package com.example.apijson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ExampleViewHolder> {


    private Context mContext;
    private List<items> mExampleList;

    public Adapter(Context context, List<items> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.items, viewGroup, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ExampleViewHolder exampleViewHolder, final int i) {
        final items currentItem = mExampleList.get(i);

        String title = currentItem.getTitle();
        String imageUrl = currentItem.getImage();
        String date = currentItem.getDate();

        exampleViewHolder.mTextViewTitle.setText(title);
        exampleViewHolder.date.setText(date);
        Picasso.get().load(imageUrl).into(exampleViewHolder.mImageView);
        exampleViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,Details.class);

                Intent intent1 = ((Activity) mContext).getIntent();
                String name = intent1.getStringExtra("userName");
                String email = intent1.getStringExtra("userEmail");
                String mobile = intent1.getStringExtra("userPhoneNumber");

                intent.putExtra("userEmail",email);
                intent.putExtra("userPhoneNumber",mobile);
                intent.putExtra("userName",name);
                intent.putExtra("field_logo",currentItem.getImage());
                intent.putExtra("title",currentItem.getTitle());
                intent.putExtra("body",currentItem.getBody());
                intent.putExtra("field_location",currentItem.getLocation());
                intent.putExtra("field_date",currentItem.getDate());

                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewTitle,date;
        CardView cardView;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
           date=itemView.findViewById(R.id.dateList);
            mTextViewTitle = itemView.findViewById(R.id.title);
            cardView = itemView.findViewById(R.id.cardView);
        }}
}
