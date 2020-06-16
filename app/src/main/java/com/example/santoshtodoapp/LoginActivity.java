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

import com.example.santoshtodoapp.tasks.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Button btnLogin,btnRegistration;

    private FirebaseAuth mAuth;


    //Progress dialog
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mDialog = new ProgressDialog(this);

        LoginFunction();
    }

    private void LoginFunction(){
        email=findViewById(R.id.email_login);
        pass=findViewById(R.id.login_password);
        btnLogin=findViewById(R.id.btn_login);
        btnRegistration=findViewById(R.id.btn_reg);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mEmail = email.getText().toString().trim();
                String password = pass.getText().toString().trim();

                if (TextUtils.isEmpty(mEmail)){
                    email.setError("Required");
                }

                if (TextUtils.isEmpty(password)){
                    pass.setError("Required");
                }

                mDialog.setMessage("Processing");
                mDialog.show();

                mAuth.signInWithEmailAndPassword(mEmail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                                Toast.makeText(getApplicationContext(),"Successfully logged in",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));



                            mDialog.dismiss();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });





        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));

            }
        });



    }
}
