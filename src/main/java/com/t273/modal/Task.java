package com.t273.modal;

import java.time.LocalDate;

public class Task {
    public int id;
    public int userId;
    public String title;
    public String description;
    public int priority;
    public LocalDate deadline;
    public String status;

    public Task(int id, int userId, String title, String description, int priority, LocalDate deadline, String status) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.status = status;
    }

    public Task(String title, String description, int priority, LocalDate deadline, String status) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.status = status;
    }

    public Task() {}

    @Override
    public String toString() {
        return String.format("Task[id=%d, title=%s, priority=%d, deadline=%s, status=%s]",
                id, title, priority, deadline, status);
    }
}
