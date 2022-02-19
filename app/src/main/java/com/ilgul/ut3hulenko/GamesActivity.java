package com.ilgul.ut3hulenko;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class GamesActivity extends AppCompatActivity {


    ImageView randomImage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        randomImage = findViewById(R.id.random_image);

        Picasso.get().load("https://picsum.photos/200/300").into(randomImage);
    }
}