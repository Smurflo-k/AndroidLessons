package ru.mirea.shurchkov.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class NotebookMainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PERMISSION = 100;
    private boolean isWork = false;
    EditText editTextTextNameFile;
    EditText editTextTextCitate;
    Button btnSave;
    Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook_main);
        editTextTextNameFile = findViewById(R.id.editTextTextNameFile);
        editTextTextCitate = findViewById(R.id.editTextTextCitate);
        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String publicDirectoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath();
                File file = new File(publicDirectoryPath, editTextTextNameFile.getText().toString()+".txt");
//                FileWriter writer = new FileWriter(file);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile());
                    OutputStreamWriter output = new OutputStreamWriter(fileOutputStream);
                    output.write(editTextTextCitate.getText().toString());
                    output.close();
                    Toast toast = Toast.makeText(getApplicationContext(), "Запись в файл успешно выполнена!", Toast.LENGTH_SHORT);
                    toast.show();
                } catch (IOException e) {
                    Log.w("ExternalStorage", "Error writing " + file, e);
                    Toast toast = Toast.makeText(getApplicationContext(), "Запись в файл провалена(", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File path = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOCUMENTS);
                File file = new File(path, editTextTextNameFile.getText().toString()+".txt");
                try {
                    FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());

                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);

                    List<String> lines = new ArrayList<String>();
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    String line = reader.readLine();
                    while (line != null) {
                        lines.add(line);
                        line = reader.readLine();
                    }
                    Log.w("ExternalStorage", String.format("Read from file %s successful", lines.toString()));
                    editTextTextCitate.setText(lines.toString());
                    Toast toast = Toast.makeText(getApplicationContext(), "Прочитка файла успешно выполнена!", Toast.LENGTH_SHORT);
                    toast.show();
                } catch (Exception e) {
                    Log.w("ExternalStorage", String.format("Read from file %s failed", e.getMessage()));
                    Toast toast = Toast.makeText(getApplicationContext(), "Прочитка файла провалена...", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

}
