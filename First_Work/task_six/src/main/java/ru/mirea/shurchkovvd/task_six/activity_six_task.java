package ru.mirea.shurchkovvd.task_six;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class activity_six_task extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six_task);
        TextView myTextView = (TextView) findViewById(R.id.textViewStudent);
        myTextView.setText("New text in MIREA");
        Button button = findViewById(R.id.button);
        button.setText("MireaButton");
        CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setChecked(false);

    }
}
