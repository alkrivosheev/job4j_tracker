package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель банковского счета
 * @author Alexander Krivosheev
 * @version 1.0
 */
public class Account {
    /**
     * Поле реквизитов счета
     */
    private String requisite;
    /**
     * Поле баланса на счете
     */
    private double balance;

    /**
     * Конструктор класса
     * @param requisite параметр реквизитов счета
     * @param balance параметр баланса счета
     */
    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    /**
     * Метод позволяет получить реквизит счета
     * @return возвращает строку с реквизитом счета
     */
    public String getRequisite() {
        return requisite;
    }

    /**
     * Метод добавления реквизита счета
     * @param requisite Принимает строку с реквизитом счета
     */
    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    /**
     * Метод позволяет получить баланс счета
     * @return возвращает баланс счета (double)
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Метод установки баланса текущего счета
     * @param balance Принимает баланс счета (double)
     */
    public void setBalance(double balance) {
        this.balance = balance;
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
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    /**
     * Перегрузка метода hashCode
     * @return Возвращает Хэш поля Реквизиты
     */
    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}
