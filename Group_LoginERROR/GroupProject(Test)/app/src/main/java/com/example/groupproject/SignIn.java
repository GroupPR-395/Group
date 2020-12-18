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

public class SignIn extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button test, reg, sign_up;
    ConstraintLayout sign;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        sign=findViewById(R.id.id_reg);
        test = findViewById(R.id.button_sign_up);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, Test.class);
                startActivity(intent);
            }
        });
        reg = findViewById(R.id.Register);
        auth = FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        users=db.getReference("Users");
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showRegister();
            }
        });
    }

    private void showRegister() {
        AlertDialog.Builder dialog = new AlertDialog. Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View reg_window = inflater.inflate(R.layout.activity_sign_up, null);
        dialog. setView(reg_window);

        final MaterialEditText name=reg_window.findViewById(R.id.new_username);
        final MaterialEditText email=reg_window.findViewById(R.id.editTextTextEmailAddress);
        final MaterialEditText password=reg_window.findViewById(R.id.new_password);
        final MaterialEditText check_pass=reg_window.findViewById(R.id.check_password);
        sign_up=findViewById(R.id.button_sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           if(TextUtils.isEmpty(name.getText().toString())){
               Snackbar.make(sign, "Введите имя пользователя!", Snackbar.LENGTH_SHORT).show();
               return;
           }
                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(sign, "Введите электронную почту!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(password.getText().toString().length()<6){
                    Snackbar.make(sign, "Введите пароль, который не больше 6 символов!", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(password.getText().toString()!=check_pass.getText().toString()){
                    Snackbar.make(sign, "Пароли не совпадают!", Snackbar.LENGTH_SHORT).show();
                    return;
                }
//регистрация пользователя
                auth.createUserWithEmailAndPassword(name.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User us=new User();
                                us.setName(name.getText().toString());
                                us.setEmail(email.getText().toString());
                                us.setPass(password.getText().toString());

                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(us)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Snackbar.make(sign, "Пользователь добавлен", Snackbar.LENGTH_SHORT ).show();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(sign,"Не получилось зарегистрироваться, возможно из-за уже существущего email. "+e.getMessage(), Snackbar.LENGTH_SHORT).show();

                    }
                });

            }
        });
        dialog.show();
    }
}