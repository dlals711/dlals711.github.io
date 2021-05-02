package code.codesample.memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import code.codesample.memo.widget.MemoAdapter;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton button_add;
    private MemoDatabase db;
    private MemoAdapter adapter;
    private RecyclerView recyclerView;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = MemoDatabase.getInstance(getApplicationContext());
        handler = new Handler();

        button_add = findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, editActivity.class));
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new MemoAdapter(this :: onItemClick);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected  void onStart(){
        super.onStart();
        new Thread(() -> {
            List<Memo> list = db.memoDAO().getMemoList();
            handler.post(()->adapter.setData(list));
        }).start();
    }

    public void onItemClick(int position, Memo memo) {
        Intent intent = new Intent(this,editActivity.class);
        intent.putExtra("id",memo.id);
        startActivity(intent);
    }
}