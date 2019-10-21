package com.example.libraryqrsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;


public class Menu extends AppCompatActivity {

     String arrayIcon[]  =
            {"Bleach","Facebook", "Whatsapp"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        CircleMenu circleMenu = findViewById(R.id.circle_menu);

//#RRGGBB

        circleMenu.setMainMenu(Color.parseColor("#D3D3D3"),R.drawable.ic_add,R.drawable.ic_clear)
                .addSubMenu(Color.parseColor("#ffffff"),R.drawable.bleach)
                .addSubMenu(Color.parseColor("#4169E1"),R.drawable.facebook_icons8)
                .addSubMenu(Color.parseColor("#32CD32"),R.drawable.whatsapp_icons8)
               // .addSubMenu(Color.parseColor("#ffffff"),R.drawable.facebook200) .addSubMenu(Color.parseColor("#ffffff"),R.drawable.googleplus) .addSubMenu(Color.parseColor("#FF0000"),R.drawable.youtube192)

                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int Visit) {
                        switch (Visit) {
                            case 0:
                                Toast.makeText(Menu.this,"Click this "+ arrayIcon[Visit],Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Menu.this,Login.class);
                               startActivity(intent);
                                break;
                            case 1:
                                Intent intent2 = new Intent(Menu.this,Register.class);
                                startActivity(intent2);
                                break;
                            case 2:
                                Intent intent3 = new Intent(Menu.this,BookScan.class);
                                startActivity(intent3);
                                break;

                            default:
                                Toast.makeText(Menu.this, "Notihing", Toast.LENGTH_SHORT).show();
                        }}
                }) .setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {
            @Override
            public void onMenuOpened() {
                Log.e("tag","onOpened");
            }

            @Override
            public void onMenuClosed() {
                Log.e("tag","onClosed");
            }


                });

    }
}
