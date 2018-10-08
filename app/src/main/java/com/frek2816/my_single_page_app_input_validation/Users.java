package com.frek2816.my_single_page_app_input_validation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Users extends AppCompatActivity {
    protected List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ViewList();
    }

    void ViewList(){
        list = new ArrayList<>();
        SharedPreferences mPrefs;
        ArrayAdapter<String> adapter;
        ListView lvMain;
        lvMain = findViewById(R.id.lvMain);
        mPrefs = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        Map<String, ?> map = mPrefs.getAll();

        for(Map.Entry<String,?> entry : map.entrySet()){
            String user = entry.getValue().toString();
            list.add(user);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lvMain.setAdapter(adapter);
    }
}


