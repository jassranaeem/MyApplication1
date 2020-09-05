package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

import java.util.ArrayList;

public class test_name_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_name_list);

        SharedPreferences shared = getSharedPreferences("user_details", MODE_PRIVATE);
        final String user_phone = (shared.getString("PHONE", ""));

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.10.7/profile_test.php?userphone="+user_phone;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        profile_tests(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(test_name_list.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(stringRequest);

    }

    public void profile_tests(String respnse) {
        try {
            JSONArray jArray = new JSONArray(respnse);
            final ArrayList<String> items = new ArrayList<String>();
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                String created_at = json_data.getString("created_at");
                String name = json_data.getString("name");
                String price = json_data.getString("price");
                String finals = "Tested for "+name+" at "+price+"rs on "+created_at;
                items.add(finals);
            }

            ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_expandable_list_item_1, items);

            ListView listView = (ListView) findViewById(R.id.profile_test_list);
            listView.setAdapter(mArrayAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
