package ru.job4j.bank;

import ru.job4j.bank.Account;
import ru.job4j.bank.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс основного функционала банковского сервиса
 * @author Alexander Krivosheev
 * @version 1.0
 */
public class BankService {
    /**
     * Поле хранит список пользователей
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавления пользователя
     * @param user Принимает обьект типа User
     */
    public void addUser(User user) {
            users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод удаляет пользователя из системы по паспорту
     * @param passport Стока с номером паспорта
     * @return Восвращает true, если удаление успешно
     */
    public boolean deleteUser(String passport) {
        return users.remove(new User(passport, "")) != null;
    }

    /**
     * Метод добавления счета пользователю
     * @param passport Принимает строку с номером паспорта
     * @param account Принимает обьект типа Account
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод поиска пользователя по паспорту
     * @param passport Принимает строку с номером паспорта
     * @return Возвращает обьект типа User
     */
    public User findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод поиска счета по паспорту и реквизитам
     * @param passport Принимает строку с номером паспорта
     * @param requisite Принимает строку в реквизитами счета
     * @return Возвращает обьект типа Account
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            return users.get(user).stream()
                    .filter(r -> r.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод перевода денежной суммы со счета на счет
     * @param srcPassport  Номеро паспора отправителя
     * @param srcRequisite Реквизиты счета отправителя
     * @param destPassport Номер паспора получателя
     * @param destRequisite Реквизиты счета получателя
     * @param amount Сумма перевода
     * @return Возвращает true в случае успеха операции
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && destAccount != null && srcAccount.getBalance() >= amount) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод возвращает перечень счетов пользователя
     * @param user Принимает обьект типа User
     * @return Возвращает список типа List со счетами пользователя
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
