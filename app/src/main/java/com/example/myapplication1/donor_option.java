package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class donor_option extends AppCompatActivity {

    ImageView nb,nd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_option);
        nb=(ImageView)findViewById(R.id.nearestblood);
        nd=(ImageView)findViewById(R.id.notifydonor);

        //database connection
       final String Url="http://192.168.10.7/checkconnection.php";

        final RequestQueue queue;
       queue=Volley.newRequestQueue(this);
        nb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              StringRequest stringRequest=new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Toast.makeText(getApplicationContext(),"Response from server"+response,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(),"Error"+error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(stringRequest);

                 Intent intent = new Intent(donor_option.this, bloodbank_location.class);
                startActivity(intent);
            }
        });

                nd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(donor_option.this, donor_registration_form.class);
                        startActivity(intent);
                    }
                });
            }
        }
