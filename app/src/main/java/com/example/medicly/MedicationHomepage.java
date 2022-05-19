package com.example.medicly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Typeface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;


public class MedicationHomepage extends AppCompatActivity {

    DBHandler DB;

    //creating array list of medication names
    private ArrayList<String> medicationName = new ArrayList<>();
    ListView medicationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_homepage);

        //formatting date to month, date and year
        String date_now = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        TextView date  = findViewById(R.id.dateTextView);
        date.setText(date_now);

        //medication list
        DB = new DBHandler(this);
        this.medicationName = DB.getMedicationNames();
        medicationView = findViewById(R.id.medicationView);

        //array adapter displays a list of elements within the array created in DBHandler.java with the use of a listview
        ArrayAdapter<String> showAdapter;
        showAdapter
         = new ArrayAdapter<String>(this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, medicationName);
        medicationView.setAdapter(showAdapter);

        /*START of navigation*/
        //NAVIGATION BUTTONS
        ImageButton addbtn =  findViewById(R.id.addbtn);
        ImageButton homepagebtn = findViewById(R.id.homepagebtn);
        ImageButton profilebtn = findViewById(R.id.profilebtn);
        ImageButton settingbtn = findViewById(R.id.settingbtn);
        ImageButton helpbutton = findViewById(R.id.helpbutton);


        //NAVIGATION
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(MedicationHomepage.this, AddNewMedication.class);

                startActivity(intent);
            }
        });
        homepagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(MedicationHomepage.this, MedicationHomepage.class);
                startActivity(intent);
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(MedicationHomepage.this, Profile.class);
                startActivity(intent);
            }
        });

        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(MedicationHomepage.this, Settings.class);
                startActivity(intent);
            }
        });

        helpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(MedicationHomepage.this, userHelp.class);
                startActivity(intent);
            }
        });
        /*END of navigation*/

    }}



