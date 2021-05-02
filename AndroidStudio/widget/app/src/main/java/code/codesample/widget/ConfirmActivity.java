package code.codesample.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import code.codesample.widget.databinding.ActivityConfirmBinding;

public class ConfirmActivity extends AppCompatActivity {
    private ActivityConfirmBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityConfirmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}