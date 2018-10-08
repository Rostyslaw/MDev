package com.frek2816.my_single_page_app_input_validation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.regex.*;

public class MainActivity extends AppCompatActivity {
    int errorEnterText = 0;
    EditText editFirstName;
    EditText editLostName;
    EditText email;
    EditText mobileNumber;
    EditText password;
    EditText passwordConfirm;
    TextView status;
    Button viewList;
    public static final Pattern PASSCPATTERN = Pattern.compile("[0-9!@#$%^&*a-zA-Z]{6,}");
    public static final Pattern PASSPATTERN = Pattern.compile("[0-9!@#$%^&*a-zA-Z]{6,}");
    public static final Pattern MOBILEPATTERN = Pattern.compile("\\+\\d{12}");
    public static final Pattern EMAILPATTERN = Pattern.compile("([a-z0-9]+)(\\.[a-z0-9]+)*@([a-z0-9]+)(\\.[a-z0-9]+)+$");
    public static final Pattern LOSTNAMEPATTERN = Pattern.compile("[a-zA-Zа-яА-Я]+$");
    public static final Pattern FIRSTNAMEPATTERN = Pattern.compile("[a-zA-Zа-яА-Я]+$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editFirstName = findViewById(R.id.editText);
        editLostName = findViewById(R.id.editText2);
        email = findViewById(R.id.editText3);
        mobileNumber = findViewById(R.id.editText4);
        password = findViewById(R.id.editText5);
        passwordConfirm = findViewById(R.id.editText6);
        status = findViewById(R.id.textView);
        viewList = findViewById(R.id.button2);
    }

    @SuppressLint("SetTextI18n")
    public void ViewList(View view) {
        //Перехід на наступну Activity
        Intent intent = new Intent(MainActivity.this, Users.class);
        startActivity(intent);
    }

    public void Send_text(View view) {
        errorEnterText = 0;
        status.setText("");
        EditFirstNameProcessed();
        EditLostNameProcessed();
        EditEmailProcessed();
        EditMobileNumberProcessed();
        EditPasswordProcessed();
        EditConfirmPasswordProcessed();

        if(errorEnterText == 0){
            String pass = password.getText().toString();
            String passC = passwordConfirm.getText().toString();

            if(!pass.equals(passC)){
                password.setError(getString(R.string.dontTrue));
                password.setText("");
                passwordConfirm.setError(getString(R.string.dontTrue));
                passwordConfirm.setText("");
            } else {
                status.setText(R.string.dataSent);
                SharedPreferences mPrefs = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                String phone = mobileNumber.getText().toString();
                prefsEditor.putString(phone, readVariables());
                prefsEditor.commit();
            }
        }else {
            if(errorEnterText == 1){
                status.setText(R.string.wrongField);
            } else if(errorEnterText > 1){
                status.setText(R.string.wrongFields);
            }
        }
    }


    void EditFirstNameProcessed(){
        String firstName = editFirstName.getText().toString();
        Matcher m = FIRSTNAMEPATTERN.matcher(firstName);

        if(!m.matches()){
            editFirstName.setError(getString(R.string.errorName));
            errorEnterText += 1;
        }
    }

    void EditLostNameProcessed(){
        String lostName = editLostName.getText().toString();
        Matcher m = LOSTNAMEPATTERN.matcher(lostName);

        if(!m.matches()){
            editLostName.setError(getString(R.string.errorLastname));
            errorEnterText += 1;
        }
    }

    void EditEmailProcessed(){
        String email_ = email.getText().toString();
        Matcher m = EMAILPATTERN.matcher(email_);

        if(!m.matches()){
            email.setError(getString(R.string.errorEmail));
            errorEnterText += 1;
        }
    }

    void EditMobileNumberProcessed(){
        String mobile = mobileNumber.getText().toString();
        Matcher m = MOBILEPATTERN.matcher(mobile);

        if(!m.matches()){
            mobileNumber.setError(getString(R.string.errorNumber));
            errorEnterText += 1;
        }
    }

    void EditPasswordProcessed(){
        String pass = password.getText().toString();
        Matcher m = PASSPATTERN.matcher(pass);

        if(!m.matches()){
            password.setError(getString(R.string.passError));
            password.setText("");
            errorEnterText += 1;
        }
    }

    void EditConfirmPasswordProcessed(){
        String passC = passwordConfirm.getText().toString();
        Matcher m = PASSCPATTERN.matcher(passC);

        if(!m.matches()){
            passwordConfirm.setError(getString(R.string.passError));
            passwordConfirm.setText("");
            errorEnterText += 1;
        }
    }

    private String readVariables() {
        String firstName = editFirstName.getText().toString();
        String lastName = editLostName.getText().toString();
        String phone = mobileNumber.getText().toString();
        String userInfo = firstName + "\n" + lastName + "\n" + phone;

        return userInfo;
    }
}