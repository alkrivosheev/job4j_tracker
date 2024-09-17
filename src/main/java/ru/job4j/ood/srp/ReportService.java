package ru.job4j.ood.srp;

public interface ReportService {
    String generateReport(String title, String content);

    void printReport(String report);
}
