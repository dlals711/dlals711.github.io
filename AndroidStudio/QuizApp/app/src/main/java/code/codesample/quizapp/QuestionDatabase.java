package code.codesample.quizapp;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class QuestionDatabase extends RoomDatabase {
    public abstract QuestionDAO questionDAO();

    private static QuestionDatabase instance;
    public static QuestionDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(
                    context,
                    QuestionDatabase.class,
                    "db")
                    .build();
        }
        return instance;
    }
}
