package com.example.myapplication1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class blood_donate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_donate);

        SharedPreferences shared = getSharedPreferences("user_details", MODE_PRIVATE);
        final String user_phone = (shared.getString("PHONE", ""));

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.10.7/profile_donor.php?userphone="+user_phone;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        profile_donors(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(blood_donate.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(stringRequest);

    }

    public void profile_donors(String respnse) {
        try {
            JSONArray jArray = new JSONArray(respnse);
            final ArrayList<String> items = new ArrayList<String>();
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);

                String ld = json_data.getString("last_donated");
                String bg = json_data.getString("blood_group");
                String finals = "Donated "+bg+" blood on "+ld;
                items.add(finals);
            }

            ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_expandable_list_item_1, items);

            ListView listView = (ListView) findViewById(R.id.donation_list);
            listView.setAdapter(mArrayAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
