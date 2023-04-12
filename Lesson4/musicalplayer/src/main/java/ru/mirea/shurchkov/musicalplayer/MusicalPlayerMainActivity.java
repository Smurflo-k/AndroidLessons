package ru.mirea.shurchkov.musicalplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ru.mirea.shurchkov.musicalplayer.databinding.ActivityMusicalPlayerMainBinding;

public class MusicalPlayerMainActivity extends AppCompatActivity {
    private ActivityMusicalPlayerMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMusicalPlayerMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textView.setText("Моя Музыка \n Креативный директор: Шурчков Влад");
            }
        });
        binding.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastNote("Таймер сработает через 5 минут!");
            }
        });
        binding.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textView2.setText("Слезы радости на мне \n На уме розы вместо табака \n Деньги на нуле, кредитка — пополам\n И на сегодня цели нет\n Завтра новая река \n Я сплавляюсь в водах Стикса\n В шортах цвета Шелби два");
            }
        });
        binding.imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastBack("Запуск предыдущей композиции");
            }
        });
        binding.imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastNext("Запуск следующей композиции");
            }
        });
        binding.imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastPause("Композиция на паузе");
            }
        });
        binding.imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastPred("Запуск предыдущего альбома исполнителя");
            }
        });
        binding.imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastDal("Запуск следующего альбома исполнителя");
            }
        });
        binding.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri address = Uri.parse("https://vk.com/boulevardepo");
                Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
                startActivity(openLinkIntent);
                ToastBoulvar("Посетите страницу данного исполнителя в сети Вконтакте!");
            }
        });
    }
    private void ToastNote(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private void ToastBack(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private void ToastNext(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private void ToastPause(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private void ToastPred(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private void ToastDal(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private void ToastBoulvar(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}