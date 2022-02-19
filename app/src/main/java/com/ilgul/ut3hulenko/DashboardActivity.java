package com.ilgul.ut3hulenko;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    private LinearLayout option_maps;
    private LinearLayout contacts;
    private LinearLayout games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        option_maps = (LinearLayout) findViewById(R.id.layoutMap);
        contacts = (LinearLayout) findViewById(R.id.layoutContact);
        games = (LinearLayout) findViewById(R.id.layoutGame);

        /*Action listener to button*/
        option_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, MapsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent y = new Intent(DashboardActivity.this, RecyclerViewCardView.class);
                y.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(y);
            }
        });

        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(DashboardActivity.this, GamesActivity.class);
                g.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(g);
            }
        });






    }
}
