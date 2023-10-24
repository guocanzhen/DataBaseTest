package com.example.databasetest;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "BookStore.dp", null, 1);
        Button createDataBase = findViewById(R.id.create_database);
        Button addData = findViewById(R.id.add_data);
        Button updateData = findViewById(R.id.update_data);
        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.delete("Book", "price > ?", new String[]{"11"});
        });
        updateData.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("price", 10.88);
            db.update("Book", values, "name = ?", new String[]{"peace"});
        });
        addData.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", "peace");
            values.put("author", "mike");
            values.put("price", 16.98);
            values.put("pages", 233);
            db.insert("Book", null, values);

            values.put("name", "love");
            values.put("author", "mike");
            values.put("price", 16.98);
            values.put("pages", 233);
            db.insert("Book", null, values);
        });
        createDataBase.setOnClickListener(v -> {
        dbHelper.getWritableDatabase();
        });
    }
}