package com.example.groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LaunchScreen extends AppCompatActivity {
    Button sign_up, vxod;
    ConstraintLayout sign_in;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);
        sign_in=findViewById(R.id.id_sign_in);
        sign_up= findViewById(R.id.button);
        auth = FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        users=db.getReference("Users");
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignIn();
            }
        });
    }
    private void showSignIn() {
        AlertDialog.Builder dialog = new AlertDialog. Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View sign_in_window = inflater.inflate(R.layout.activity_sign_in, null);
        dialog. setView(sign_in_window);
        final MaterialEditText name=sign_in_window.findViewById(R.id.username);
        final MaterialEditText password=sign_in_window.findViewById(R.id.password);
        vxod=findViewById(R.id.button_sign_in);
        vxod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString())) {
                    Snackbar.make(sign_in, "Введите имя пользователя!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().length() < 6) {
                    Snackbar.make(sign_in, "Введите пароль, который не больше 6 символов!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
//Авторизация пользователя
                auth.signInWithEmailAndPassword(name.getText().toString(),password.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(LaunchScreen.this, Test.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(sign_in,"Ошибка авторизации. "+e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });
        dialog.show();

}}