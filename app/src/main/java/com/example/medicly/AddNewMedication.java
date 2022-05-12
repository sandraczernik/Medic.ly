package com.example.medicly;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNewMedication extends AppCompatActivity {
    EditText medication_Name, medication_Dose, medication_Intructions;
    Button addMedicationButton;
    Spinner medication_Measurement, medication_Type;
    DatePicker medication_Reminder;
    TimePicker timepicker;
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_medication);

//        int currentUserID = getIntent().getIntExtra("userID", -1);
//
//       // int currentUser = Integer.parseInt(currentUserID);
//        System.out.println("CURRENT USER ID" + currentUserID);

        addMedicationButton = findViewById(R.id.addMedicationBtn);
        medication_Name = findViewById(R.id.medicationName);
        medication_Dose = findViewById(R.id.dosageamount);
        medication_Intructions = findViewById(R.id.medicationInstructions);
        medication_Measurement = findViewById(R.id.dosagemeasurement);
        medication_Type = findViewById(R.id.medicationType);

        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this, R.array.medicationTypes, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        medication_Type.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.dosagemeasurements, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        medication_Measurement.setAdapter(adapter);


        medication_Reminder = (DatePicker)findViewById(R.id.reminder);
        timepicker = (TimePicker) findViewById(R.id.time_picker);

        DB = new DBHandler(AddNewMedication.this);

        addMedicationButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override

            public void onClick(View v) {


                Medication newMedication = new Medication();


               // newMedication.setMedicationID(DB.getNextMedicationID());
                newMedication.setMedicationName(medication_Name.getText().toString());
                newMedication.setMedicationDose(medication_Dose.getText().toString());
                newMedication.setMedicationType(medication_Type.getSelectedItem().toString());
                newMedication.setMedicationMeasurement(medication_Measurement.getSelectedItem().toString());
                newMedication.setMedicationIntructions(medication_Intructions.getText().toString());
                //String medicationMeasurement  = medication_Measurement.getSelectedItem().toString();
                // below line is to get data from all edit text fields.
                //String medicationName = medication_Name.getText().toString();
                // String medicationType = medication_Type.getText().toString();
                // String medicationDose = medication_Dose.getText().toString();
                // String medicationIntructions = medication_Intructions.getText().toString();
                ////////////////////////////// String medicationReminder = DateFormat.getDateInstance().format(medication_Reminder.getCalendarView().getDate());
                //DATE




                int year = medication_Reminder.getYear();
                int month = medication_Reminder.getMonth();
                int day = medication_Reminder.getDayOfMonth();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year,month,day);
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                //String medicationReminder = format.format(calendar.getTime());
                newMedication.setMedicationReminder(format.format(calendar.getTime()));
                createNotificationChannel();

                //TIME
                int hour = timepicker.getCurrentHour();
                int minute = timepicker.getCurrentMinute();

                setAlarm(calendar.getTimeInMillis());
                //String timepicker3 = Integer.parseInt(Integer.toString(hour)) + ":" + Integer.parseInt(Integer.toString(minute));
                newMedication.setTimepicker3(Integer.parseInt(Integer.toString(hour)) + ":" + Integer.parseInt(Integer.toString(minute)));
                System.out.println("DOSE" + newMedication.getMedicationDose());
                System.out.println("NAME" + newMedication.getMedicationName());
                System.out.println("ID" + newMedication.getMedicationID());
                System.out.println("MSRMT" + newMedication.getMedicationMeasurement());
                System.out.println("RMDR" + newMedication.getMedicationReminder());
               System.out.println("INSTRS" + newMedication.getMedicationIntructions());
                System.out.println("TYPE" + newMedication.getMedicationType());

                if (newMedication.getMedicationName().equals("") || newMedication.getMedicationType().equals("") || newMedication.getMedicationDose().equals("") || newMedication.getMedicationMeasurement().equals("") || newMedication.getMedicationIntructions().equals("") || newMedication.getMedicationReminder().equals("")||newMedication.getTimepicker3().equals(""))
                    Toast.makeText(AddNewMedication.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean insertMed = DB.insertMedication(newMedication.getMedicationName(), newMedication.getMedicationType(), newMedication.getMedicationDose(),  newMedication.getMedicationMeasurement(), newMedication.getMedicationType(), newMedication.getMedicationReminder(), newMedication.getTimepicker3());

                    //Boolean joinTable = DB.innerjoinTable(newMedication.getMedicationID(), currentUserID);
                    System.out.println(insertMed);
                    if(insertMed  ){
                    Toast.makeText(AddNewMedication.this, "New Medication has been added", Toast.LENGTH_SHORT).show();
                    medication_Name.setText("");
                    medication_Dose.setText("");
                    medication_Intructions.setText("");
                    Intent intent = new Intent(getApplicationContext(),MedicationHomepage.class);
                    startActivity(intent);
                }}}
            }


            );

            }

    private void setAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService((Context.ALARM_SERVICE));
        Intent intent = new Intent(this, BroadcastReceiverAlarm.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        alarmManager.set(AlarmManager.RTC, timeInMillis, pendingIntent);

        Toast.makeText(AddNewMedication.this, "Medication Reminder has been set", Toast.LENGTH_SHORT).show();
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "UserNotificationChannel";
            String description = "Channel reminder";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("notifyUser", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }


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
                Intent intent = new Intent(AddNewMedication.this, AddNewMedication.class);

                startActivity(intent);
            }
        });
        homepagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewMedication.this, MedicationHomepage.class);
                startActivity(intent);
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewMedication.this, Profile.class);
                startActivity(intent);
            }
        });

        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewMedication.this, Settings.class);
                startActivity(intent);
            }
        });

        helpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewMedication.this, userHelp.class);
                startActivity(intent);
            }
        });
    }

}

















