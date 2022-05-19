package com.example.medicly;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
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
    //creating elements based on exisitng elements in .xml page
    EditText medication_Name, medication_Dose, medication_Intructions;
    Button addMedicationButton;
    Spinner medication_Measurement, medication_Type;
    DatePicker medication_Reminder;
    TimePicker medication_Time;
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_medication);

        //assigning elements to variables
        addMedicationButton = findViewById(R.id.addMedicationBtn);
        medication_Name = findViewById(R.id.medicationName);
        medication_Dose = findViewById(R.id.dosageamount);
        medication_Intructions = findViewById(R.id.medicationInstructions);
        medication_Measurement = findViewById(R.id.dosagemeasurement);
        medication_Type = findViewById(R.id.medicationType);

        //dropdown menu created for medication types with the values available in strings.xml
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.medicationTypes, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        medication_Type.setAdapter(adapter2);

        //dropdown menu created for medication measurements with the values available in strings.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dosagemeasurements, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        medication_Measurement.setAdapter(adapter);

        //assigning both reminder date and time to variables
        medication_Reminder = findViewById(R.id.reminderDate);
        medication_Time = findViewById(R.id.reminderTime);
        //creating new database handler
        DB = new DBHandler(AddNewMedication.this);

        addMedicationButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override

            public void onClick(View v) {

                //creating newMedication objects
                Medication newMedication = new Medication();

                newMedication.setMedicationName(medication_Name.getText().toString());
                newMedication.setMedicationDose(medication_Dose.getText().toString());
                newMedication.setMedicationType(medication_Type.getSelectedItem().toString());
                newMedication.setMedicationMeasurement(medication_Measurement.getSelectedItem().toString());
                newMedication.setMedicationIntructions(medication_Intructions.getText().toString());

                //setting year month and day to integer variables
                int year = medication_Reminder.getYear();
                int month = medication_Reminder.getMonth();
                int day = medication_Reminder.getDayOfMonth();

                //retrieving calendar variables ready for notification reminder setup
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                newMedication.setMedicationReminder(format.format(calendar.getTime()));
                //creating notification channel based on method
                createNotificationChannel();

                //retrieving time
                int hour = medication_Time.getCurrentHour();
                int minute = medication_Time.getCurrentMinute();

                //setting alarm based the calendar input by the user, and converting it to milliseconds as it is needed for the reminder to
                //create a notification at a specific time - the default way to create reminders
                setAlarm(calendar.getTimeInMillis());

                //formatting time reminder so the hours and minutes are formatted like this 00:00
                newMedication.setTimepicker3(Integer.parseInt(Integer.toString(hour)) + ":" + Integer.parseInt(Integer.toString(minute)));

                //if medication name, type, dose, measurements, instructions etc are set to "" (empty), promp user to enter all fields, otherwise insert data
                //input by user to database
                if (newMedication.getMedicationName().equals("") || newMedication.getMedicationType().equals("") || newMedication.getMedicationDose().equals("") || newMedication.getMedicationMeasurement().equals("") || newMedication.getMedicationIntructions().equals("") || newMedication.getMedicationReminder().equals("") || newMedication.getTimepicker3().equals(""))
                    Toast.makeText(AddNewMedication.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean insertMed = DB.insertMedication(newMedication.getMedicationName(), newMedication.getMedicationType(), newMedication.getMedicationDose(), newMedication.getMedicationMeasurement(), newMedication.getMedicationType(), newMedication.getMedicationReminder(), newMedication.getTimepicker3());
                    //if medication has been inserted into the database, create toast message, and set relevant fields to empty for security reasons
                    if (insertMed) {
                        Toast.makeText(AddNewMedication.this, "New Medication has been added", Toast.LENGTH_SHORT).show();
                        medication_Name.setText("");
                        medication_Dose.setText("");
                        medication_Intructions.setText("");
                        //take user back to homepage if all executes successfully
                        Intent intent = new Intent(getApplicationContext(), MedicationHomepage.class);
                        startActivity(intent);
                    }
                }
            }
        }
        );

    }

    //method which passes in time in milliseconds to create a pending intent and to officially set the time reminder here
    private void setAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService((Context.ALARM_SERVICE));
        Intent intent = new Intent(this, BroadcastReceiverAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.set(AlarmManager.RTC, timeInMillis, pendingIntent);
        Toast.makeText(AddNewMedication.this, "Medication Reminder has been set", Toast.LENGTH_SHORT).show();
    }

    //notification channel creation, with high importance, meaning a heads-up notification will appear
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "UserNotificationChannel";
            String description = "Channel reminder";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("notifyUser", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }

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
        /*END of navigation*/
    }

}

















