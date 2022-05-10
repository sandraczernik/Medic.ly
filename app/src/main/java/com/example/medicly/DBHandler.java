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
    private int currentID;
    private int currentUser;
    private int currentMedicationID;
    private int userIDNext;
    public DBHandler(Context context) {
        super(context, "UserAccounts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table if not exists users(" +
                "userID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "firstname TEXT, " +
                "lastname TEXT, " +
                "username TEXT , " +
                "userbirthday TEXT, " +
                "password TEXT)"
        );

        MyDB.execSQL("create Table if not exists userMedications(" +
                "medicationID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "medicationName TEXT," +
                "medicationType TEXT, " +
                "medicationDose TEXT," +
                "medicationMeasurement TEXT," +
                "medicationIntructions TEXT," +
                "medicationReminder TEXT," +
                "timepicker3 TEXT)"
        );


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
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertMedication(String medicationName, String medicationType, String medicationDose, String medicationMeasurement, String medicationIntructions, String medicationReminder, String timepicker3) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("medicationName", medicationName);
        contentValues.put("medicationType", medicationType);
        contentValues.put("medicationDose", medicationDose);
        contentValues.put("medicationMeasurement", medicationMeasurement);
        contentValues.put("medicationIntructions", medicationIntructions);
        contentValues.put("medicationReminder", medicationReminder);
        contentValues.put("timepicker3", timepicker3);
        long result = MyDB.insert("userMedications", null, contentValues);
        System.out.println("result" + result);
        if (result == -1) return false;
        else
            return true;
    }


//    public Boolean innerjoinTable(int medicationID, int userID){
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("medicationID", medicationID);
//        contentValues.put("userID", userID);
//        long result = MyDB.insert("userMedication", null, contentValues);
//        System.out.println("result" + result);
//        if (result == -1) return false;
//        else
//            return true;
//    }



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

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


//    public int getNextMedicationID() {
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        //String query = "Select medicationID from userMedications ORDER BY medicationID DESC LIMIT 1;";
//        Cursor cursor = MyDB.rawQuery("Select medicationID from userMedications ORDER BY medicationID DESC LIMIT 1;", null);
//        cursor.moveToFirst();
//        if (cursor.moveToFirst()) {
//        int currentID = cursor.getInt(cursor.getInt(0));
//            System.out.println(cursor.getInt(currentID));
//        }
//        cursor.close();
//        return currentID +1 ;
//
//    }
//
    public int getUserID(String userID) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String query = "Select username from users WHERE userID = \"" + userID + "\";";
        int currentUser = -1;
        Cursor cursor2 = MyDB.rawQuery(query, null);
        if (cursor2.moveToFirst()) {
            currentUser = cursor2.getInt(0);

        }
        cursor2.close();
        System.out.println(currentUser);
        return currentUser;

    }

//    }
//    public ArrayList<Integer> getMedicationID() {
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        String query = "Select medicationID,medicationName from userMedications";
//        Cursor cursor2 = MyDB.rawQuery(query, null);
//        //HashMap<Integer,String> medicationList = new HashMap<>();
//        ArrayList<Integer> listMedications = new ArrayList<>();
//        if (cursor2.moveToFirst()) {
//            currentMedicationID = cursor2.getInt(0);
//            listMedications.add(currentMedicationID);
//            cursor2.moveToNext();
//        }
//
//        return listMedications;
//
//    }

//}
//    public int getUserID() {
//        SQLiteDatabase MyDB = this.getReadableDatabase();
//        String query = "Select userID from users  WHERE userID = \"" + userID + "\";";
//        Cursor cursor2 = MyDB.rawQuery(query, null);
//        if (cursor2.moveToFirst()) {
//            userIDNext = cursor2.getInt(0);
//            cursor2.moveToNext();
//        }
//
//        return userIDNext;
//    }
//


    public ArrayList<String> getMedicationNames() {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String query = "Select medicationName, medicationType,medicationDose, medicationMeasurement,timepicker3, medicationReminder from userMedications";
        Cursor cursor2 = MyDB.rawQuery(query, null);

        //HashMap<Integer,String> medicationList = new HashMap<>();
        ArrayList<String> listMedications = new ArrayList<>();
        if (cursor2.moveToFirst()) {
            do{
                String currentMedicationName = cursor2.getString(0);
                String currentMedicationType = cursor2.getString(1);
                String currentmedicationDose = cursor2.getString(2);
                String currentmedicationMeasurement = cursor2.getString(3);
                String currenttimepicker3 = cursor2.getString(4);
                String currentmedicationReminder = cursor2.getString(5);
                String oneMedication = currentMedicationName + " | " + currentMedicationType + " | " + currentmedicationDose + currentmedicationMeasurement + "     |     " + currenttimepicker3;
                listMedications.add("\n" + currentmedicationReminder);
                listMedications.add(oneMedication);
            } while (cursor2.moveToNext());
        }
        cursor2.close();
        return listMedications;

    }


}
