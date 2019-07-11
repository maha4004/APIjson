package com.example.apijson;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<items> addItems1;
    Adapter adapterItems;
    private RequestQueue requestQueue;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= (RecyclerView)findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addItems1=new ArrayList<>();
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        LoadData();

    }

    private void LoadData(){
        String url ="https://event.kku.edu.sa/ws/events-ar.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonArray = response.getJSONArray("events");
                            for(int i = 0; i<jsonArray.length();i++){
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                JSONObject jsonObject2 = jsonObject1.getJSONObject("event");
                                String title = jsonObject2.getString("title");
                                String body = jsonObject2.getString("body");
                                String location = jsonObject2.getString("field_location");
                               JSONObject jsonObject3 = jsonObject2.getJSONObject("field_logo");
                                String src = jsonObject3.getString("src");
                               String date = jsonObject2.getString("field_date");

                                items add = new items(title,src,body,location,date);
                                addItems1.add(add);
                            }
                          //  mLayoutManager = new LinearLayoutManager(MainActivity.this);
                          //  recyclerView.setLayoutManager(mLayoutManager);

                            adapterItems = new Adapter(MainActivity.this, addItems1);
                            recyclerView.setAdapter(adapterItems);
                            } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

                    }
                },new Response.ErrorListener(){
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    });
        requestQueue.add(request);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id==R.id.english){
            Intent intent = new Intent(MainActivity.this,EnglishEvents.class);
            String name = getIntent().getStringExtra("userName");
            intent.putExtra("userName",name);
            String email = getIntent().getStringExtra("userEmail");
            intent.putExtra("userEmail",email);
            String mobile = getIntent().getStringExtra("userPhoneNumber");
            intent.putExtra("userPhoneNumber",mobile);
            startActivity(intent);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
