package ru.job4j.ood.srp.report;

import org.junit.Test;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import java.util.Calendar;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CSVReportTest {

    @Test
    public void whenGenerateCSVReport() {
        MemoryStore store = new MemoryStore();
        Calendar hired = Calendar.getInstance();
        hired.set(2020, Calendar.JANUARY, 1, 8, 0, 0);
        Calendar fired = Calendar.getInstance();
        fired.set(2021, Calendar.JANUARY, 1, 8, 0, 0);
        Employee emp1 = new Employee("John", hired, fired, 100);
        store.add(emp1);
        Report report = new CSVReport(store, new ReportDateTimeParser());
        String expected = "Name,Hired,Fired,Salary" + System.lineSeparator()
                + "John,01:01:2020 08:00,01:01:2021 08:00,100.0" + System.lineSeparator();
        String result = report.generate(employee -> true);
        assertThat(result, is(expected));
    }
}
