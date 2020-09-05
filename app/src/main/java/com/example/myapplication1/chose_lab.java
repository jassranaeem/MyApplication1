package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.GetChars;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
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

public class chose_lab extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_lab);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.10.7/labs.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        lab_list(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(chose_lab.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(stringRequest);

    }

    public void lab_list(String respnse)
    {
        try
        {
            JSONArray jArray = new JSONArray(respnse);
            final ArrayList<String> items = new ArrayList<String>();
            final ArrayList<String> items_id = new ArrayList<String>();
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                String name = json_data.getString("name");
                String id = json_data.getString("id");
                items_id.add(id);
                items.add(name);
            }

            ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_expandable_list_item_1, items);

            ListView listView = (ListView) findViewById(R.id.lab_list);
            listView.setAdapter(mArrayAdapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(chose_lab.this, chose_tests.class);
                    intent.putExtra("key", items_id.get(position));
                    intent.putExtra("NAME",items.get(position));
                    // pass your values and retrieve them in the other Activity using keyName
                    startActivity(intent);

                }
            });
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
