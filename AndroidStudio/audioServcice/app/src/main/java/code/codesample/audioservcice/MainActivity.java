package code.codesample.audioservcice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.SeekBar;

import code.codesample.audioservcice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private ActivityMainBinding binding;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        IntentFilter filter=new IntentFilter("com.codesample.service.Play");
        registerReceiver(receiver, filter);

        binding.seekBar.setOnSeekBarChangeListener(this);
        binding.seekBar.setEnabled(false);
        binding.buttonPlay.setOnClickListener(v-> requestPlay());
        binding.buttonStop.setOnClickListener(v-> requestStop());

        binding.buttonPlay.setOnClickListener(v -> {
            Intent intent = new Intent(this, PlayService.class);
            startService(intent);
        });

        binding.buttonStop.setOnClickListener(v -> {
            Intent intent = new Intent(this, PlayService.class);
            stopService(intent);
        });
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent != null) {
                String status = intent.getStringExtra("status");
                if(status.equals("play")){
                    int duration=intent.getIntExtra("duration", 0);
                    int current=intent.getIntExtra("current", 0);
                    binding.seekBar.setEnabled(true);
                    binding.seekBar.setMax(duration);
                    binding.seekBar.setProgress(current);
                } else if(status.equals("stop")){
                    binding.seekBar.setProgress(0);
                    binding.seekBar.setEnabled(false);
                }
            }
        }
    };

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser){
            Intent intent=new Intent("com.codesample.audioservcice.Request");
            intent.putExtra("progress", progress);
            sendBroadcast(intent);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void requestPlay(){
        Intent intent=new Intent(this, PlayService.class);
        startService(intent);
    }
    private void requestStop(){
        Intent intent=new Intent(this, PlayService.class);
        stopService(intent);
    }
}