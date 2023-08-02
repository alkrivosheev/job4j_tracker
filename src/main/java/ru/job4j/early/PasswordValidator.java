package ru.job4j.early;

public class PasswordValidator {
    private static final String[] FORBIDDEN = {"qwerty", "12345", "password", "admin", "user"};

    /**
     *  7. Пароль не содержит подстрок без учета регистра: qwerty, 12345, password, admin, user.
     *     Без учета регистра, значит что, например, ни qwerty ни QWERTY ни qwErty и т.п.
     *     если да, то "Password shouldn't contain substrings: qwerty, 12345, password, admin, user".
     * @param password Пароль
     * @return Вернет пароль или выбросит исключение.
     */
    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }
        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }

        boolean hasUpCase = false;
        boolean hasLowCase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        for (char symbol : password.toCharArray()) {
            if (Character.isUpperCase(symbol)) {
                hasUpCase = true;
            }
            if (Character.isLowerCase(symbol)) {
                hasLowCase = true;
            }
            if (Character.isDigit(symbol)) {
                hasDigit = true;
            }
            if (!Character.isLetterOrDigit(symbol)) {
                hasSpecial = true;
            }
            if (hasUpCase && hasLowCase && hasDigit && hasSpecial) {
                break;
            }
        }
        if (!hasUpCase) {
            throw new IllegalArgumentException(
                    "Password should contain at least one uppercase letter"
            );
        }
        if (!hasLowCase) {
            throw new IllegalArgumentException(
                    "Password should contain at least one lowercase letter"
            );
        }
        if (!hasDigit) {
            throw new IllegalArgumentException(
                    "Password should contain at least one figure"
            );
        }
        if (!hasSpecial) {
            throw new IllegalArgumentException(
                    "Password should contain at least one special symbol"
            );
        }
        for (String word:FORBIDDEN) {
            if (password.toLowerCase().contains(word)) {
                throw new IllegalArgumentException(
                        "Password shouldn't contain substrings: qwerty, 12345, password, admin, user"
                );
            }
        }
        return password;
    }
}