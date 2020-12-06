package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DBHelper adatbazis;
    private Button buttonAdatFelvetel,buttonKereses,buttonListazas;
    private TextView textAdatok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        buttonListazas.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                adatLekerdezes();
            }
        });
        buttonAdatFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent masikActivityre = new Intent(MainActivity.this, AdatFelvetelActivity.class);
                startActivity(masikActivityre);
                finish();
            }
        });


    }
    public void init()
    {
        adatbazis = new DBHelper(MainActivity.this);
        buttonAdatFelvetel = findViewById(R.id.buttonAdatFelvetel);
        buttonKereses = findViewById(R.id.buttonKereses);
        buttonListazas = findViewById(R.id.buttonListazas);
        textAdatok = findViewById(R.id.textAdatok);
        textAdatok.setMovementMethod(new ScrollingMovementMethod());
    }

    private void adatLekerdezes()
    {
        Cursor adatok = adatbazis.adatLekerdezes();
        if (adatok == null)
        {
            Toast.makeText(this, "Sikertelen lekérdezés", Toast.LENGTH_SHORT).show();
            return;
        }
        if (adatok.getCount()==0)
        {
            Toast.makeText(this, "Üres az adatbázis", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuilder stringBuffer = new StringBuilder();
        while (adatok.moveToNext())
        {
            stringBuffer.append("ID: "+adatok.getString(0)+"\n");
            stringBuffer.append("Főző: "+adatok.getString(1)+"\n");
            stringBuffer.append("Gyümölcs: "+adatok.getString(2)+"\n");
            stringBuffer.append("Alkohol: "+adatok.getString(3)+"\n\n");
        }
        textAdatok.setText(stringBuffer.toString());
        Toast.makeText(this, "sikeres lekérdezés", Toast.LENGTH_SHORT).show();
    }

}