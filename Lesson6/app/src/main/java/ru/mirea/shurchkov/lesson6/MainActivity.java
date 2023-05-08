package ru.mirea.shurchkov.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {
        EditText editTextTextNumGroup;
        EditText editTextTextNumList;
        EditText editTextTextFavFilm;
        Button btnSave;
        @Override
             protected void onCreate (Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextTextNumGroup = findViewById(R.id.editTextTextNumGroup);
        editTextTextNumList = findViewById(R.id.editTextTextNumList);
        editTextTextFavFilm = findViewById(R.id.editTextTextFavFilm);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("GROUP", "БСБО-17-20");
                editor.putInt("NUMBER", 27);
                editor.putString("FAVOURITE FILM", "Смешарики пробуждение зла");
                editor.apply();
                editTextTextNumGroup.setText(sharedPref.getString("GROUP", ""));
                editTextTextNumList.setText(String.valueOf(sharedPref.getInt("NUMBER", 0)));
                editTextTextFavFilm.setText(sharedPref.getString("FAVOURITE FILM", ""));
            }
        });
    }
}

