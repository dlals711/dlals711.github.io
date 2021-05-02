package code.codesample.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import code.codesample.quizapp.databinding.ActivityQuestionListBinding;
import code.codesample.quizapp.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {
    ActivitySettingBinding binding;
    boolean textVisible = false;
    private int whatScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText question = binding.settingEt;
        EditText score = binding.settingScore;

        EditText question2 = binding.settingEt2;
        EditText score2 = binding.settingScore2;

        EditText textPb1 = binding.settingTextCorrect;
        EditText textPb2 = binding.settingTextCorrect2;
        EditText textPb3 = binding.settingTextCorrect3;
        EditText textPb4 = binding.settingTextCorrect4;

        ImageView imgPb1 = binding.getImage;
        ImageView imgPb2 = binding.getImage2;
        ImageView imgPb3 = binding.getImage3;
        ImageView imgPb4 = binding.getImage4;

        // db생성
        QuestionDatabase db = QuestionDatabase.getInstance(getApplicationContext());

        binding.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Toast.makeText(SettingActivity.this, "이미지화면", Toast.LENGTH_SHORT).show();
                    binding.settingImgQuestion.setVisibility(View.VISIBLE);
                    binding.settingTextQuestion.setVisibility(View.GONE);
                    textVisible = true;
                    Log.d("visible", String.valueOf(textVisible));
                } else {
                    //Toast.makeText(SettingActivity.this, "텍스트화면", Toast.LENGTH_SHORT).show();
                    binding.settingImgQuestion.setVisibility(View.GONE);
                    binding.settingTextQuestion.setVisibility(View.VISIBLE);
                    textVisible = false;
                    Log.d("visible", String.valueOf(textVisible));
                }
            }
        });

        Log.d("visible", String.valueOf(textVisible));

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textVisible == false) {
                    Log.d("click", "Click");
                    if (question.toString().equals("")) {
                        Toast.makeText(SettingActivity.this, "질문을 입력해주세요!", Toast.LENGTH_SHORT).show();
                    } else if (score.getText().toString().equals("")) {
                        Toast.makeText(SettingActivity.this, "배점을 입력해주세요!", Toast.LENGTH_SHORT).show();
                    } else if (textPb1.getText().toString().equals("") ||
                            textPb2.getText().toString().equals("") ||
                            textPb3.getText().toString().equals("") ||
                            textPb4.getText().toString().equals("")) {
                        Toast.makeText(SettingActivity.this, "문제를 다 입력해주세요!", Toast.LENGTH_SHORT).show();
                    } else if (binding.option1.isChecked() == false &&
                            binding.option2.isChecked() == false &&
                            binding.option3.isChecked() == false &&
                            binding.option4.isChecked() == false) {
                        Toast.makeText(SettingActivity.this, "정답을 체크해주세요!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (binding.option1.isChecked()) {
                            whatScore = 1;
                        } else if (binding.option2.isChecked()) {
                            whatScore = 2;
                        } else if (binding.option3.isChecked()) {
                            whatScore = 3;
                        } else if (binding.option4.isChecked()) {
                            whatScore = 4;
                        }
                        new InsertAsyncTask(db.questionDAO()).execute(new Question(
                                question.getText().toString(),
                                textPb1.getText().toString(),
                                textPb2.getText().toString(),
                                textPb3.getText().toString(),
                                textPb4.getText().toString(),
                                score.getText().toString(),
                                whatScore));
                    }
                } else if (textVisible == true) {
                    if (binding.settingEt2.getText().toString().equals("")) {
                        Toast.makeText(SettingActivity.this, "질문을 입력해주세요!!", Toast.LENGTH_SHORT).show();
                    } else if (binding.settingScore2.getText().toString().equals("")) {
                        Toast.makeText(SettingActivity.this, "배점을 입력해주세요!!", Toast.LENGTH_SHORT).show();
                    } else if (binding.option1.isChecked() == false &&
                            binding.option2.isChecked() == false &&
                            binding.option3.isChecked() == false &&
                            binding.option4.isChecked() == false) {
                        Toast.makeText(SettingActivity.this, "정답을 체크해주세요!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static class InsertAsyncTask extends AsyncTask<Question, Void, Void> {
        private QuestionDAO mQuestionDAO;

        public InsertAsyncTask(QuestionDAO questionDAO) {
            this.mQuestionDAO = questionDAO;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            mQuestionDAO.addQuestion(questions[0]);
            return null;
        }
    }

}