package com.example.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

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
        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.query("Book", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
                    double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
                    int pages = cursor.getInt(cursor.getColumnIndexOrThrow("pages"));
                    Toast.makeText(this,"book name is " + name, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this,"book author is " + author, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this,"book price is " + price, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this,"book pages is " + pages, Toast.LENGTH_SHORT).show();
                } while (cursor.moveToNext());
            }
            cursor.close();
        });
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