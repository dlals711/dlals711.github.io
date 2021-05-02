package code.codesample.foodrecoder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import code.codesample.foodrecoder.data.FoodRecord;
import code.codesample.foodrecoder.databinding.ActivityMainBinding;
import code.codesample.foodrecoder.databinding.ActivityRecordBinding;

public class RecordActivity extends AppCompatActivity implements RecordAdapter.OnItemClickListener {
    private @NonNull ActivityRecordBinding binding;
    private FoodRecordOpenHelper helper;
    private RecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        helper = new FoodRecordOpenHelper(this, "db", null, 1);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        adapter = new RecordAdapter(helper.getRecords(), this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(manager);


    }

    @Override
    public void onItemClick(View v, int position, FoodRecord record) {
        // helper.delete(record.getId());
        // adapter.setData(helper.getRecords());
        adapter.notifyDataSetChanged();
    }

}