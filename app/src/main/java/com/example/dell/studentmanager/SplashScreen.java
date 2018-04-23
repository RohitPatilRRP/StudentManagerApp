package com.example.dell.studentmanager;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

//    TextView t;
//    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,Login.class);
                startActivity(i);

//                t = findViewById(R.id.tagline);
//                iv = findViewById(R.id.logo);
//                Animation a = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.fadein);
//                t.startAnimation(a);
//                iv.startAnimation(a);
//                overridePendingTransition(R.anim.fadein,R.anim.fadeout);

                finish();
            }
        },2000);

    }
}
