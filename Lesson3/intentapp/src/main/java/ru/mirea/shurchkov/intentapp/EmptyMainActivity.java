package ru.mirea.shurchkov.intentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EmptyMainActivity extends AppCompatActivity {

    private TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_main);
        text1 = findViewById(R.id.textView2);
        String text = (String) getIntent().getSerializableExtra("key1");
        text1.setText(text);
    }
}