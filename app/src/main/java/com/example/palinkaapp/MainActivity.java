package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper adatbazis;
    TextView textAdatok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        adatbazis.adatRogzites("Pécsi pálinka","Körte","30");
        adatLekerdezes();

    }

    public void init()
    {
        adatbazis = new DBHelper(MainActivity.this);
        textAdatok = findViewById(R.id.textAdatok);

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
            stringBuffer.append("Alkohol: "+adatok.getString(3)+"\n");
        }
        textAdatok.setText(stringBuffer.toString());
        Toast.makeText(this, "sikeres lekérdezés", Toast.LENGTH_SHORT).show();
    }
}