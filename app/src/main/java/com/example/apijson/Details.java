package com.example.apijson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {
TextView textView,textTitle,textLocation,textDate;
ImageView imageView;
String body,title,location,date;
String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        textView = (TextView)findViewById(R.id.body);
        textTitle = (TextView)findViewById(R.id.title);
        textLocation=(TextView)findViewById(R.id.location);
        imageView = (ImageView) findViewById(R.id.imageView);
        textDate= (TextView)findViewById(R.id.date);
        title=getIntent().getStringExtra("title");
        location=getIntent().getStringExtra("field_location");
        body=getIntent().getStringExtra("body");
        date=getIntent().getStringExtra("field_date");
      image= getIntent().getStringExtra("field_logo");
     // imageView.setImageResource(Integer.parseInt(String.valueOf(image)));
      Picasso.get().load(image).into(imageView);
        textView.setText(body);
        textTitle.setText(title);
        textLocation.setText(location);
        textDate.setText(date);
        Button button = (Button) findViewById(R.id.ticket);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Details.this,Ticket.class);
               title = textTitle.getText().toString();
               location=textLocation.getText().toString();

           //    String name = getIntent().getStringExtra("value0");
                String name=getIntent().getStringExtra("userName");
                intent.putExtra("userName",name);
                String email = getIntent().getStringExtra("userEmail");
                intent.putExtra("userEmail",email);
                String phone = getIntent().getStringExtra("userPhoneNumber");
                intent.putExtra("userPhoneNumber",phone);
                intent.putExtra("Value",title);
                intent.putExtra("Value3",date);
                intent.putExtra("Value2",location);
                startActivity(intent);
            }
        });
    }
}
