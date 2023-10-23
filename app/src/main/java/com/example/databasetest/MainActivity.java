package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "BookStore.dp", null, 1);
        Button createDataBase = findViewById(R.id.create_database);
        createDataBase.setOnClickListener(v -> {
        dbHelper.getWritableDatabase();
        });
    }
}