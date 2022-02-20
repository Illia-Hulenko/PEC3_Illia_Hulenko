package com.ilgul.ut3hulenko;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CongratulationActivity extends AppCompatActivity {

    Button btnSalir, buttonRestart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);

        //Vinculando los componentes
        btnSalir = findViewById(R.id.btnSalir);
        buttonRestart = findViewById(R.id.buttonRestart);


        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(CongratulationActivity.this, GamesActivity.class);
                g.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(g);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent(CongratulationActivity.this, DashboardActivity.class);
                startActivity(r);
            }
        });
    }



}

