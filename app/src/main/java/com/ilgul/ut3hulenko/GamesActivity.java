package com.ilgul.ut3hulenko;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class GamesActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    TextView preguntasId, pregunto;
    RadioButton opcion1, opcion2, opcion3;
    Button sendButton, btnSalir;
    ImageView randomImage;

    List<Question> questionList;
    ColorStateList textColorDefaultRb;

    int questionCounter;
    int questionCountTotal;
    Question currentQuestion;

    boolean answered;


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

        //Vinculando los botones
        radioGroup = findViewById(R.id.radioGroup);
        opcion1 = findViewById(R.id.radioButton1);
        opcion2 = findViewById(R.id.radioButton2);
        opcion3 = findViewById(R.id.radioButton3);
        sendButton = findViewById(R.id.buttonSend);
        btnSalir = findViewById(R.id.btnSalir);

        textColorDefaultRb = opcion1.getTextColors();

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered){
                    if (opcion1.isChecked() || opcion2.isChecked() || opcion3.isChecked()){
                        checkAnswer();
                    } else {
                        Toast.makeText(GamesActivity.this,
                                "No se ha seleccionado ninguno opcion",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ex = new Intent(GamesActivity.this, DashboardActivity.class);
                ex.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(ex);
            }
        });
    }

    private void showNextQuestion(){
        opcion1.setTextColor(textColorDefaultRb);
        opcion2.setTextColor(textColorDefaultRb);
        opcion3.setTextColor(textColorDefaultRb);
        radioGroup.clearCheck();

        if (questionCounter < questionCountTotal){
            currentQuestion = questionList.get(questionCounter);

            pregunto.setText(currentQuestion.getQuestion());
            opcion1.setText(currentQuestion.getOpcion1());
            opcion2.setText(currentQuestion.getOpcion2());
            opcion3.setText(currentQuestion.getOpcion3());

            questionCounter++;
            preguntasId.setText("Pregunta: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            sendButton.setText("Confirmar");
        } else {
            finishQuiz();
        }
    }
    private void checkAnswer(){
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNr = radioGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()){
            Toast.makeText(GamesActivity.this,
                    "Correct answer",
                    Toast.LENGTH_SHORT).show();
        }
        showSolution();
    }

    private void showSolution(){
        opcion1.setTextColor(Color.RED);
        opcion2.setTextColor(Color.RED);
        opcion3.setTextColor(Color.RED);

        switch(currentQuestion.getAnswerNr()){
            case 1:
                opcion1.setTextColor(Color.GREEN);
                pregunto.setText("La pregunta №1 es correcto.");
                break;
            case 2:
                opcion2.setTextColor(Color.GREEN);
                pregunto.setText("La pregunta №2 es correcto.");
                break;
            case 3:
                opcion3.setTextColor(Color.GREEN);
                pregunto.setText("La pregunta №3 es correcto.");
                break;
        }

        if (questionCounter < questionCountTotal){
            sendButton.setText("Next");
        } else {
            sendButton.setText("Finish");
        }
    }

    private void finishQuiz(){
        Intent cong = new Intent(GamesActivity.this, CongratulationActivity.class);
        cong.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(cong);
        finish();
    }
}