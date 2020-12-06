package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdatFelvetelActivity extends AppCompatActivity
{
    private DBHelper adatbazis;
    private EditText editTextFozo, editTextGyumolcs, editTextAlkohol;
    private Button buttonFelvetel, buttonVissza;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_felvetel);

        init();
        //OnClickListenerek
        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent masikActivityre = new Intent(AdatFelvetelActivity.this, MainActivity.class);
                startActivity(masikActivityre);
                finish();
            }
        });
        buttonFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatRogzites();
            }
        });
    }

    public void init()
    {
        adatbazis = new DBHelper(AdatFelvetelActivity.this);
        editTextFozo = findViewById(R.id.editTextFozo);
        editTextGyumolcs = findViewById(R.id.editTextGyumolcs);
        editTextAlkohol = findViewById(R.id.editTextAlkohol);
        buttonFelvetel = findViewById(R.id.buttonFelvetel);
        buttonVissza = findViewById(R.id.buttonVissza);
    }

    private void adatRogzites()
    {
        String fozo = editTextFozo.getText().toString().trim();
        String gyumolcs = editTextGyumolcs.getText().toString().trim();
        String alkohol = editTextAlkohol.getText().toString().trim();
        if (fozo.isEmpty())
        {
            Toast.makeText(this, "A pálinkafőző neve megadása kötelező", Toast.LENGTH_SHORT).show();
        }
        else if (gyumolcs.isEmpty())
        {
            Toast.makeText(this, "Gyümölcs megadása kötelező", Toast.LENGTH_SHORT).show();
        }
        else if (alkohol.isEmpty())
        {
            Toast.makeText(this, "Alkoholtartalom megadása kötelező", Toast.LENGTH_SHORT).show();
        }
        else
        {
            int alkoholSzam = Integer.parseInt(alkohol);
            boolean sikeres = adatbazis.adatRogzites(fozo, gyumolcs, alkoholSzam);
            if (sikeres)
            {
                Toast.makeText(this, "Sikeres adatrögzítés", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Sikertelen adatrögzítés\nA főző-gyümölcs kombináció már létezik az adatbázisban", Toast.LENGTH_LONG).show();
            }
        }
    }
}