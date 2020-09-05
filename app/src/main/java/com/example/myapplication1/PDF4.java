package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class PDF4 extends AppCompatActivity
{
    PDFView report1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf1);

        report1 = (PDFView) findViewById(R.id.pdfreport1);

        report1.fromAsset("PDF4.pdf").load();
    }
}
