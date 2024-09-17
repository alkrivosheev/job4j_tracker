package ru.job4j.ood.srp;

public class ReportServiceImpl implements ReportService {
    @Override
    public String generateReport(String title, String content) {
        return "Отчет: " + title + "\n" + content;
    }

    @Override
    public void printReport(String report) {
        System.out.println(report);
    }
}
