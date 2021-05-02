package code.codesample.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.webkit.WebViewClient;
import android.widget.Toast;

import code.codesample.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final int REQ_SMS = 10;
    private BTReceiver btReceiver;
    private ActivityResultLauncher<String> launcher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) sendSMS();
        else Toast.makeText(this, "Denied", Toast.LENGTH_SHORT).show();
    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(btReceiver);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.webview.setWebViewClient(new WebViewClient());
        binding.webview.loadUrl("https://www.naver.com");

        binding.buttonSend.setOnClickListener(v -> {
            if (checkAndRequestPermission())
                sendSMS();
        });

        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter != null)
            binding.switchBluetooth.setChecked(adapter.isEnabled());

        binding.switchBluetooth.setOnCheckedChangeListener((v, b) -> {
            if (adapter != null) {
                if (b) adapter.enable();
                else adapter.disable();
            }
        });

        btReceiver = new BTReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(btReceiver, filter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean checkAndRequestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED)
            return true;
        //requestPermissions(new String[]{Manifest.permission.SEND_SMS}, REQ_SMS);
        launcher.launch(Manifest.permission.SEND_SMS);
        return false;
    }

    private void sendSMS() {
        String message = binding.editTextMessage.getText().toString();
        String receiver = binding.editTextReceiver.getText().toString();
        if (!message.isEmpty() && !receiver.isEmpty()) {
            SmsManager manager = SmsManager.getDefault();
            manager.sendTextMessage(receiver, null, message, null, null);
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode==REQ_SMS){
//            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
//                sendSMS();
//            else{
//                Toast.makeText(this,
//                        "권한이 없어 전송하지 못했습니다.", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    class BTReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                int status = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.STATE_OFF);

                switch (status) {
                    case BluetoothAdapter.STATE_TURNING_ON:
                    case BluetoothAdapter.STATE_ON:
                        binding.switchBluetooth.setChecked(true);
                        break;

                    case BluetoothAdapter.STATE_TURNING_OFF:
                    case BluetoothAdapter.STATE_OFF:
                        binding.switchBluetooth.setChecked(false);
                }
            }
        }
    }
}