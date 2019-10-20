package com.example.libraryqrsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfirmDetail extends AppCompatActivity implements DataToBeSend.DisplayData {

    private TextView textViewUsrnme;
    private TextView textViewPswd;
    private Button btnGo;
    //private Button btnConfirm;
    private  TextView txtboxUsr;
    private TextView txtboxBook;
    DatabaseReference FRBSBOOK;
    //BookBorrower bookBorrower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_detail);

        textViewUsrnme = (TextView) findViewById(R.id.textView_username);
        textViewPswd = (TextView) findViewById(R.id.textView_password);
        txtboxUsr = (TextView) findViewById(R.id.txtBoxInfo);
        txtboxBook = (TextView) findViewById(R.id.txtBoxBook);
        //btnConfirm = (Button) findViewById(R.id.btnSend);


        FRBSBOOK = FirebaseDatabase.getInstance().getReference().child("BookBorrower");
        //btnConfirm

        btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openData();
            }
        });
    }
    public void openData(){
        DataToBeSend exampleDialog = new DataToBeSend();
        exampleDialog.show(getSupportFragmentManager(),"contoh dialog");
    }


    @Override
    public void applyTexts(String username, String password) {
        textViewUsrnme.setText(username);
        textViewPswd.setText(password);
        txtboxUsr.setText(username);
        txtboxBook.setText(password);


    }
}
