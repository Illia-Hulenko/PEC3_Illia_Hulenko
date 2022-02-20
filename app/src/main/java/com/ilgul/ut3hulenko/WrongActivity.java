package com.ilgul.ut3hulenko;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WrongActivity extends AppCompatActivity {

    Button btnSalirWrong, buttonTryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong);

        buttonTryAgain = findViewById(R.id.buttonTryAgain);
        btnSalirWrong = findViewById(R.id.btnSalirWrong);

        buttonTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tr = new Intent(WrongActivity.this, GamesActivity.class);
                tr.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(tr);
            }
        });

        btnSalirWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wr = new Intent(WrongActivity.this, DashboardActivity.class);
                wr.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(wr);
            }
        });
    }
}