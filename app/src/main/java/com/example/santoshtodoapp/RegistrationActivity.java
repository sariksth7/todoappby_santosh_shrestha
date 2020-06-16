package com.example.santoshtodoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText emailReg,passwordReg;
    private Button btnRegister, btnLogin;

    //Firebase authentcation
    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Registration();

        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);
    }


    private void Registration(){
        emailReg = findViewById(R.id.email_reg);
        passwordReg = findViewById(R.id.reg_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_reg);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailReg.getText().toString().trim();
                String password = passwordReg.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    emailReg.setError("Required");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    passwordReg.setError("Required");
                    return;
                }

                mDialog.setMessage("Registration Processing");
                mDialog.show();



                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                        mDialog.dismiss();
                                    }


                        else {
                            Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }
                    }
                });
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),LoginActivity.class));

            }
        });
    }

}
