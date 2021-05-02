package code.codesample.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

import code.codesample.restaurant.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.menuLayout.setVisibility(View.VISIBLE);
                b.mapLayout.setVisibility(View.GONE);
                b.eventLayout.setVisibility(View.GONE);
            }
        });

        b.mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.menuLayout.setVisibility(View.GONE);
                b.mapLayout.setVisibility(View.VISIBLE);
                b.eventLayout.setVisibility(View.GONE);
            }
        });

        b.eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.menuLayout.setVisibility(View.GONE);
                b.mapLayout.setVisibility(View.GONE);
                b.eventLayout.setVisibility(View.VISIBLE);
            }
        });

    }
}