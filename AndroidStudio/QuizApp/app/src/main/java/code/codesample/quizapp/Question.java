package code.codesample.quizapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(tableName = "question")
public class Question {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String q_question; // 질문

    public String q_problem1; // 문제
    public String q_problem2;
    public String q_problem3;
    public String q_problem4;

    public String score; // 배점

    public String q_image1; // 이미지
    public String q_image2;
    public String q_image3;
    public String q_image4;

    public int q_answer; // 답 ex) 1번, 2번

    public Question(String q_question,
                    String q_problem1,
                    String q_problem2,
                    String q_problem3,
                    String q_problem4,
                    String score,
                    int whatScore) {
        this.q_question = q_question; // 질문
        this.q_problem1 = q_problem1; // 문제인
        this.q_problem2 = q_problem2; // 문제2
        this.q_problem3 = q_problem3; // 문제3
        this.q_problem4 = q_problem4; // 문제4
        this.score = score; // 배점
        this.q_answer = whatScore; // 답
    }
}
