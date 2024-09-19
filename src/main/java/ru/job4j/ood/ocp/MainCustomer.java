package ru.job4j.ood.ocp;

public class MainCustomer {

    public static void main(String[] args) {
        Customer regular = new RegularCustomer();
        Customer premium = new PremiumCustomer();
        DiscountCalculator calculator = new DiscountCalculator();
        System.out.println("Discount for Regular: " + calculator.calculateDiscount(regular));
        System.out.println("Discount for Premium: " + calculator.calculateDiscount(premium));
    }
}
