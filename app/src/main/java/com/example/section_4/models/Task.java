package com.example.section_4.models;

import java.util.UUID;

public class Task {
    private String id;
    private String title;
    private boolean isCompleted;

    public Task(String title) {
        this.id = UUID.randomUUID().toString(); // إنشاء معرف فريد
        this.title = title;
        this.isCompleted = false;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
