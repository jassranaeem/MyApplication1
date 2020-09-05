package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class person_onway extends AppCompatActivity
{
    TextView t1, t2, t3, t4;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_onway);


        t1 = (TextView) findViewById(R.id.name);
        t2 = (TextView) findViewById(R.id.address);
        t3 = (TextView) findViewById(R.id.nic);
        t4 = (TextView) findViewById(R.id.phonenumber);
        btn = (Button) findViewById(R.id.personbutton);

        final String IDs = getIntent().getExtras().getString("ID");
        final String urll = "http://192.168.10.7/check.php?users="+IDs;
        final String url = "http://192.168.10.7/phlebotomist.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        RequestQueue queues = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        get_details(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);

        StringRequest stringRequests = new StringRequest(Request.Method.GET, urll,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        selected_tests(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queues.add(stringRequests);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(person_onway.this,testlocation.class);
                startActivity(intent);
            }
        });

    }
    public void selected_tests(String respnse) {
        try {
            JSONArray jArray = new JSONArray(respnse);
            ArrayList<String> items = new ArrayList<String>();
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                String name = json_data.getString("name");
                String price = json_data.getString("price");
                items.add(name + "\t\t\t\t\t\t - \t\t\t\t\t\t" + price + "rs");
            }

            ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_expandable_list_item_1, items);

            ListView listView = (ListView) findViewById(R.id.selected_tests);
            listView.setAdapter(mArrayAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void get_details(String response) {
        try {
            JSONArray jArray = new JSONArray(response);

            JSONObject json_data = jArray.getJSONObject(0);
            String name = json_data.getString("first_name") + json_data.getString("last_name");
            String address = json_data.getString("address");
            String cnic = json_data.getString("cnic");
            String phone = json_data.getString("phone_number");


            t1.setText("RIDER NAME : " + name);
            t2.setText("ADDRESS : " + address);
            t3.setText("CNIC : " + cnic);
            t4.setText("PHONE : " + phone);


        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}