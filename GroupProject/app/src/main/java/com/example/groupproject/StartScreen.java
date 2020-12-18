package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;

public class StartScreen extends AppCompatActivity {

    ImageButton car1;
    ImageButton car2;
    ImageButton car3;
    ImageButton car4;
    ImageButton menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        car1 = findViewById(R.id.Car1);

        car1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartScreen.this, BottomSheet.class);
                startActivity(intent);
            }
        });

        car2 = findViewById(R.id.Car2);

        car2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartScreen.this, BottomSheet.class);
                startActivity(intent);
            }
        });

        car3 = findViewById(R.id.Car3);

        car3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartScreen.this, BottomSheet.class);
                startActivity(intent);
            }
        });

        car4 = findViewById(R.id.Car4);

        car4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartScreen.this, BottomSheet.class);
                startActivity(intent);
            }
        });

        menu = findViewById(R.id.imageButton2);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartScreen.this, Menus.class);
                startActivity(intent);
            }
        });

    }
}