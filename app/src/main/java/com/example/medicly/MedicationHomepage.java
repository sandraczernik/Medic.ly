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

    //private HashMap<Integer,String> medicationList = new HashMap<>();
    private ArrayList<Integer> medicationNameID = new ArrayList<>();
    private ArrayList<String> medicationName = new ArrayList<>();

    ListView medicationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_homepage);


        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        TextView date  = (TextView) findViewById(R.id.dateTextView);
        date.setText(date_n);

        ImageButton addbtn = (ImageButton) findViewById(R.id.addbtn);
        ImageButton homepagebtn = (ImageButton) findViewById(R.id.homepagebtn);
        ImageButton profilebtn = (ImageButton) findViewById(R.id.profilebtn);
        ImageButton settingbtn = (ImageButton) findViewById(R.id.settingbtn);
        ImageButton helpbutton = (ImageButton) findViewById(R.id.helpbutton);

        DB = new DBHandler(this);

        //this.medicationNameID = DB.getMedicationID();
        this.medicationName = DB.getMedicationNames();
        //medicationList = DB.getMedicationNames();

        medicationView = findViewById(R.id.medicationView);
        ArrayAdapter<String> showAdapter;
        showAdapter
         = new ArrayAdapter<String>(this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, medicationName);
        //HashMap<Integer, String> adapter = new HashMap<Integer, String>(this, android.R.layout.simple_spinner_item, medicationList);
       // medicationView.setAdapter(new showAdapter2(showAdapter));
        medicationView.setAdapter(showAdapter);
        System.out.println("MEDICATION NAME" + this.medicationName);



       // recyclerviewList = findViewById(R.id.recyclerView);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(MedicationHomepage.this, AddNewMedication.class);

//                int currentUserID = getIntent().getExtras().getInt("userID");
//                System.out.println("TEST " + currentUserID);
//                intent.putExtra("userID",currentUserID);

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
                Intent intent = new  Intent(MedicationHomepage.this, MedicationHomepage.class);
                startActivity(intent);
            }
        });

        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(MedicationHomepage.this, MedicationHomepage.class);
                startActivity(intent);
            }
        });

        helpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(MedicationHomepage.this, MedicationHomepage.class);
                startActivity(intent);
            }
        });

    }



}