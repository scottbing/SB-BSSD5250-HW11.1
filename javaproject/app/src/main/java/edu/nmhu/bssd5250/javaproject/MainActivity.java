package edu.nmhu.bssd5250.javaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "edu.nmhu.bssd5250.javaproject.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.main_layout);

        RadioGroup radioGroup = new RadioGroup(this);

        RadioButton radiobutton1 = new RadioButton(this);
        radiobutton1.setId(View.generateViewId());
        radiobutton1.setText(R.string.blue);
        radiobutton1.setChecked(true);

        RadioButton radiobutton2 = new RadioButton(this);
        radiobutton2.setId(View.generateViewId());
        radiobutton2.setText(R.string.yellow);

        RadioButton radiobutton3 = new RadioButton(this);
        radiobutton3.setId(View.generateViewId());
        radiobutton3.setText(R.string.green);

        radioGroup.addView(radiobutton1);
        radioGroup.addView(radiobutton2);
        radioGroup.addView(radiobutton3);

        linearLayout.addView(radioGroup, 1);

        Button submit = findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedID = radioGroup.getCheckedRadioButtonId();
                RadioButton radio = findViewById(checkedID);
                String choice = radio.getText().toString();
                Log.d("MACT", choice);
                Intent intent = new Intent(getApplicationContext(), MoodActivity.class);
                intent.putExtra(EXTRA_MESSAGE, choice);
                startActivity(intent);
            }
        });
    }
}