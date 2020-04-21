package com.mingkang.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLogInActivity extends AppCompatActivity {

    private Button btnSignUp, btnLogIn;
    private EditText edtUserNameSignUp, edtUserNameLogIn, edtPasswordSignUp, edtPasswordLogIn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        edtUserNameSignUp = findViewById(R.id.edtUserNameSignUp);
        edtUserNameLogIn = findViewById(R.id.edtUserNameLogIn);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtPasswordLogIn = findViewById(R.id.edtPasswordLogIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null)
                            FancyToast.makeText(SignUpLogInActivity.this, edtUserNameSignUp.getText().toString()+" is signed up successfully!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                        else
                            FancyToast.makeText(SignUpLogInActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                    }
                });
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtUserNameLogIn.getText().toString(), edtPasswordLogIn.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null&&e==null)
                            FancyToast.makeText(SignUpLogInActivity.this, edtUserNameLogIn.getText().toString()+" is logged in successfully!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                        else
                            FancyToast.makeText(SignUpLogInActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                    }
                });
            }
        });
    }
}
