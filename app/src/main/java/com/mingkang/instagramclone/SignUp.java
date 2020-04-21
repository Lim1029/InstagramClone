package com.mingkang.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button btnSignUp;
    private TextView txtLogInButton;
    private EditText edtUsername, edtPassword, edtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtUsername = findViewById(R.id.edtUsernameInSignUp);
        edtEmail = findViewById(R.id.edtEmailInSignUp);
        edtPassword = findViewById(R.id.edtPasswordInSignUp);
        btnSignUp = findViewById(R.id.btnSignUpInSignUp);
        txtLogInButton = findViewById(R.id.txtLogInButtonInSignUp);

        btnSignUp.setOnClickListener(this);
        txtLogInButton.setOnClickListener(this);
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN)
                    onClick(btnSignUp);
                return false;
            }
        });

        if(ParseUser.getCurrentUser()!=null)
            transitionToSocialActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignUpInSignUp:
                if(edtEmail.getText().toString().equals("")||edtUsername.getText().toString().equals("")||edtPassword.getText().toString().equals(""))
                    FancyToast.makeText(SignUp.this,
                            "email, username, password is required",
                            Toast.LENGTH_SHORT,FancyToast.INFO,false).show();
                else {
                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());
                    appUser.setUsername(edtUsername.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing Up " + edtUsername.getText().toString());
                    progressDialog.show();
                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUp.this,
                                        edtUsername.getText() + " is signed up successfully.",
                                        Toast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                                transitionToSocialActivity();
                            } else {
                                FancyToast.makeText(SignUp.this,
                                        e.getMessage(),
                                        Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }
                break;
            case R.id.txtLogInButtonInSignUp:
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void rootLayoutTapped(View v){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }

    private void transitionToSocialActivity(){
        Intent intent = new Intent (SignUp.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
