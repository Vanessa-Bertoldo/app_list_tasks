package com.example.app_tasks.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.app_tasks.R;
import com.example.app_tasks.adapter.TaskAdapter;
import com.example.app_tasks.helper.DbHelper;
import com.example.app_tasks.helper.RecyclerItemClickListener;
import com.example.app_tasks.helper.TaskDAO;
import com.example.app_tasks.model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private FloatingActionButton fab;
    private RecyclerView recyclerViewListTasks;
    private TaskAdapter taskAdapter;
    private List<Task> listTask = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertdataDatabase();

        fab                     = findViewById(R.id.addNewTask);
        recyclerViewListTasks   = findViewById(R.id.reyclerListTask);
        addNewTaskClick();
        clickListTasks();

    }

    public void insertdataDatabase(){
//        DbHelper db = new DbHelper(getApplicationContext());
//        ContentValues cv = new ContentValues();
//        cv.put("name", "Teste");
//        db.getWritableDatabase().insert("tasks", null, cv);
    }
    public void addNewTaskClick(){
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), AddTask.class);
                    startActivity(intent);
                }
            });
    }

    public void openPopUp(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.main_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        Toast.makeText(
                getApplicationContext(),
                "Item clicado" + menuItem,
                Toast.LENGTH_SHORT
        ).show();
        return true;
    }

    public void loadList(){
        //list task
        //list test
        /*
            Task task = new Task();
            task.setNameTask("Ir ao mercado");
            listTask.add(task);
        */
        //list to database
        TaskDAO taskDAO = new TaskDAO(getApplicationContext());
        listTask = taskDAO.listData();

        //config adapter
        taskAdapter = new TaskAdapter(listTask);
        //config reciclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewListTasks.setLayoutManager(layoutManager);
        recyclerViewListTasks.setHasFixedSize(true);
        recyclerViewListTasks.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerViewListTasks.setAdapter(taskAdapter);
    }

    @Override
    protected void onStart() {
        loadList();
        super.onStart();
    }

    public void clickListTasks(){ //evento de click das tarefas listadas pelo recycler view
        recyclerViewListTasks.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerViewListTasks,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "teste",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Log.i("clique", "onItemClick");

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );
    }
}