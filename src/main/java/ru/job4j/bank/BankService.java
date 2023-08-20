package ru.job4j.bank;

import ru.job4j.bank.Account;
import ru.job4j.bank.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        boolean userExist = false;
        for (User existUser : users.keySet()) {
            if (existUser.equals(user)) {
                userExist = true;
                break;
            }
        }
        if (!userExist) {
            users.put(user, new ArrayList<Account>());
        }
    }

    public boolean deleteUser(String passport) {
        User user = findByPassport(passport);
        if (user != null) {
            users.remove(user);
            return true;
        }
        return false;
    }

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        boolean accountExist = false;
        if (user != null) {
            if (users.get(user) != null) {
                for (Account accounts : users.get(user)) {
                    if (accounts.getRequisite().equals(account.getRequisite())) {
                        accountExist = true;
                        break;
                    }
                }
            }
            if (!accountExist) {
                users.get(user).add(account);
            }
        }
    }

    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return null;
    }

    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            for (Account account : users.get(user)) {
                if (account.getRequisite().equals(requisite)) {
                    return account;
                }
            }
        }
        return null;
    }

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

    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
