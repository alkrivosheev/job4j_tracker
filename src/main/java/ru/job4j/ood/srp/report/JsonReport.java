package ru.job4j.ood.srp.report;

import com.google.gson.*;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JsonReport implements Report {

    private final Store store;

    public JsonReport(Store store) {
        this.store = store;
    }

    static class EmployeeSerializer implements JsonSerializer<Employee> {

        @Override
        public JsonElement serialize(Employee employee, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm");
            jsonObject.addProperty("name", employee.getName());
            jsonObject.addProperty("hired", dateFormat.format(employee.getHired().getTime()));
            jsonObject.addProperty("fired", dateFormat.format(employee.getFired().getTime()));
            jsonObject.addProperty("salary", employee.getSalary());
            return jsonObject;
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Employee.class, new EmployeeSerializer())
                .create();
        List<Employee> filteredEmployees = store.findBy(filter).stream()
                .filter(filter)
                .collect(Collectors.toList());
        return gson.toJson(filteredEmployees);
    }
}
