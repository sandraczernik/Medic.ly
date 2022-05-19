package com.example.medicly;

public class Medication {
    //creation of variables relating to medication information, this class uses getters and setters so variables can be safely stored within this class,
    //and can only be retrieved using the relevant getter and setter methods()
    private int medicationID;
    private static String medicationName;
    private static String medicationType;
    private static String medicationDose;
    private static String medicationMeasurement;
    private static String medicationIntructions;
    private String medicationReminder;
    private String userReminderTime;



    public Medication(int medicationID,String  medicationName, String medicationType, String medicationDose, String medicationMeasurement, String medicationIntructions,String medicationReminder,String userReminderTime) {

        this.medicationID = medicationID ;
        this.medicationName = medicationName;
        this.medicationType = medicationType;
        this.medicationDose = medicationDose;
        this.medicationMeasurement = medicationMeasurement;
        this.medicationIntructions = medicationIntructions;
        this.medicationReminder = medicationReminder;
       this.userReminderTime = userReminderTime;
    }

    public Medication() {

    }

    public String getMedicationReminder() {
        return medicationReminder;
    }

    public void setMedicationReminder(String medicationReminder) {
        this.medicationReminder = medicationReminder;
    }

    public String getTimepicker3() {
        return userReminderTime;
    }

    public void setTimepicker3(String userReminderTime) {
        this.userReminderTime = userReminderTime;
    }

    public void setMedicationID(int medicationID) {
        this.medicationID = medicationID;
    }

    public int getMedicationID() {
        return medicationID;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getMedicationType() {
        return medicationType;
    }

    public void setMedicationType(String medicationType) {
        this.medicationType = medicationType;
    }

    public String getMedicationDose() {
        return medicationDose;
    }

    public void setMedicationDose(String medicationDose) {
        this.medicationDose = medicationDose;
    }

    public String getMedicationMeasurement() {
        return medicationMeasurement;
    }

    public void setMedicationMeasurement(String medicationMeasurement) {
        this.medicationMeasurement = medicationMeasurement;
    }

    public String getMedicationIntructions() {
        return medicationIntructions;
    }

    public void setMedicationIntructions(String medicationIntructions) {
        this.medicationIntructions = medicationIntructions;
    }
}
