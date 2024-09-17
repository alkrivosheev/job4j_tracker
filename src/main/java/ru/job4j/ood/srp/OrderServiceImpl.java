package ru.job4j.ood.srp;

public class OrderServiceImpl implements OrderService {
    @Override
    public void processOrder(int orderId) {
        System.out.println("Заказ обработан: " + orderId);
    }

    @Override
    public void sendConfirmationEmail(String customerEmail) {
        System.out.println("Информация о заказе направлена по адресу: " + customerEmail);
    }
}
