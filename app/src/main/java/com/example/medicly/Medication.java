package com.example.medicly;

public class Medication {
    private int medicationID;
    private String medicationName;
    private String medicationType;
    private String medicationDose;
    private String medicationMeasurement;
    private String medicationIntructions;
    private String medicationReminder;
    private String timepicker3;



    public Medication(int medicationID,String  medicationName, String medicationType, String medicationDose, String medicationMeasurement, String medicationIntructions,String medicationReminder,String timepicker3) {

        this.medicationID = medicationID ;
        this.medicationName = medicationName;
        this.medicationType = medicationType;
        this.medicationDose = medicationDose;
        this.medicationMeasurement = medicationMeasurement;
        this.medicationIntructions = medicationIntructions;
        this.medicationReminder = medicationReminder;
       this.timepicker3 = timepicker3;
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
        return timepicker3;
    }

    public void setTimepicker3(String timepicker3) {
        this.timepicker3 = timepicker3;
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
