package com.example.medicly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegister extends AppCompatActivity {

    EditText firstname, lastname, username, userbirthday, password, repassword;
    Button signup, signin;
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        username = (EditText) findViewById(R.id.username);
        userbirthday = (EditText) findViewById(R.id.userbirthday);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        //signin = (TextView) findViewById(R.id.btnsignin);
        DB = new DBHandler(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String first = firstname.getText().toString();
                String last = lastname.getText().toString();
                String user = username.getText().toString();
                String bday = userbirthday.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                User userData = new User();
                userData.setFirstname(first);
                userData.setLastname(last);
                userData.setUsername(user);
                userData.setUserbirthday(bday);
                userData.setPassword(pass);


                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if(userData.getUsername().equals("")||userData.getPassword().equals("")||repass.equals("")||userData.getFirstname().equals("")||userData.getLastname().equals("")||userData.getUserbirthday().equals(""))
                    Toast.makeText(UserRegister.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(userData.getUsername().matches(emailPattern)){

                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(first, last, user, bday, pass);
                            if(insert){
                                Toast.makeText(UserRegister.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                firstname.setText("");
                                lastname.setText("");
                                username.setText("");
                                userbirthday.setText("");
                                password.setText("");
                                repassword.setText("");
                                Intent intent = new Intent(getApplicationContext(),UserLogin.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(UserRegister.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(UserRegister.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(UserRegister.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } else{
                        Toast.makeText(UserRegister.this, "Please use a valid email", Toast.LENGTH_SHORT).show();
                    }
                }}


        });



    }
    public void currentUser(View view) {
        Intent intentSign = new Intent (this, UserLogin.class);
        startActivity(intentSign);

    }
}
