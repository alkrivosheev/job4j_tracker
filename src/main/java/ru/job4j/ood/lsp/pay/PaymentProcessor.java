package ru.job4j.ood.lsp.pay;

public class PaymentProcessor {
    public void processPayment(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
        System.out.println("Processing payment: " + amount);
    }
}
