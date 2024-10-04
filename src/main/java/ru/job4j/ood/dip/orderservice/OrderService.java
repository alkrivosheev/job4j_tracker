package ru.job4j.ood.dip.orderservice;

public class OrderService {
    private EmailNotificationService emailService;

    public OrderService() {

        emailService = new EmailNotificationService();
    }

    public void processOrder() {

        /* Логика обработки заказа */

        emailService.sendEmail("Order processed");

    }
}
