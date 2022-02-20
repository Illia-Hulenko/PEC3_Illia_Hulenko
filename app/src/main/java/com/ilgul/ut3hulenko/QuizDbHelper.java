package com.ilgul.ut3hulenko;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ilgul.ut3hulenko.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UT3Hulenko.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPCION1 + " TEXT, " +
                QuestionTable.COLUMN_OPCION2 + " TEXT, " +
                QuestionTable.COLUMN_OPCION3 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable(){
        Question q1 = new Question("What is a duel between three people called?", "A truel", "A triple", "A duel", 1);
        addQuestion(q1);
        Question q2 = new Question("In the state of Georgia, it’s illegal to eat what with a fork?", "Fried chicken", "Boiled pork", "Only Hotdog", 1);
        addQuestion(q2);
        Question q3 = new Question("Which Tasmanian marsupial is known for its temper?", "Pademelon", "Wombat", "Tasmanian devil", 3);
        addQuestion(q3);
        Question q4 = new Question("Iceland diverted roads to avoid disturbing communities of what?", "People", "Elves", "Orcs", 2);
        addQuestion(q4);
    }

    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPCION1, question.getOpcion1());
        cv.put(QuestionTable.COLUMN_OPCION2, question.getOpcion2());
        cv.put(QuestionTable.COLUMN_OPCION3, question.getOpcion3());
        cv.put(QuestionTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionTable.TABLE_NAME, null, cv);
    }

    //TODO HZ
    @SuppressLint("Range")
    public List<Question> getAllQuestions(){
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME, null);

        if (c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOpcion1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPCION1)));
                question.setOpcion2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPCION2)));
                question.setOpcion3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPCION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
