package com.example.apijson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.String.valueOf;

public class Ticket extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4,ed5,ed6;
String st1,st2,st3,st4,st5,st6;
DatabaseReference databaseReference;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        databaseReference= FirebaseDatabase.getInstance().getReference();
 ed1 = (EditText) findViewById(R.id.name);
 ed2 = (EditText) findViewById(R.id.email);
 ed3 = (EditText) findViewById(R.id.mobile);
 ed4 = (EditText) findViewById(R.id.eventName);
 ed5 = (EditText) findViewById(R.id.location);
 ed6 = (EditText) findViewById(R.id.date);

st1=getIntent().getStringExtra("userName");
ed1.setText(st1);
st2 = getIntent().getStringExtra("userEmail");
ed2.setText(st2);
st3=getIntent().getStringExtra("userPhoneNumber");
ed3.setText(st3);
st4=getIntent().getStringExtra("Value");
ed4.setText(st4);
st5=getIntent().getStringExtra("Value2");
ed5.setText(st5);
st6=getIntent().getStringExtra("Value3");
ed6.setText(st6);

        Button button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
                String to = ed2.getText().toString().trim();
                String subject = ed4.getText().toString().trim();
                String message = "أهلا" + "\t"+ed1.getText().toString().trim()+"\n" +  "تأكيد حجزك لفعالية" + "\n"+ed4.getText().toString().trim()+"\n"
                        + ed5.getText().toString().trim()+"\n"+"بتاريخ"+"\n"+ed6.getText().toString().trim();

                sendMail sm = new sendMail(Ticket.this,to,subject,message);
                sm.execute();


            }
        });
        Button button1 = (Button) findViewById(R.id.btn2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("userName",st1);
                intent.putExtra("userEmail",st2);
                intent.putExtra("userPhoneNumber",st3);
                startActivity(intent);
            }
        });


    }
    public void postData(){
        String userName = ed1.getText().toString().trim();
        String email = ed2.getText().toString().trim();
        String phone = ed3.getText().toString().trim();
        String eventName = ed4.getText().toString().trim();
        String eventPlace = ed4.getText().toString().trim();
        String eventDate = ed5.getText().toString().trim();

        PostTicket postTicket = new PostTicket(userName,email,phone,eventName,eventPlace,eventDate);
        databaseReference.setValue(postTicket);

    }
}
