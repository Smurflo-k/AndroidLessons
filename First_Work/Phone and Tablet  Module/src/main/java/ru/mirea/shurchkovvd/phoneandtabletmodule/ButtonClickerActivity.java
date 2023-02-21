package ru.mirea.shurchkovvd.phoneandtabletmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class ButtonClickerActivity extends AppCompatActivity {
    private TextView tvOut;
    private Button btnWhoAmI;
    private Button btnItIsNotMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_clicker);
        tvOut = findViewById(R.id.tvOut);
        btnWhoAmI = findViewById(R.id.btnWhoAmI);
        btnItIsNotMe = findViewById(R.id.btnItIsNotMe);

        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOut.setText("Мой номер по списку № 27");
            }

        };
        btnWhoAmI.setOnClickListener(oclBtnWhoAmI);

    }
    public void onMyButtonClick(View w)
    {
        Toast.makeText(this, "ItIsNotMe", Toast.LENGTH_SHORT).show();
    }

    //public void onCheckboxClicked(View view) {
        //CheckBox checkBox = (CheckBox) view;
        //TextView selection = findViewById(R.id.tvOut);
        //if(checkBox.isChecked()) {
            //selection.setText("Включено");
            //checkBox.setText("Выключить");
        //}
        //else {
            //selection.setText("Выключено");
            //checkBox.setText("Включить");
        //}
    //}

}