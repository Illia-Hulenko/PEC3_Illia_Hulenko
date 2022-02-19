package com.ilgul.ut3hulenko;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GamesActivity extends AppCompatActivity {

    private LinearLayout activity_main;
    RadioGroup radioGroup;
    TextView preguntasId, pregunto;
    RadioButton opcion1, opcion2, opcion3;
    Button sendButton;
    ImageView randomImage;
    int contador = 1; //Cuenta el numero de preguntas a las identifica
    boolean check = false; //Valida que no se dejen preguntas sin responder

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        //Random image from Picaso library
        randomImage = findViewById(R.id.random_image);
        Picasso.get().load("https://picsum.photos/200/300").into(randomImage);

        //Vinculando los componentes
        preguntasId = findViewById(R.id.idPreguntas);
        pregunto = findViewById(R.id.elPregunto);

        radioGroup = findViewById(R.id.radioGroup);
        opcion1 = findViewById(R.id.radioButton1);
        opcion2 = findViewById(R.id.radioButton2);
        opcion3 = findViewById(R.id.radioButton3);

        //Llamando al metodo que muestra la primera pregunta
        try {
            llenarDatos(contador);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Vinculando los botones
        sendButton = findViewById(R.id.buttonSend);
        //OnClick listener del boton SEND
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (opcion1.isChecked()){
                    //Llamado al metodo que verifica si la respuesta seleccionada es coorecta
                    verificarRespuesta(contador, opcion1.getText().toString());
                    check = true; //Cambia el estado de check
                } else if(opcion2.isChecked()){
                    verificarRespuesta(contador, opcion2.getText().toString());
                    check = true; //Cambia el estado de check
                } else if(opcion3.isChecked()){
                    verificarRespuesta(contador, opcion3.getText().toString());
                    check = true; //Cambia el estado de check
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "No se ha selectado ninguna opción",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }

                //Si hay una respuseta seleccionada continua
                if(check){
                    //TODO verificar el numero de las preguntas 5
                    if (contador < 5){
                        contador += 1;
                        try {
                            llenarDatos(contador);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        mostrarResultado();
                    }
                }
            }
        });

        //TODO el boton y el metodo SALIR
        /*
        buttonsalir = findViewById(R.id.);
        buttonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Salir();
            }
        });

         */

    }

    public void llenarDatos(int nemeroPregunta) throws JSONException {

        String jsonFileContent = null;

        try{
            jsonFileContent = PreguntasGames.leerJson(getApplicationContext(), "preguntas.json");

            JSONObject Jsonobject = new JSONObject(jsonFileContent);
            JSONArray jsonArray = Jsonobject.getJSONArray("arrayPreguntas");

            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                int id_preguntas = jsonObj.getInt("id_Pregunta");

                //Si el id corresponde con la pregunta extraemos loas datos
                if (id_preguntas == nemeroPregunta){
                    String Pregunta = jsonObj.getString("Pregunta");
                    String respuesta = jsonObj.getString("respuesta");
                    String alternativa1 = jsonObj.getString("alternativa1");
                    String alternativa2 = jsonObj.getString("alternativa2");

                    //Se muestra la pregunta y numero de pregunta
                    preguntasId.setText("Pregunta " + id_preguntas);
                    pregunto.setText(Pregunta);

                    //Se crea una lista para las posibles respuestas
                    List<String> opciones = Arrays.asList(respuesta, alternativa1, alternativa2);

                    //Da orden aleatorio a las respuestas
                    Collections.shuffle(opciones);

                    //Se asignan las respuestas a los botones de radio
                    opcion1.setText(opciones.get(0));
                    opcion2.setText(opciones.get(1));
                    opcion3.setText(opciones.get(2));
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public void verificarRespuesta(int numeroPregunta, String respuestaDada){

        String jsonFileContent = null;

        try {
            jsonFileContent = PreguntasGames.leerJson(getApplicationContext(), "UTF-8");
            JSONObject Jasonobject = new JSONObject(jsonFileContent);
            JSONArray jsonArray = Jasonobject.getJSONArray("arrayPreguntas");

            for (int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                int id_preguntas = jsonObj.getInt("id_Pregunta");

                //Encontrando la pregunta qie queremos verificar
                if (id_preguntas == numeroPregunta){
                    String respuesta = jsonObj.getString("respuesta");
                    if (respuesta.equals(respuestaDada)){
                        //TODO Cambiar a otra pantalla "CORRECT"
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "CORRECTO",
                                Toast.LENGTH_SHORT);
                        toast.show();

                    } else {
                        //TODO Cambiar a otra pantalla "WRONG"
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "WRONG",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        } catch(IOException | JSONException e){
            e.printStackTrace();
        }
    }

    public void mostrarResultado(){
        //TODO Cambiar a otra pantalla "CONGRATULATIONS"
        Toast toast = Toast.makeText(getApplicationContext(),
                "CONGRATULATIONS",
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void Salir(){
        activity_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = new Intent(GamesActivity.this, DashboardActivity.class);
                d.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(d);
            }
        });
    }
}