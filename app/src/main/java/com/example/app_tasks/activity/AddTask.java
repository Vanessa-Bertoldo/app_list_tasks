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
    private Task taskActual;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        editTask        = findViewById(R.id.txtTask);
        buttonSaveTask  = findViewById(R.id.buttonSaveTask);
        //recupera a tarefa caso seja edição
        taskActual      = (Task) getIntent().getSerializableExtra("taskSelected");
        //configurar tarefa na caixa de texto
        if(taskActual != null){
            editTask.setText(taskActual.getNameTask());
        }
        saveTask();
    }

    public void saveTask(){
        buttonSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTask = editTask.getText().toString();
                TaskDAO taskDAO = new TaskDAO(getApplicationContext());
                Task task = new Task();

                if(taskActual != null){ //update
                    if(!nameTask.isEmpty()){
                        task.setNameTask(nameTask);
                        task.setId(taskActual.getId());

                        //atualizar no banco de dados
                        if(taskDAO.updateData(task)){
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Sucesso ao salvar tarefa",
                                    Toast.LENGTH_SHORT
                            ).show();
                        } else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Erro ao salvar tarefa",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                } else { //insert
                    task.setNameTask(nameTask);
                    //taskDAO.saveData(task);
                    if(taskDAO.saveData(task)){
                        Toast.makeText(
                                getApplicationContext(),
                                "Sucesso ao salvar tarefa",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else {
                        Toast.makeText(
                                getApplicationContext(),
                                "Erro ao salvar tarefa",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
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