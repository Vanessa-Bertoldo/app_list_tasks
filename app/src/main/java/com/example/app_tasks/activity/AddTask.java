package com.example.app_tasks.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.app_tasks.R;
import com.example.app_tasks.helper.TaskDAO;
import com.example.app_tasks.model.Task;
import com.google.android.material.textfield.TextInputEditText;

public class AddTask extends AppCompatActivity {
    private TextInputEditText editTask;
    private ImageButton buttonSaveTask;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        editTask        = findViewById(R.id.txtTask);
        buttonSaveTask  = findViewById(R.id.buttonSaveTask);
        saveTask();
    }


    public void saveTask(){
        buttonSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTask = editTask.getText().toString();
                if(!nameTask.isEmpty()){
                    TaskDAO taskDAO = new TaskDAO(getApplicationContext());
                    Task task = new Task();
                    task.setNameTask(nameTask);
                    taskDAO.saveData(task);
                }
                finish();
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate((R.menu.main_menu));
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.save:
//                Toast.makeText(
//                        getApplicationContext(),
//                        "save habilitation",
//                        Toast.LENGTH_SHORT
//                ).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}