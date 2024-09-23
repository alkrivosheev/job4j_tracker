package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.function.Predicate;
import java.util.List;

public class XmlReport implements Report {
    private final Store store;

    public XmlReport(Store store) {
        this.store = store;
    }

    static class EmployeeSerializer {
        public String xmlSerialize(Employees employees) throws JAXBException {
            String res = "";
            JAXBContext context = JAXBContext.newInstance(Employees.class); // Изменили контекст для работы с Employees
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(employees, writer);
                res = writer.getBuffer().toString();
            } catch (JAXBException | IOException e) {
                e.printStackTrace();
            }
            return res;
        }
    }

    public static class DateAdapter extends XmlAdapter<String, Calendar> {
        private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

        @Override
        public Calendar unmarshal(String v) throws ParseException {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DATE_FORMAT.parse(v));
            return calendar;
        }

        @Override
        public String marshal(Calendar v) {
            return DATE_FORMAT.format(v.getTime());
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder res = new StringBuilder();
        EmployeeSerializer xmlSer = new EmployeeSerializer();

        List<Employee> employees = store.findBy(filter);
        Employees employeesContainer = new Employees(employees); // Создаем контейнер Employees

        try {
            res.append(xmlSer.xmlSerialize(employeesContainer)); // Сериализуем контейнер
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        return res.toString();
    }
}
