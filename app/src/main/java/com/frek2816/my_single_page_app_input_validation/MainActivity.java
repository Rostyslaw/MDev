package com.frek2816.my_single_page_app_input_validation;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    }




    @SuppressLint("SetTextI18n")

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
                password.setError("Паролі не співпадають");
                password.setText("");
                passwordConfirm.setError("Паролі не співпадають");
                passwordConfirm.setText("");
            }else {

                status.setText("Data sent");
            }

        }else {
            if(errorEnterText == 1){
                status.setText("Wrong field");
            }else if(errorEnterText > 1){
                status.setText("Wrong fields");
            }
        }


    }

    void EditFirstNameProcessed(){
        String firstName = editFirstName.getText().toString();
        Pattern p = Pattern.compile("[a-zA-Zа-яА-Я]+$");
        Matcher m = p.matcher(firstName);

        if(!m.matches()){
            editFirstName.setError("Ім'я не може містити розділові знаки та цифри");
            errorEnterText += 1;
        }

    }

    void EditLostNameProcessed(){
        String lostName = editLostName.getText().toString();
        Pattern p = Pattern.compile("[a-zA-Zа-яА-Я]+$");
        Matcher m = p.matcher(lostName);

        if(!m.matches()){
            editLostName.setError("Прізвище не може містити розділові знаки та цифри");
            errorEnterText += 1;
        }

    }

    void EditEmailProcessed(){
        String email_ = email.getText().toString();
        Pattern p = Pattern.compile("([a-z0-9]+)(\\.[a-z0-9]+)*@([a-z0-9]+)(\\.[a-z0-9]+)+$");
        Matcher m = p.matcher(email_);

        if(!m.matches()){
            email.setError("Заповніть поле та перевірте правильність вводу");
            errorEnterText += 1;
        }

    }

    void EditMobileNumberProcessed(){

        String mobile = mobileNumber.getText().toString();
        Pattern p = Pattern.compile("\\+\\d{12}");
        Matcher m = p.matcher(mobile);

        if(!m.matches()){
            mobileNumber.setError("Заповніть поле та перевірте правильність вводу");
            errorEnterText += 1;
        }


    }

    void EditPasswordProcessed(){
        String pass = password.getText().toString();
        Pattern p = Pattern.compile("[0-9!@#$%^&*a-zA-Z]{6,}");
        Matcher m = p.matcher(pass);

        if(!m.matches()){
            password.setError("Пароль має складатись більш ніж з 6 символів та не містити розділових знаків");
            password.setText("");
            errorEnterText += 1;
        }

    }

    void EditConfirmPasswordProcessed(){
        String passC = passwordConfirm.getText().toString();
        Pattern p = Pattern.compile("[0-9!@#$%^&*a-zA-Z]{6,}");
        Matcher m = p.matcher(passC);

        if(!m.matches()){
            passwordConfirm.setError("Пароль має складатись більш ніж з 6 символів та не містити розділових знаків");
            passwordConfirm.setText("");
            errorEnterText += 1;
        }

    }
}

