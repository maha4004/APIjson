package com.example.apijson;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LogIn extends AppCompatActivity {

    private String URLline = "https://itcsvc.kku.edu.sa/KKU_MobileAppWS/v1/auth/checkUserIdAndPassword";
    private EditText etUname, etPass;
    private Button btn;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String key_TEXT = "text";
    public static final String key_TEXT1= "text1";
    private String username;
    private String password;
    private String result;
    private JSONObject jsonObject;
    private String userName;
    private String userEmail;
    private String userPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log_in);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        etUname = findViewById(R.id.etusername);
        etPass = findViewById(R.id.etpassword);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        

   }
    private void loginUser(){
          username = etUname.getText().toString().trim();
          password = etPass.getText().toString().trim();
        JSONObject js = new JSONObject();
        try {
            js.put("wsUserName","mobile_apps");
            js.put("wsPassword","Kku_mobile1");
            js.put("userId",username);
            js.put("password",password);
            js.put("domain","KKU.EDU.SA");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, URLline,js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                             result = response.getString("result");
                             jsonObject = response.getJSONObject("userInfo");
                             userName = jsonObject.getString("userName");
                             userEmail = jsonObject.getString("userEmail");
                            userPhone = jsonObject.getString("userPhoneNumber");
                           action();
                           save();


                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),"خطأ في اسم المستخدم أو كلمة المرور !",Toast.LENGTH_LONG).show(); } }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LogIn.this, error.toString(),Toast.LENGTH_LONG).show(); }})
        {
            @Override
            protected Map<String,String> getParams(){
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }};
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public void save(){

        if(result.equals("true")){
            SharedPreferences sp = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
            SharedPreferences.Editor editor= sp.edit();
            editor.putString(key_TEXT,username);
            editor.putString(key_TEXT1,password);
            editor.apply();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("userName",userName);
            intent.putExtra("userEmail",userEmail);
            intent.putExtra("userPhoneNumber",userPhone);
            String title = getIntent().getStringExtra("Value");
            String location = getIntent().getStringExtra("Value2");
            String date = getIntent().getStringExtra("Value3");
            intent.putExtra("Value",title);
            intent.putExtra("Value2",location);
            intent.putExtra("Value3",date);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(),"خطأ في اسم المستخدم أو كلمة المرور !",Toast.LENGTH_LONG).show();
        }
    }

    public void action(){
        SharedPreferences sp=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String check_name=  sp.getString(key_TEXT,"");
        String check_pass= sp.getString(key_TEXT1,"");
        if (check_name.equals(username)&&check_pass.equals(password)){
            Intent intent = new Intent(getApplicationContext(),Details.class);
            startActivity(intent);
           }
           else
               startActivity(new Intent(getApplicationContext(),LogIn.class));
    }
}
