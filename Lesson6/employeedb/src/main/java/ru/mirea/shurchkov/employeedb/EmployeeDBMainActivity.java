package ru.mirea.shurchkov.employeedb;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class EmployeeDBMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dbmain);
        AppDatabase db = App.getInstance().getDatabase();
        SuperheroDao superheroDao = db.superheroDao();
        Superhero superhero = new Superhero();
        superhero.id = 1;
        superhero.name = "Человек-горшок";
        superhero.superpower = "Управляет отходами";
        superhero.evil="Человек-мыло";
        superheroDao.insert(superhero);
        List<Superhero> superHeroes = superheroDao.getAll();
        superhero = superheroDao.getById(1);
        superhero.superpower = "Подчинение всего сущего на планете";
        superheroDao.update(superhero);
        Log.d("База данных", superhero.name + " " + superhero.superpower);
    }
}