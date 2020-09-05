package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

public class verification_code extends AppCompatActivity {

    String otp_generated = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);
        otp_generated = getIntent().getStringExtra("OTP");

        TextView textView = (TextView) findViewById(R.id.textView4);
        textView.setText(otp_generated);


    }

    public void verify(View view)
    {
        final String urll ="http://192.168.43.18/user_register.php";
        String phone = getIntent().getStringExtra("PHONE");
        EditText editText = (EditText) findViewById(R.id.editText2);
        String otp = editText.getText().toString();


        SharedPreferences sharedPref = getSharedPreferences("user_details",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("PHONE", phone);
        editor.commit();

        String url = urll +"?phonenumber="+phone;
        if (otp.equals(otp_generated))
        {
            Toast.makeText(this, "verified", Toast.LENGTH_SHORT).show();
            submit_info(url);

        }
    }

    public void submit_info(String url)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(verification_code.this, response, Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(verification_code.this, dashboard_activiy.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

}


