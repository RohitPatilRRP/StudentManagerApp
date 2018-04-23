package com.example.dell.studentmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements TextWatcher,CompoundButton.OnCheckedChangeListener{


    EditText number,pass;
    TextView backtosignup;
    Button login;
    boolean validPassword,validNumber;
    //SQLiteDatabase db;
    SqliteHelper sqliteHelper;
    CheckBox rememberMe;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        sqliteHelper = new SqliteHelper(this);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        number = findViewById(R.id.userLoginNumber);
        pass = findViewById(R.id.userLoginPassword);
        login = findViewById(R.id.loginbtn);
        backtosignup = findViewById(R.id.gotosignup);
        rememberMe = findViewById(R.id.rememberMe);

        if(sharedPreferences.getBoolean(KEY_REMEMBER, false))
            rememberMe.setChecked(true);
        else
            rememberMe.setChecked(false);

        number.setText(sharedPreferences.getString(KEY_USERNAME,""));
        pass.setText(sharedPreferences.getString(KEY_PASS,""));

        number.addTextChangedListener(this);
        pass.addTextChangedListener(this);
        rememberMe.setOnCheckedChangeListener(this);


        backtosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Signup.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( 0 < number.getText().toString().length() && 0 < pass.getText().toString().length()){

                    final String num = number.getText().toString();
                    final String password = pass.getText().toString();

                    if (validateNumber()){
                        validNumber = true;
                    }
                    else{
                        validNumber = false;
                    }

                    if (password.length() > 6){
                        validPassword = true;
                    }else {
                        validPassword = false;
                        Toast.makeText(Login.this,"Password is too short",Toast.LENGTH_SHORT).show();
                    }

                    if (validNumber && validPassword){
                        //Toast.makeText(Login.this, "Valid number and password!!!", Toast.LENGTH_SHORT).show();
                        if (numbersAlreadyPresent(num)){

                            if (passwordForTheNumberIsCorrect(num ,password)) {
                                Toast.makeText(Login.this, "Welcome to Student Manager App", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(Login.this, Index.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                                finish();
                            }
                        }

                    }

                }
                else{
                    Toast.makeText(Login.this,"Please enter all the details",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }//end of OnCreate();


    public boolean validateNumber(){
        String nums = number.getText().toString();

        String numPattern = "^[7-9][0-9]{9}$";

// onClick of button perform this simplest code.
        if (nums.matches(numPattern)) {
            //Toast.makeText(getApplicationContext(), "valid phone number", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Invalid phone number", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private boolean numbersAlreadyPresent(String num) {

        Cursor cursor = sqliteHelper.ifNumExists(num);

        if (cursor.moveToFirst()) {
            String nums = cursor.getString(0);
            //Toast.makeText(Login.this, "I'm in!!! "+nums, Toast.LENGTH_SHORT).show();

            if (nums.equals(num)) {
                //Toast.makeText(getApplicationContext(), "Phone Number Already Exists!!!", Toast.LENGTH_SHORT).show();
                return true;
            }
        } else {
            Toast.makeText(Login.this, "Number doesn't Exist!!!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return false;
    }

    public boolean passwordForTheNumberIsCorrect(String num,String pass){

        Cursor cursor = sqliteHelper.ifPasswordForTheNumberIsCorrect(num,pass);

        if (cursor.moveToFirst()) {
            String nums = cursor.getString(0);
            String password = cursor.getString(1);

            //Toast.makeText(Login.this,nums+" "+password,Toast.LENGTH_SHORT).show();

            if ( password.equals(pass)){
                return true;
            }else {
                Toast.makeText(Login.this, "Incorrect Password!!!", Toast.LENGTH_SHORT).show();
            }

        }

        return false;
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        managePrefs();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        managePrefs();
    }

    private void managePrefs(){
        if(rememberMe.isChecked()){
            editor.putString(KEY_USERNAME, number.getText().toString().trim());
            editor.putString(KEY_PASS, pass.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
            //Toast.makeText(this,"Checked In",Toast.LENGTH_SHORT).show();
        }else{
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);//editor.putString(KEY_PASS,"");
            editor.remove(KEY_USERNAME);//editor.putString(KEY_USERNAME, "");
            editor.apply();
            //Toast.makeText(this,"Not Checked In",Toast.LENGTH_SHORT).show();

        }
    }
}

//

