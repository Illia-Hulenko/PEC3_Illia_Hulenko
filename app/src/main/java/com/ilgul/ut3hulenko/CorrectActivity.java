package com.ilgul.ut3hulenko;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CorrectActivity extends AppCompatActivity {

    Button buttonNext, btnSalirCorr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct);

        buttonNext = findViewById(R.id.buttonNext);
        btnSalirCorr = findViewById(R.id.btnSalirCorr);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(CorrectActivity.this, GamesActivity.class);
                n.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(n);
            }
        });

        btnSalirCorr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(CorrectActivity.this, DashboardActivity.class);
                s.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(s);
            }
        });

    }
}