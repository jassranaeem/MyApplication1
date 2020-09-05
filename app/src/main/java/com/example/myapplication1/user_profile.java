package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class user_profile extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        t1=(TextView)findViewById(R.id.testreport);
        t2=(TextView)findViewById(R.id.testlist);
        t3=(TextView)findViewById(R.id.testblood);
        t4=(TextView)findViewById(R.id.bloodrecieve);
        t5=(TextView)findViewById(R.id.myprofile);

            t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(user_profile.this, test_reports.class);
                    startActivity(intent);
                }
            });


            t2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(user_profile.this, test_name_list.class);
                    startActivity(intent);
                }
            });

            t3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(user_profile.this, blood_donate.class);
                    startActivity(intent);
                }
            });

            t4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(user_profile.this, blood_recieve.class);
                    startActivity(intent);
                }
            });

            t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(user_profile.this, profile_view.class);
                startActivity(intent);
            }
        });


    }
}
