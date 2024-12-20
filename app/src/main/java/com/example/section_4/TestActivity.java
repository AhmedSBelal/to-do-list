package com.example.section_4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.section_4.adapters.TaskAdapter;
import com.example.section_4.models.Task;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity implements TaskAdapter.OnTaskActionListener {

    private ArrayList<Task> tasks = new ArrayList<>();
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        EditText inputTask = findViewById(R.id.input_task);
        Button addTaskButton = findViewById(R.id.add_task_button);
        RecyclerView taskList = findViewById(R.id.task_list);

        taskAdapter = new TaskAdapter(tasks, this);
        taskList.setLayoutManager(new LinearLayoutManager(this));
        taskList.setAdapter(taskAdapter);

        addTaskButton.setOnClickListener(v -> {
            String taskText = inputTask.getText().toString().trim();
            if (!taskText.isEmpty()) {
                tasks.add(new Task(taskText));
                taskAdapter.notifyDataSetChanged();
                inputTask.setText("");
            } else {
                Toast.makeText(this, "Task cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onEditTask(Task task) {
        // فتح حوار تعديل المهمة
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Task");

        final EditText input = new EditText(this);
        input.setText(task.getTitle());
        builder.setView(input);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String newTitle = input.getText().toString().trim();
            if (!newTitle.isEmpty()) {
                task.setTitle(newTitle);
                taskAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Task cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    @Override
    public void onDeleteTask(Task task) {
        // حذف المهمة
        tasks.remove(task);
        taskAdapter.notifyDataSetChanged();
    }
}
