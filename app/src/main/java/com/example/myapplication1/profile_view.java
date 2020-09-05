package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class profile_view extends AppCompatActivity {
    TextView t1,t2,t3,t4;
    String first_name,last_name,blood_group,gender,age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        t1 = (TextView)findViewById(R.id.txt1);
        t2 = (TextView)findViewById(R.id.txt2);
        t3 = (TextView)findViewById(R.id.txt3);
        t4 = (TextView)findViewById(R.id.txt4);

        SharedPreferences shared = getSharedPreferences("user_details", MODE_PRIVATE);
        final String user_phone = (shared.getString("PHONE", ""));

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.10.7/profile.php?userphone="+user_phone;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            profile(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(profile_view.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(stringRequest);

    }


    public void profile(String respnse) {
        try {
            JSONArray jArray = new JSONArray(respnse);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                first_name = json_data.getString("first_name");
                last_name = json_data.getString("last_name");
                gender = json_data.getString("gender");
                age = json_data.getString("age");
                blood_group = json_data.getString("blood_group");

            }

            t1.setText("Full Name : "+first_name+" "+last_name);
            t2.setText("Gender : "+ gender);
            t3.setText("Blood Group : "+blood_group);
            t4.setText("Age : "+ age);
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}