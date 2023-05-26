package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText e1;
    private EditText e2;

    private Button add;
    private Button next;

    private TextView answerText;
    private MathUtil mathUtil;

    private boolean oneFilled;

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!s.equals(null)) {
                if(oneFilled)
                    add.setEnabled(true);
                else
                    oneFilled = true;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mathUtil = new MathUtil();
        defineResources();
        setResources();
    }

    private void defineResources() {
        e1 = findViewById(R.id.editText1);
        e2 = findViewById(R.id.editText2);
        answerText = findViewById(R.id.answerText);
        add = findViewById(R.id.addButton);
        next = findViewById(R.id.nextButton);
    }

    private void setResources() {

        e1.addTextChangedListener(textWatcher);
        e2.addTextChangedListener(textWatcher);
        add.setEnabled(false);
        next.setVisibility(View.INVISIBLE);
        oneFilled = false;
    }

    private void updateAnswer(String answer) {
        answerText.setText(answer);
    }

    private double getE1() {
        return Double.parseDouble(e1.getText().toString());
    }

    private double getE2() {
        return Double.parseDouble(e2.getText().toString());
    }

    public void add(View view) {
        double sum = mathUtil.add(getE1(), getE2());
        updateAnswer(Double.toString(sum));
        next.setVisibility(View.VISIBLE);
    }

    public void nextPage(View view) {
        this.recreate();
    }
}