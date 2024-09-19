package ru.job4j.ood.ocp;

public class Customer {

    public String type;
}

class RegularCustomer extends Customer {

    public RegularCustomer() {
        this.type = "Regular";
    }
}

class PremiumCustomer extends Customer {

    public PremiumCustomer() {
        this.type = "Premium";
    }
}

class DiscountCalculator {

    public double calculateDiscount(Customer customer) {
        if (customer.type.equals("Regular")) {
            return 0.1;
        } else if (customer.type.equals("Premium")) {
            return 0.2;
        }
        return 0;
    }
}
