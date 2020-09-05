package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.BreakIterator;

public class registration_form extends AppCompatActivity
{
    //initialize variable
    EditText name, f_name, age, relationshipwithpaitent, Cnic, price;
    Button btn;
    RadioGroup rg;
    RadioButton rfemale, rmale;

    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        RequestQueue queue = Volley.newRequestQueue(this);
        final String url ="http://192.168.10.7/patient_test_info.php";
        name = (EditText) findViewById(R.id.firstname);
        f_name = (EditText) findViewById(R.id.lastname);
        age = (EditText) findViewById(R.id.age);
        relationshipwithpaitent = (EditText) findViewById(R.id.rwp);
        Cnic = (EditText) findViewById(R.id.cnic);
        price = (EditText) findViewById(R.id.price);
        btn = (Button) findViewById(R.id.btn);
        final TextView tv = (TextView)findViewById(R.id.textView26);
        //initialize AwesomeValidation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.firstname, RegexTemplate.NOT_EMPTY, R.string.namerror);
        awesomeValidation.addValidation(this, R.id.lastname, RegexTemplate.NOT_EMPTY, R.string.f_namerror);
        awesomeValidation.addValidation(this, R.id.rwp, RegexTemplate.NOT_EMPTY, R.string.DOB);
        awesomeValidation.addValidation(this, R.id.cnic, RegexTemplate.NOT_EMPTY, R.string.invalid_cnic);

        final String first_name = f_name.getText().toString();
        final String last_name = name.getText().toString();
        String relation_with_patient = relationshipwithpaitent.getText().toString();
        final String Age = age.getText().toString();
        String CNIC = Cnic.getText().toString();


        //check validation


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rg = (RadioGroup) findViewById(R.id.radioGroup);


                int selectedId = rg.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                rmale = (RadioButton) findViewById(selectedId);
                String gender = rmale.toString();
                rfemale=(RadioButton)findViewById(selectedId);
                String gender1=rfemale.toString();

                String qryString = "?name="+first_name+"&fname="+last_name+"&gender=male&age="+Age;
                String urll = url + qryString;
                if (awesomeValidation.validate()) {
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, urll,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(registration_form.this, response.toString(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(registration_form.this, person_onway.class);
                                    startActivity(intent);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(registration_form.this, error.toString(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Submission failed", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    }






