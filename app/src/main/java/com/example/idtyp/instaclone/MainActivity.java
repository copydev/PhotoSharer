package com.example.idtyp.instaclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    Boolean signUpModeActive = true;

    TextView username ;
    TextView password ;
    TextView changeSignUpModeTextView;
    Button signUpButton;
    RelativeLayout backgroundRelativeLayout ;
    ImageView logo;

    public void showUserList(){
        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
        startActivity(intent);

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.changeSignUpModeTextView){

            if(signUpModeActive){
                signUpButton.setText("Log in");
                changeSignUpModeTextView.setText("or, Sign Up");
                signUpModeActive = false;
            }
            else{
                signUpModeActive = true;
                signUpButton.setText("Sign Up");
                changeSignUpModeTextView.setText("or, Log in");
            }

        }

        else if(v.getId() == R.id.backgroundRelativeLayout || v.getId() == R.id.logo){

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("InstaClone");

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        changeSignUpModeTextView = findViewById(R.id.changeSignUpModeTextView);
        signUpButton = findViewById(R.id.signUpButton);
        backgroundRelativeLayout = findViewById(R.id.backgroundRelativeLayout);
        logo = findViewById(R.id.logo);

        changeSignUpModeTextView.setOnClickListener(this);

        password.setOnKeyListener(this);
        backgroundRelativeLayout.setOnClickListener(this);
        logo.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){
            //Toast.makeText(getApplicationContext(),"Error here",Toast.LENGTH_LONG).show();
            showUserList();
        }

        ParseAnalytics.trackAppOpenedInBackground(getIntent());


    }

    public void signUp(View view){

        if(username.getText().toString().equals("") || password.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Username or password is required", Toast.LENGTH_LONG).show();
        }
        else {
            if (signUpModeActive){
                ParseUser user = new ParseUser();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());


                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.i("Signup", "Successful");
                            showUserList();
                        } else {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
            else{
                ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null){
                            Log.i("Login","Successful");
                            showUserList();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == event.ACTION_DOWN) {

            signUp(v);

        }
        return false;
    }
}
