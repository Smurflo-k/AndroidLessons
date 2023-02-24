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
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_clicker);
        tvOut = findViewById(R.id.tvOut);
        btnWhoAmI = findViewById(R.id.btnWhoAmI);
        btnItIsNotMe = findViewById(R.id.btnItIsNotMe);
        checkBox = findViewById(R.id.checkBox);

        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOut.setText(R.string.tvout1);
                checkBox.setChecked(true);
                checkBox.setText(R.string.cb_open);
            }
        };
        btnWhoAmI.setOnClickListener(oclBtnWhoAmI);
    }
    public void onMyButtonClick(View w)
    {
        Toast.makeText(this, "Еще 1 способ! ItIsNotMe", Toast.LENGTH_SHORT).show();
        tvOut.setText(R.string.tvout2);
        checkBox.setChecked(true);
        checkBox.setText(R.string.cb_open);
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