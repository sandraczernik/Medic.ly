package com.example.medicly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class UserLogin extends AppCompatActivity {



    EditText username, password;
    Button btnlogin;
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        DB = new DBHandler(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(UserLogin.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(UserLogin.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), MedicationHomepage.class);
                       // System.out.println("USERNAME " + username.getText().toString());

                     //   int currentUserID = DB.getUserID(username.getText().toString());
                       // System.out.println("CURRENT USER ID =" + currentUserID);

                       // System.out.println("ID " + currentUserID);

                        //intent.putExtra("userID",currentUserID);
                        startActivity(intent);
                    }else{
                        Toast.makeText(UserLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    public void forgotPassword(View view) {
        Intent intentSign = new Intent (this, ForgotPassword.class);
        startActivity(intentSign);

    }

    public void newUser(View view) {
        Intent intentSign = new Intent (this, UserRegister.class);
        startActivity(intentSign);

    }


}