package code.codesample.foodrecoder;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;

import code.codesample.foodrecoder.data.FoodRecord;
import code.codesample.foodrecoder.databinding.ActivityMainBinding;


@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SharedPreferences preferences;
    private FoodRecordOpenHelper helper;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        helper = new FoodRecordOpenHelper(this, "db", null, 1);

        ArrayList<FoodRecord> list = helper.getRecords();
        for(FoodRecord r : list)
            Log.i("Main", r.getFood() + r.getTime());

        preferences = getSharedPreferences("food", Context.MODE_PRIVATE);
        String lastFood = preferences.getString("food", null);
        String lastTime = preferences.getString("time", null);
        displayRecord(lastFood, lastTime);
        binding.buttonRecord.setOnClickListener(onSave);

        binding.buttonShowAll.setOnClickListener(v->{
            startActivity(new Intent(this, RecordActivity.class));
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void displayRecord(String lastFood, String lastTime) {
        if (lastFood == null) {
            binding.textViewRecord.setText("저장된 기록이 없습니다.");
            binding.textViewElapsed.setText("경과 시간이 없습니다.");
        } else {
            LocalDateTime startTime = LocalDateTime.parse(lastTime);
            LocalDateTime endTime = LocalDateTime.now();
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm", Locale.KOREA);
            String timeStr = startTime.format(formatter);
            String foodMessage = String.format("%s - %s", timeStr, lastFood);
            binding.textViewRecord.setText(foodMessage);
            long hours = ChronoUnit.HOURS.between(startTime, endTime);
            long minutes = ChronoUnit.MINUTES.between(startTime, endTime);
            minutes -= hours * 60;
            binding.textViewElapsed.setText(
                    String.format(Locale.KOREA, "%d시간 %02d분 경과"
                            , hours, minutes));
        }
    }

    private View.OnClickListener onSave= v->{
        SharedPreferences.Editor editor=preferences.edit();
        String food=binding.editText.getText().toString();
        if(!food.isEmpty()){
            editor.putString("food", food);
            String now=LocalDateTime.now().toString();
            editor.putString("time", now);
            editor.apply();
        }
    };
}