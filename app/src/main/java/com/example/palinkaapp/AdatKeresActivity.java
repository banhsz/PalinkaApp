package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdatKeresActivity extends AppCompatActivity {

    private DBHelper adatbazis;
    private EditText editTextFozo, editTextGyumolcs;
    private Button buttonKereses, buttonVissza;
    private TextView textAdatok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_keres);

        init();

        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent masikActivityre = new Intent(AdatKeresActivity.this, MainActivity.class);
                startActivity(masikActivityre);
                finish();
            }
        });

        buttonKereses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatKereses();
            }
        });
    }

    public void init()
    {
        adatbazis = new DBHelper(AdatKeresActivity.this);
        editTextFozo = findViewById(R.id.editTextFozo);
        editTextGyumolcs = findViewById(R.id.editTextGyumolcs);
        buttonKereses = findViewById(R.id.buttonKereses);
        buttonVissza = findViewById(R.id.buttonVissza);
        textAdatok = findViewById(R.id.textAdatok);
        textAdatok.setMovementMethod(new ScrollingMovementMethod());
    }

    private void adatKereses()
    {
        String fozo = editTextFozo.getText().toString().trim();
        String gyumolcs = editTextGyumolcs.getText().toString().trim();
        Cursor adatok = adatbazis.adatKereses(fozo, gyumolcs);
        if (fozo.isEmpty()) {
            Toast.makeText(this, "A pálinkafőző neve megadása kötelező", Toast.LENGTH_SHORT).show();
        } else if (gyumolcs.isEmpty()) {
            Toast.makeText(this, "Gyümölcs megadása kötelező", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (adatok == null)
            {
                textAdatok.setText("Hiba a keresés során");
                return;
            }
            if (adatok.getCount()==0)
            {
                textAdatok.setText("A megadott adatokkal nem található pálinka");
                return;
            }
            StringBuilder stringBuffer = new StringBuilder();
            while (adatok.moveToNext())
            {
                stringBuffer.append("Alkoholtartalom: "+adatok.getString(3)+"%\n");
            }
            textAdatok.setText(stringBuffer.toString());
            Toast.makeText(this, "Sikeres lekérdezés", Toast.LENGTH_SHORT).show();
        }
    }
}