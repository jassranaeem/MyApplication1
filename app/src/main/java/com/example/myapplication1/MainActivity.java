package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void cont (View view)
    {
        SharedPreferences prf = getSharedPreferences("user_details",MODE_PRIVATE);

        if (prf.contains("PHONE"))
        {
            Toast.makeText(this, "Your preference exists", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, dashboard_activiy.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(MainActivity.this, login_page_activity.class);
            startActivity(intent);
        }
    }
}




