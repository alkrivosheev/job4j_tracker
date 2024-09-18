package ru.job4j.ood.srp.report;

import org.junit.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
public class SalaryDescendingReportTest {

    @Test
    public void whenGenerateReportThenSortedBySalaryDescending() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee emp1 = new Employee("John", now, now, 100);
        Employee emp2 = new Employee("Jane", now, now, 200);
        Employee emp3 = new Employee("Alice", now, now, 150);
        store.add(emp1);
        store.add(emp2);
        store.add(emp3);
        Report report = new SalaryDescendingReport(store);
        String expected = "Name; Salary;" + System.lineSeparator()
                + "Jane 200.0" + System.lineSeparator()
                + "Alice 150.0" + System.lineSeparator()
                + "John 100.0" + System.lineSeparator();
        String result = report.generate(employee -> true);
        assertThat(result, is(expected));
    }
}
