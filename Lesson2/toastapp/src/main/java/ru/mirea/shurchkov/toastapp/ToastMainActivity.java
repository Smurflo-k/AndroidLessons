package ru.mirea.shurchkov.toastapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ToastMainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_main);

        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        textView = new TextView(this);
        textView.setId(View.generateViewId());
        ConstraintLayout.LayoutParams textViewLayout = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        textViewLayout.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        textViewLayout.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        textViewLayout.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        textView.setLayoutParams(textViewLayout);
        constraintLayout.addView(textView);

        editText = new EditText(this);
        editText.setId(View.generateViewId());
        editText.setHint("Введите текст:");
        ConstraintLayout.LayoutParams editTextLayout = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        editTextLayout.topToBottom = textView.getId();
        editTextLayout.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        editTextLayout.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        editText.setLayoutParams(editTextLayout);
        constraintLayout.addView(editText);

        Button button = new Button(this);
        button.setText("Ввод");
        ConstraintLayout.LayoutParams buttonLayout = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        buttonLayout.topToBottom = editText.getId();
        buttonLayout.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        button.setLayoutParams(buttonLayout);
        constraintLayout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView.setText(getString(R.string.stud)+ "\n" + getString(R.string.group) + "\n" + getString(R.string.kolsymb) + editText.getText().length());
            }
        });

        setContentView(constraintLayout);

        Toast toast = Toast.makeText(getApplicationContext(),
                "Здравствуйте,Влад!",
                Toast.LENGTH_LONG);
        toast.show();
    }
}