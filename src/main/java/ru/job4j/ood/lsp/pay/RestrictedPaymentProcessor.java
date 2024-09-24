package ru.job4j.ood.lsp.pay;

public class RestrictedPaymentProcessor extends PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        if (amount > 1000) {
            throw new IllegalArgumentException("Amount exceeds limit.");
        }
        super.processPayment(amount);
    }
}
