package com.mingkang.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnLogIn;
    private TextView txtSignUpButton;
    private EditText edtUsername, edtPassword, edtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsernameInLogIn);
        edtEmail = findViewById(R.id.edtEmailInLogin);
        edtPassword = findViewById(R.id.edtPasswordInLogin);
        btnLogIn = findViewById(R.id.btnLogInInLogin);
        txtSignUpButton = findViewById(R.id.txtSignUpButtonInLogin);

        btnLogIn.setOnClickListener(this);
        txtSignUpButton.setOnClickListener(this);

        if(ParseUser.getCurrentUser()!=null)
            transitionToSocialActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogInInLogin:
                ParseUser.logInInBackground(edtUsername.getText().toString(), edtPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null&&e==null) {
                            FancyToast.makeText(LoginActivity.this,
                                    edtUsername.getText() + " is logged in successfully.",
                                    Toast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                            transitionToSocialActivity();
                        }else
                            FancyToast.makeText(LoginActivity.this,
                                    e.getMessage(),
                                    Toast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                    }
                });
                break;
            case R.id.txtSignUpButtonInLogin:
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
                break;
        }
    }

    public void rootLayoutTapped(View v){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
    private void transitionToSocialActivity(){
        Intent intent = new Intent (LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
