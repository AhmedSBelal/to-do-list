package com.example.section_4.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.section_4.R;
import com.example.section_4.models.Task;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<Task> tasks;
    private OnTaskActionListener listener;

    public interface OnTaskActionListener {
        void onEditTask(Task task);
        void onDeleteTask(Task task);
    }

    public TaskAdapter(ArrayList<Task> tasks, OnTaskActionListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.taskTitle.setText(task.getTitle());
        holder.taskCheckbox.setChecked(task.isCompleted());

        // تعديل حالة المهمة عند النقر على CheckBox
        holder.taskCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> task.setCompleted(isChecked));

        // حدث التعديل
        holder.editTaskButton.setOnClickListener(v -> listener.onEditTask(task));

        // حدث الحذف
        holder.deleteTaskButton.setOnClickListener(v -> listener.onDeleteTask(task));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskTitle;
        CheckBox taskCheckbox;
        Button editTaskButton, deleteTaskButton;

        TaskViewHolder(View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.task_title);
            taskCheckbox = itemView.findViewById(R.id.task_checkbox);
            editTaskButton = itemView.findViewById(R.id.edit_task_button);
            deleteTaskButton = itemView.findViewById(R.id.delete_task_button);
        }
    }
}
