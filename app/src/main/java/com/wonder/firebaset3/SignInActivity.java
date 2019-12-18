package com.wonder.firebaset3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private EditText mEmail,mPassword;
    private Button mSignIn,mRegister,mBack;
    private ProgressBar mprogressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);
        mAuth=FirebaseAuth.getInstance();

        mEmail=(EditText)findViewById(R.id.emil_txt);
        mPassword=(EditText)findViewById(R.id.password_txt);
        mSignIn=(Button)findViewById(R.id.signin_btn);
        mRegister=(Button)findViewById(R.id.register_btn);
        mBack=(Button)findViewById(R.id.back3_btn);
        mprogressBar=(ProgressBar)findViewById(R.id.progressBar2);
        mprogressBar.setVisibility(View.GONE);

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty())return;
                inProgress(true);
                mAuth.signInWithEmailAndPassword(mEmail.getText().toString(),mPassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(SignInActivity.this, "User Signned in", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignInActivity.this,BookListActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();return;
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(SignInActivity.this, "Sign in Falier", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty())return;
                inProgress(true);
                mAuth.createUserWithEmailAndPassword(mEmail.getText().toString(),mPassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(SignInActivity.this, "User Registerd", Toast.LENGTH_SHORT).show();
                                inProgress(false);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        inProgress(false);
                        Toast.makeText(SignInActivity.this, "Registerd  failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();return;
            }
        });
    }

    private void inProgress(boolean x){
        if (x){
            mprogressBar.setVisibility(View.VISIBLE);
            mBack.setEnabled(false);
            mSignIn.setEnabled(false);
            mRegister.setEnabled(false);
        }else{
            mprogressBar.setVisibility(View.GONE);
            mBack.setEnabled(true);
            mSignIn.setEnabled(true);
            mRegister.setEnabled(true);
        }
    }

    private boolean isEmpty(){
        if (TextUtils.isEmpty(mEmail.getText().toString())){
            mEmail.setError("REQUIRED!");
            return true;
        }
        if (TextUtils.isEmpty(mPassword.getText().toString())){
            mPassword.setError("REQUIRED!");
            return true;
        }
        return false;
    }
}
