package ru.job4j.ood.srp;

public class UserServiceImpl implements UserService {
    @Override
    public boolean validateUser(String email) {
        return email.contains("@");
    }

    @Override
    public void saveUser(String name, String email) {
        System.out.println("Пользователь сохранен в базу данных: " + name);
    }
}