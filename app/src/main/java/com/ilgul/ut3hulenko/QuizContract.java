package com.ilgul.ut3hulenko;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract(){}

    public static class QuestionTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPCION1 = "option1";
        public static final String COLUMN_OPCION2 = "option2";
        public static final String COLUMN_OPCION3 = "option3";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
    }
}
