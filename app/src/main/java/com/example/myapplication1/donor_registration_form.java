package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

public class donor_registration_form extends AppCompatActivity {

    EditText e1,e2,e3;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_registration_form);

        SharedPreferences shared = getSharedPreferences("user_details", MODE_PRIVATE);
        final String user_phone = (shared.getString("PHONE", ""));
        e1 = (EditText) findViewById(R.id.txtname);
        e2 = (EditText)findViewById(R.id.txtfname);
        e3 = (EditText)findViewById(R.id.txtbg);
        btn = (Button)findViewById(R.id.btn2);
        final RequestQueue queue = Volley.newRequestQueue(this);
        final String urll ="http://192.168.10.7/donor.php";
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name = e1.getText().toString();
                String last_name = e2.getText().toString();
                String bloodgroup = e3.getText().toString();

                String url = urll +"?fname="+first_name+"&lname="+last_name+"&blood_group="+bloodgroup+"&userphone="+user_phone;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(donor_registration_form.this, response, Toast.LENGTH_LONG).show();
                                Intent intent4= new Intent(donor_registration_form.this, bloodbank_location.class);

                                startActivity(intent4);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(stringRequest);
            }
        });
    }


}