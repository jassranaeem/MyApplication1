package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.audiofx.DynamicsProcessing;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Config;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class login_page_activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_activity);

    }

    private static String generateOTP() {
        String numbers = "1234567890";
        Random random = new Random();
        char[] otp = new char[5];

        for (int i = 0; i < 5; i++) {
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
        }

        String string = new String(otp);
        return string;
    }


    public void httprequest(View view)
    {
        EditText editText = (EditText) findViewById(R.id.editText);
        final String phone = editText.getText().toString();


            RequestQueue queue = Volley.newRequestQueue(this);
            //String url ="http://google.com";
            final String otp_generated = generateOTP();
            final String msg = "Thanks for Using Azmayesgah, Your OTP is " + otp_generated;
            String url = "https://sendpk.com/api/sms.php?username=923108102439&password=smsapil12&sender=Azmayesgah&mobile=" + phone + "&message=" + msg;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Intent intent = new Intent(login_page_activity.this, verification_code.class);
                            intent.putExtra("OTP", otp_generated);
                            intent.putExtra("PHONE", phone);
                            startActivity(intent);
                            Toast.makeText(login_page_activity.this, "success", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(login_page_activity.this, "error", Toast.LENGTH_SHORT).show();
                }
            });

            queue.add(stringRequest);

    }
}