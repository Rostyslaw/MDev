package com.frek2816.my_single_page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.enterName);
        textView = findViewById(R.id.printName);
    }

    public void NameConfirm(View view){
        String text = editText.getText().toString();
        textView.setText("Hello" + " " + text);
    }

    public void Clear(View view){
        editText.setText("");
        textView.setText("");
    }
}
