package com.example.dell.studentmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import static com.example.dell.studentmanager.SqliteDatabaseAdapter.*;

public class Signup extends AppCompatActivity {

    SqliteHelper sqliteHelper;
    EditText email, number, pass, cpass;
    Button reg;
    TextView login;
    boolean validEmail,validPassword,validNumber,samePassword;


//    boolean validPassword = false;
//    boolean validNumber = false;
//    boolean samePassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        sqliteHelper = new SqliteHelper(this);


        email = findViewById(R.id.userEmail);
        number = findViewById(R.id.userNumber);
        pass = findViewById(R.id.userPassword);
        cpass = findViewById(R.id.userCPassword);

        reg = findViewById(R.id.registerbtn);
        login = findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Signup.this,Login.class);
                startActivity(i);
            }
        });




        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailid = email.getText().toString();
                final String num = number.getText().toString();
                final String password = pass.getText().toString();
                final String cpassword = cpass.getText().toString();

//                if ( TextUtils.isEmpty(emailid) || TextUtils.isEmpty(num) || TextUtils.isEmpty(password) || TextUtils.isEmpty(cpassword) ){
                if (0 < email.getText().toString().length() && 0 < number.getText().toString().length() && 0 < pass.getText().toString().length() && 0 < cpass.getText().toString().length()) {

//                    final String password = pass.getText().toString();
//                    final String cpassword = cpass.getText().toString();

                    if (password.contentEquals(cpassword) ) {
                        samePassword = true;

                        //Toast.makeText(Signup.this,"Passwords match",Toast.LENGTH_SHORT).show();
                    }else {
                        samePassword = false;

                        Toast.makeText(Signup.this,"Passwords don't match",Toast.LENGTH_SHORT).show();
                    }

                    if (password.length() > 6){
                        validPassword = true;
                    }else {
                        validPassword = false;
                        Toast.makeText(Signup.this,"Password is too short",Toast.LENGTH_SHORT).show();
                    }

                    if (validateEmail()) {
                        validEmail = true;
                    }else {
                        validEmail = false;
                    }

                    if (validateNumber()) {
                        validNumber = true;
                    }else {
                        validNumber = false;
                    }

                    if (emailAlreadyPresent(emailid) && numberAlreadyPresent(num)){

                        if (samePassword && validNumber && validEmail && validPassword){


                            //Toast.makeText(Signup.this," "+emailid+" "+num+" "+password ,Toast.LENGTH_SHORT).show();

                            final boolean isCreated = sqliteHelper.insertData(emailid,num,password);

                            if (isCreated){
                                Toast.makeText(Signup.this,"Account Successfully Created!!!",Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(Signup.this,Login.class);
                                startActivity(i);
                            }else {
                                Toast.makeText(Signup.this,"Something went wrong!!!",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }else{
                        Toast.makeText(Signup.this,"Change Necessary Details!!!",Toast.LENGTH_SHORT).show();
                    }



                }
                else {
                    Toast.makeText(Signup.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    public boolean validateEmail() {

        String emails = email.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        // onClick of button perform this simplest code.
        if (emails.matches(emailPattern)) {
            //Toast.makeText(Signup.this, "valid email address", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(Signup.this, "Invalid email address", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

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


    private boolean emailAlreadyPresent(String emailid) {

        Cursor cursor = sqliteHelper.ifEmailExists(emailid);

        if (cursor.moveToFirst()) {
            String emails = cursor.getString(0);

            if (emails.equals(emailid)) {
                Toast.makeText(getApplicationContext(), "Email Already Exists!!!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }

    private boolean numberAlreadyPresent(String num) {

        Cursor cursor = sqliteHelper.ifNumExists(num);

        if (cursor.moveToFirst()) {

            String nums = cursor.getString(0);

           if (nums.equals(num)) {
                Toast.makeText(getApplicationContext(), "Phone Number Already Exists!!!", Toast.LENGTH_SHORT).show();
                return false;
           }

        }
        return true;
    }

}
