package ru.job4j.lombok;

public class PermissionUsage {
    public static void main(String[] args) {
        var usage = Permission.of()
                .id(1)
                .name("root")
                .accessBy("dir open")
                .accessBy("dir read")
                .accessBy("file write")
                .accessBy("file read")
                .build();
        System.out.println(usage);
    }
}
