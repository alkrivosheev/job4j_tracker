package ru.job4j.ood.srp;

public interface UserService {
    boolean validateUser(String email);

    void saveUser(String name, String email);
}
