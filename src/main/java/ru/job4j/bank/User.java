package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель пользователя банковского сервиса
 * @author Alexander Krivosheev
 * @version 1.0
 */
public class User {
    /**
     * Поле содержит строку с номером паспорта пользователя
     */
    private String passport;
    /**
     * Поле с именем пользователя
     */
    private String username;

    /**
     * Конструктор класса
     * @param passport Строка с паспортом
     * @param username Строка с именем пользователя
     */
    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    /**
     * Метод получения паспортных данных
     * @return Возвращает строку с номером паспорта
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Метод записывает номер паспорта в обьект User
     * @param passport Принимает строку с номером паспорта
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * Метод возвращает имя пользователя
     * @return Строка с именем пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Метод записывает имя пользователя в обьект User
     * @param username Принимает строку с именем
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Перегрузка метода equals
     * @param o Принимает обьект Account для сравнения с текущим
     * @return Возвращает true, если обьекты равны
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    /**
     * Перегрузка метода hashCode
     * @return Возвращает Хэш поля Реквизиты
     */
    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
