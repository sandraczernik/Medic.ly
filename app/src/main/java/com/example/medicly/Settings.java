package com.example.medicly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button buttonlogout, deleteAccount;
        //two buttons created, log out and delete, when clicked will take user to relevant pages and create a toast message
        buttonlogout = findViewById(R.id.buttonlogout);
        deleteAccount = findViewById(R.id.deleteaccount);

        buttonlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Settings.this, "You have been logged out", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(Settings.this, UserRegister.class);
                startActivity(intent);
            }
        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Settings.this, "You have successfully deleted an account", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(Settings.this, UserRegister.class);
                startActivity(intent);
            }
        });

        /*START of navigation*/
        //NAVIGATION BUTTONS
        ImageButton addbtn = findViewById(R.id.addbtn);
        ImageButton homepagebtn = findViewById(R.id.homepagebtn);
        ImageButton profilebtn = findViewById(R.id.profilebtn);
        ImageButton settingbtn = findViewById(R.id.settingbtn);
        ImageButton helpbutton = findViewById(R.id.helpbutton);

        //NAVIGATION
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, AddNewMedication.class);

                startActivity(intent);
            }
        });
        homepagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, MedicationHomepage.class);
                startActivity(intent);
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, Profile.class);
                startActivity(intent);
            }
        });

        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, Settings.class);
                startActivity(intent);
            }
        });

        helpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, userHelp.class);
                startActivity(intent);
            }
        });
        /*END of navigation*/


    }
}