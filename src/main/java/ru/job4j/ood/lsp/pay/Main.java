package ru.job4j.ood.lsp.pay;

public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new RestrictedPaymentProcessor();
        processor.processPayment(1500);
    }
}
