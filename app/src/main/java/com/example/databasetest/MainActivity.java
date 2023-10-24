package com.example.databasetest;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "BookStore.dp", null, 2);
        Button createDataBase = findViewById(R.id.create_database);
        createDataBase.setOnClickListener(v -> {
        dbHelper.getWritableDatabase();
        });
    }
}