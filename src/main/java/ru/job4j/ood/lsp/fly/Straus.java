package ru.job4j.ood.lsp.fly;

public class Straus extends Bird {
    @Override
    public void fly() {
          throw new UnsupportedOperationException("Strauss can't fly.");
    }
}
