package com.example.app_tasks.helper;

import com.example.app_tasks.model.Task;

import java.util.List;

public interface ITaskDAO {
    public boolean saveData(Task task);
    public boolean updateData(Task task);
    public boolean deleteData(Task task);
    public List<Task> listData();
}
