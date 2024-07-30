package com.sqllite.example;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> task,id,desc;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView= findViewById(R.id.tv1);
        listView= findViewById(R.id.lv1);
        task = new ArrayList<>();
        DbHelper dbHelper = new DbHelper(MainActivity.this);
        dbHelper.insertTodo("TASK1","TASK DESC");
        dbHelper.insertTodo("TASK2","TASK DESC2");
        dbHelper.insertTodo("TASK3","TASK DESC3");
        dbHelper.insertTodo("TASK4","TASK DESC4");

        //fetching db in UI

        Cursor cursorInUi = dbHelper.readData();
        if(cursorInUi.getCount()==0){
            Toast.makeText(this, "DB is empty!!!", Toast.LENGTH_SHORT).show();
        }else{
            while(cursorInUi.moveToNext()){
                   Log.d("SQLdata", "onCreate: " + cursorInUi.getString(0));
                task.add(cursorInUi.getString(0));
                task.add(cursorInUi.getString(1));
                task.add(cursorInUi.getString(2));
                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(MainActivity.this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,task);
                listView.setAdapter(myAdapter);
            }
        }

    }
}