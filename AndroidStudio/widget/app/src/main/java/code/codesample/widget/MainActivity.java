package code.codesample.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import code.codesample.widget.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private ActivityMainBinding b;

    private Button buttonApply;
    private EditText editTextName, editTextPhone;
    private RadioButton radioButtonAdult, radioButtonStudent;
    private CheckBox checkBoxTerms;
    private ProgressBar progressBar;

    private int beforeLength = 0;
    private boolean isSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());



        initWidgets();


    }

    private void initWidgets() {
        b.editTextName.addTextChangedListener(this);

        b.editTextPhone.addTextChangedListener(this);

        b.radioButtonAdult.setOnClickListener(this);

        b.radioButtonStudent.setOnClickListener(this);

        b.checkBoxTerms.setOnCheckedChangeListener(this);

        b.progressBar.setProgress(0);

        b.buttonApply.setVisibility(View.INVISIBLE);

        b.buttonApply.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        beforeLength = s.length();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        int currentLength = s.length();
        if(beforeLength == 0 && currentLength > 0)
            progressBar.incrementProgressBy(1);
        else if(beforeLength > 0 && currentLength == 0)
            progressBar.incrementProgressBy(-1);
        updateProgress();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonApply){
            Intent intent = new Intent(this, ConfirmActivity.class);
            startActivity(intent);
        } else {
            updateProgress();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) progressBar.incrementProgressBy(1);
        else progressBar.incrementProgressBy(-1);
        updateProgress();
    }

    private void updateProgress() {
        if(progressBar.getProgress() == 4)
            buttonApply.setVisibility(View.VISIBLE);
        else
            buttonApply.setVisibility(View.INVISIBLE);
    }
}