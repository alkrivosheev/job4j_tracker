package ru.job4j.lombok;

import java.util.List;
import lombok.*;

@Builder(builderMethodName = "of")
@ToString
@Getter
public class Permission {
    private int id;
    private String name;

    @Singular("accessBy")
    private List<String> rules;
}