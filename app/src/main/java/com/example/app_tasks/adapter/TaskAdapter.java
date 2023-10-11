package com.example.app_tasks.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_tasks.R;
import com.example.app_tasks.model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {
    private List<Task> listTasks;
    public TaskAdapter(List<Task> list) {
        this.listTasks = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tasks_adapter, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Task task = listTasks.get(position);
        holder.task.setText(task.getNameTask());
    }

    @Override
    public int getItemCount() {
        return this.listTasks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView task;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            task = itemView.findViewById(R.id.textTask);
        }
    }
}
