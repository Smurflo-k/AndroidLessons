package ru.mirea.shurchkov.thread;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

import ru.mirea.shurchkov.thread.databinding.ActivityThreadMainBinding;
public class ThreadMainActivity extends AppCompatActivity {
    private ActivityThreadMainBinding binding;
    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThreadMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TextView infoTextView = findViewById(R.id.textView3);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Имя текущего потока: " + mainThread.getName());
        mainThread.setName("МОЙ НОМЕР ГРУППЫ: 17, НОМЕР ПО СПИСКУ: 27, МОЙ ЛЮБИИМЫЙ ФИЛЬМ: Форсаж 3");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(ThreadMainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread.getStackTrace()));
        binding.btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float a, b, c;
                String s1 = binding.editCountPars.getText().toString();
                String s2 = binding.editCountDays.getText().toString();
                a = Float.parseFloat(s1);
                b = Float.parseFloat(s2);
                c = (a / b);
                float c1 = (float)Math.ceil(c);
                String s = Double.toString(c1);
                binding.TextVivod.setText(s);
            }
        });
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        int numberThread = counter++;
                        Log.d("ThreadProject", String.format("Запущен поток No %d студентом группы No %s номер по списку No %d ", numberThread, "БСБО-17-20", 27));
                        long endTime = System.currentTimeMillis() + 20 * 1000;
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                    Log.d(ThreadMainActivity.class.getSimpleName(), "Endtime: " + endTime);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject", "Выполнен поток No " + numberThread);
                        }
                    }
                }).start();
            }
        });
    }
}
