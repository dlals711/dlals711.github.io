package code.codesample.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import code.codesample.quizapp.databinding.ActivityMainBinding;
import code.codesample.quizapp.databinding.ActivityQuestionListBinding;

public class QuestionListActivity extends AppCompatActivity {
    ActivityQuestionListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionListActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        binding.intoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.passwordEt.getText().toString().equals("1234")) {
                    binding.constraintLayout1.setVisibility(View.GONE);
                    binding.constraintLayout2.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(QuestionListActivity.this, "비밀번호를 확인해주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}