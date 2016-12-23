package com.netbigs.apps.moviesat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.text.TextUtilsCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends Activity {

    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mLoginBt;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();


        mEmailField=(EditText) findViewById(R.id.mailfield);
        mPasswordField=(EditText) findViewById(R.id.passfield);
        mLoginBt=(Button)findViewById(R.id.loginbutton);

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()!=null){

                    startActivity(new Intent(login.this,MainActivity.class));
                }
            }
        };


        mLoginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        startSignIn();
            }
        });
    }

    protected void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    private void startSignIn(){
        String email=mEmailField.getText().toString();
        String password=mPasswordField.getText().toString();

        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)) {
            Toast.makeText(login.this,"Fields are Empty",Toast.LENGTH_LONG).show();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(login.this,"Sign In Failed",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

}
