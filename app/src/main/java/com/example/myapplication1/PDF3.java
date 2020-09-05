package com.example.myapplication1;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import androidx.appcompat.app.AppCompatActivity;

public class PDF3 extends AppCompatActivity
{
    PDFView report1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf1);

        report1 = (PDFView) findViewById(R.id.pdfreport1);

        report1.fromAsset("PDF3.pdf").load();
    }
}
