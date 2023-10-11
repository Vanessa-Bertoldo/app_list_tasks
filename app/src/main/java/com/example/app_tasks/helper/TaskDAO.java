package com.example.app_tasks.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.app_tasks.model.Task;

import java.util.ArrayList;
import java.util.List;

//DAO é o padrão utilizado para manipulação de dados
//Os metodos CRUD serão desenvolvidos aqui
public class TaskDAO implements ITaskDAO{
    private SQLiteDatabase write;
    private SQLiteDatabase read;
    public TaskDAO(Context context){
        DbHelper db     = new DbHelper(context);
        write           = db.getWritableDatabase(); //insere dados em uma tabela
        read            = db.getReadableDatabase(); //le os dados de uma tabela
    }
    @Override
    public boolean saveData(Task task) {
        ContentValues cv = new ContentValues(); //pega o nome da coluna da tabela
        cv.put("name", task.getNameTask()); //nome, valor
        try{
            read.insert(DbHelper.TABLE_TASKS, null, cv);
            Log.e("INFO", "Tarefa salva com sucesso ");
        } catch (Exception e){
            Log.e("INFO", "Erro ao salvar tarefa " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateData(Task task) {
        return false;
    }

    @Override
    public boolean deleteData(Task task) {
        return false;
    }

    @Override
    public List<Task> listData() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TABLE_TASKS + ";";
        Cursor cursor = read.rawQuery(sql, null); //o 2 parametro são os paramentros condicionais de filtro
        while(cursor.moveToNext()){ //o cursor faz com que as linhas sejam percorridas
            Task task = new Task();
            @SuppressLint("Range") Long id     = cursor.getLong(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));

            task.setId(id);
            task.setNameTask(name);

            tasks.add(task);
        }
        return tasks;
    }
}
