package com.example.medicly;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNewMedication extends AppCompatActivity {
    EditText medication_Name, medication_Type, medication_Dose, medication_Intructions;
    Button addMedicationButton;
    Spinner medication_Measurement;
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
        medication_Type = findViewById(R.id.medicationType);
        medication_Dose = findViewById(R.id.dosageamount);
        medication_Intructions = findViewById(R.id.medicationInstructions);
        medication_Measurement = findViewById(R.id.dosagemeasurement);
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
                newMedication.setMedicationType(medication_Type.getText().toString());
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


                //TIME
                int hour = timepicker.getCurrentHour();
                int minute = timepicker.getCurrentMinute();
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
                    medication_Type.setText("");
                    medication_Dose.setText("");
                    medication_Intructions.setText("");
                    Intent intent = new Intent(getApplicationContext(),MedicationHomepage.class);
                    startActivity(intent);
                }}}
            });
            }
    }

















