package com.example.medicly;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DBNAME = "MedicationApp";
    //creation of DBHandler, used on all pages dealing with database functions
    public DBHandler(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        //creating user table
        MyDB.execSQL("create Table if not exists users(" +
                "userID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "firstname TEXT, " +
                "lastname TEXT, " +
                "username TEXT , " +
                "userbirthday TEXT, " +
                "password TEXT)"
        );

        //creating medications table
        MyDB.execSQL("create Table if not exists userMedications(" +
                "medicationID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "medicationName TEXT," +
                "medicationType TEXT, " +
                "medicationDose TEXT," +
                "medicationMeasurement TEXT," +
                "medicationIntructions TEXT," +
                "medicationReminder TEXT," +
                "userReminderTime TEXT)"
        );

        //linking user medicationID to userID by the use of foreign keys for increased security
        MyDB.execSQL("CREATE TABLE IF NOT EXISTS userMedication(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "medicationID INTEGER," +
                "userID INTEGER," +
                "FOREIGN KEY(medicationID) REFERENCES userMedications(medicationID)," +
                "FOREIGN KEY(userID) REFERENCES users(userID))"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        //if table exists currently in the database, do not create a new one
        MyDB.execSQL("drop Table if exists users");
    }

    //insert medication function, including all values based on user input
    public Boolean insertMedication(String medicationName, String medicationType, String medicationDose, String medicationMeasurement, String medicationIntructions, String medicationReminder, String userReminderTime) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("medicationName", medicationName);
        contentValues.put("medicationType", medicationType);
        contentValues.put("medicationDose", medicationDose);
        contentValues.put("medicationMeasurement", medicationMeasurement);
        contentValues.put("medicationIntructions", medicationIntructions);
        contentValues.put("medicationReminder", medicationReminder);
        contentValues.put("userReminderTime", userReminderTime);
        long result = MyDB.insert("userMedications", null, contentValues);
        System.out.println("result" + result);
        if (result == -1) return false;
        else
            return true;
    }



    //insert user data based on user input
    public Boolean insertData(String firstname, String lastname, String username, String userbirthday, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("username", username);
        contentValues.put("userbirthday", userbirthday);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        System.out.println("result" + result);
        if (result == -1) return false;
        else
            return true;
    }

    //method to check if username currently exists in the database
    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //method to check the password against the username, if correct the user will be logged in
    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //method which created an array of relevant medication information, to be displayed on the homepage
    public ArrayList<String> getMedicationNames() {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String query = "Select medicationName, medicationType,medicationDose, medicationMeasurement,userReminderTime, medicationReminder from userMedications";
        Cursor cursor2 = MyDB.rawQuery(query, null);
        ArrayList<String> listMedications = new ArrayList<>();
        //cursor moves to each column in database, and retrieved values from columns based on numbers 0 to 5
        if (cursor2.moveToFirst()) {
            do{
                String currentMedicationName = cursor2.getString(0);
                String currentMedicationType = cursor2.getString(1);
                String currentmedicationDose = cursor2.getString(2);
                String currentmedicationMeasurement = cursor2.getString(3);
                String currenttimepicker3 = cursor2.getString(4);
                String currentmedicationReminder = cursor2.getString(5);
                //concateing all strings of information about a medication together to display as a list
                String oneMedication = currentMedicationName + " | " + currentMedicationType + " | " + currentmedicationDose + currentmedicationMeasurement + "     |     " + currenttimepicker3;
                listMedications.add("\n" + currentmedicationReminder);
                listMedications.add(oneMedication);
                //cursor moves to next value until all medications within the database are displayed
            } while (cursor2.moveToNext());
        }
        cursor2.close();
        return listMedications;

    }


}
