package com.example.medicly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    //main activity when user opens application, includes button to register a new user
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registerBtn(View view) {
        Intent intentLogIn = new Intent(this, UserRegister.class);
        startActivity(intentLogIn);
    }
}