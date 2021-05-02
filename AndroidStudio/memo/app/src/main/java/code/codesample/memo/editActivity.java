package code.codesample.memo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.time.LocalDateTime;

public class editActivity extends AppCompatActivity {
    Button delete_button, cancel_button, register_button;
    EditText edtittext_title, edittext_body;
    private MemoDatabase db;
    private Memo memo;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        delete_button = findViewById(R.id.delete_button);
        cancel_button = findViewById(R.id.cancel_button1);
        register_button = findViewById(R.id.register_button);
        edtittext_title = findViewById(R.id.edtittext_title);
        edittext_body = findViewById(R.id.edittext_body);

        db = MemoDatabase.getInstance(getApplicationContext());
        handler = new Handler();
        register_button.setOnClickListener(this :: onSave);

        int id = getIntent().getIntExtra("id", 0);
        loadMemo(id);
    }

    private void loadMemo(int id) {
        if(id > 0) {
            new Thread(() -> {
                memo = db.memoDAO().getMemo(id);
                handler.post(() -> {
                    edtittext_title.setText(memo.title);
                    edittext_body.setText(memo.body);
                });
            }).start();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void onSave(View view) {
        String title = edtittext_title.getText().toString();
        String body = edittext_body.getText().toString();
        if(title.isEmpty() || body.isEmpty()) return;
        if(memo == null) {
            memo = new Memo();
        }
        memo.title = title;
        memo.body = body;
        memo.time = LocalDateTime.now().toString();
        
        new Thread(()->{
            db.memoDAO().addMemo(memo);
            finish();
        }).start();
    }
}