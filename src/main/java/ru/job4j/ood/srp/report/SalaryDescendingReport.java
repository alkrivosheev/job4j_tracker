package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.List;
import java.util.function.Predicate;
import java.util.Comparator;

public class SalaryDescendingReport implements Report {

    private final Store store;

    public SalaryDescendingReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;").append(System.lineSeparator());
        List<Employee> employees = store.findBy(filter);

        // Сортировка по зарплате в порядке убывания
        employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());

        for (Employee employee : employees) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
