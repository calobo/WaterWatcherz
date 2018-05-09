package com.example.water.waterwatcherz;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by krish on 4/12/2018.
 */


public class AddTaskActivity  extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner taskSpinner = findViewById(R.id.taskDropDown);
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        Bundle bundle = getIntent().getExtras();

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.tasks_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskSpinner.setAdapter(adapter1);
        taskSpinner.setOnItemSelectedListener(this);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"Database")
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2)
                .build();

    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        Integer week = 0;
        Integer month = 0;
        parent.getItemAtPosition(pos);
        EditText monthEnter = findViewById(R.id.monthEDT);
        EditText weekEnter = findViewById(R.id.weekEDT);
        if (!monthEnter.getText().toString().isEmpty()) {
             month = Integer.parseInt(monthEnter.getText().toString());
        }
        if(!weekEnter.getText().toString().isEmpty()) {
             week = Integer.parseInt(weekEnter.getText().toString());
        }

        String taskName = taskSpinner.getSelectedItem().toString();
        WaterTask waterTask = new WaterTask();
        waterTask.setTaskName(taskName);
        waterTask.setWeek(week);
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
