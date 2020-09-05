package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class chose_tests extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_tests);
        final String[] ValueHolder = new String[1];
        final int[] price = new int[1];
        TextView lab_name = (TextView) findViewById(R.id.lab_name);
        ListView listView = (ListView) findViewById(R.id.test_list);
        Button btn = (Button) findViewById(R.id.button70);
        final String data = getIntent().getExtras().getString("key");
        final String name = getIntent().getExtras().getString("NAME");
        lab_name.setText(name + " Lab");
        final String url = "http://192.168.10.7/testss.php?id=" + data;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        final SparseBooleanArray[] sparseBooleanArray = new SparseBooleanArray[1];
                        final ListView listView = (ListView) findViewById(R.id.test_list);
                        try {
                            final JSONArray jArray = new JSONArray(response);
                            final ArrayList<String> items = new ArrayList<String>();
                            final ArrayList<String> items_price = new ArrayList<String>();
                            final ArrayList<String> items_id = new ArrayList<String>();

                            for (int i = 0; i < jArray.length(); i++) {
                                JSONObject json_data = jArray.getJSONObject(i);
                                String name = json_data.getString("name");
                                String price = json_data.getString("price");
                                String id = json_data.getString("id");
                                items_price.add(price);
                                items_id.add(id);
                                items.add(name+"\t\t - \t\t"+price+"rs");
                            }
                            final ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(chose_tests.this, android.R.layout.simple_list_item_multiple_choice, items);


                            listView.setAdapter(mArrayAdapter);


                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    // TODO Auto-generated method stub

                                    sparseBooleanArray[0] = listView.getCheckedItemPositions();
                                    ValueHolder[0] = "" ;
                                    price[0] = 0;
                                    int i = 0 ;

                                    while (i < sparseBooleanArray[0].size()) {

                                        if (sparseBooleanArray[0].valueAt(i)) {

                                            ValueHolder[0] += items_id.get(sparseBooleanArray[0].keyAt(i)) + ",";
                                            price[0] += Integer.parseInt(items_price.get(sparseBooleanArray[0].keyAt(i)));
                                        }

                                        i++ ;
                                    }

                                    ValueHolder[0] = ValueHolder[0].replaceAll("(,)*$", "");

                                }
                            });


                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        queue.add(stringRequest);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(chose_tests.this, register_form.class);
                intent4.putExtra("TOTAL_PRICE", price[0]);
                intent4.putExtra("ID", ValueHolder[0]);
                startActivity(intent4);
            }
        });
    }


}