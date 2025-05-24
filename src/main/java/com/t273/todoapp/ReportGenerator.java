package com.t273.todoapp;

import com.t273.database.TaskDAO;

public class ReportGenerator {
    private final int userId;

    public ReportGenerator(int userId) {
        this.userId = userId;
    }

    public void generate() {
        try {
            int pending = TaskDAO.countTasksByStatus("pending", userId);
            int completed = TaskDAO.countTasksByStatus("completed", userId);

            System.out.println("\n📊 --- Task Report ---");
            System.out.println("🟡 Pending Tasks:   " + pending);
            System.out.println("✅ Completed Tasks: " + completed);
            System.out.println("--------------------------");
        } catch (Exception e) {
            System.err.println("❌ Error generating report: " + e.getMessage());
        }
    }
}
