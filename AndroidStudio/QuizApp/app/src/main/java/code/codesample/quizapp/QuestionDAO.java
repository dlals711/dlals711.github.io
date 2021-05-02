package code.codesample.quizapp;

import androidx.room.Insert;

public interface QuestionDAO {
    @Insert
    long addQuestion(Question question);
}
