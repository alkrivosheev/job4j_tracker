package ru.job4j.ood.srp;

public interface OrderService {
    void processOrder(int orderId);

    void sendConfirmationEmail(String customerEmail);
}
