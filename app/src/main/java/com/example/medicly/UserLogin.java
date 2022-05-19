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

        //variable set up, username == email, password == password, btnlogin == login button
        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        btnlogin = findViewById(R.id.btnsignin1);
        DB = new DBHandler(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //when login button is pressed, convert email and password to string
                String user = username.getText().toString();
                String pass = password.getText().toString();
                //if email and password == "" (empty), prompt user to enter all fields
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(UserLogin.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    //if email and password fields are not empty, call method to check username and password on the database to check if it is matching
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    //if method is successful, sign in user, create a successful toast message, and take user to homepage
                    if(checkuserpass==true){
                        Toast.makeText(UserLogin.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), MedicationHomepage.class);
                        startActivity(intent);
                    }else{
                        //otherwise, prompt user that email or password are incorrect with a toast
                        Toast.makeText(UserLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    //button for forgot password (placeholder)
    public void forgotPassword(View view) {
        Intent intentSign = new Intent (this, ForgotPassword.class);
        startActivity(intentSign);

    }
    //button for new user, navigates to user register page
    public void newUser(View view) {
        Intent intentSign = new Intent (this, UserRegister.class);
        startActivity(intentSign);

    }


}