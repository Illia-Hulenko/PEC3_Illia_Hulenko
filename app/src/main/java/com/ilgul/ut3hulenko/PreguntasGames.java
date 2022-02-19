package com.ilgul.ut3hulenko;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PreguntasGames {

    public static String leerJson(Context context, String fileName) throws IOException{
        //Para leer texto desde un archivo JSON
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName), "UTF-8"));

        String content ="";
        String line;
        while((line = reader.readLine()) != null){
            content = content + line;
        }
        return content;
    }
}
