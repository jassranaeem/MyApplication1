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

public class register_form extends AppCompatActivity
{

    EditText e1,e2,e3,e4,e5;
    TextView price;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        Intent intent = getIntent();
        Integer total_price = intent.getExtras().getInt("TOTAL_PRICE");
        final String IDs = intent.getExtras().getString("ID");

        SharedPreferences shared = getSharedPreferences("user_details", MODE_PRIVATE);
        final String userphone = (shared.getString("PHONE", ""));

        price = (TextView)findViewById(R.id.price);
        e1 = (EditText) findViewById(R.id.first_name);
        e2 = (EditText)findViewById(R.id.last_name);
        e3 = (EditText)findViewById(R.id.age);
        e4 = (EditText)findViewById(R.id.cnic);
        e5 = (EditText)findViewById(R.id.rwp);
        btn = (Button)findViewById(R.id.btn);
        final RequestQueue queue = Volley.newRequestQueue(this);
        price.setText("Price = "+total_price+" Rs");
        final String urll ="http://192.168.10.7/patient_test_info.php";
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name = e1.getText().toString();
                String last_name = e2.getText().toString();
                String age = e3.getText().toString();
                String cnic = e4.getText().toString();
                String relation = e5.getText().toString();

                String url = urll +"?fname="+first_name+"&lname="+last_name+"&age="+age+"&gender=male&cnic="+cnic+"&relation="+relation+"&userphone="+userphone;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(register_form.this, response, Toast.LENGTH_SHORT).show();
                                Intent intent4= new Intent(register_form.this, person_onway.class);
                                intent4.putExtra("ID", IDs);
                                startActivity(intent4);
                            }
                        },
                        new Response.ErrorListener()
                        {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {

                    }
                });
                queue.add(stringRequest);
            }
        });
    }


}