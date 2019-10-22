package com.example.libraryqrsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmDetail2 extends AppCompatActivity implements DataToBeSend.DisplayData{
    private TextView textViewUsrnme;
    private TextView textViewPswd;
    private Button btnGo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_detail2);
        textViewUsrnme = (TextView) findViewById(R.id.textView_username);
        textViewPswd = (TextView) findViewById(R.id.textView_password);
    }

    @Override
    public void applyTexts(String username, String password) {


            textViewUsrnme.setText(username);
            textViewPswd.setText(password);





    }
}
