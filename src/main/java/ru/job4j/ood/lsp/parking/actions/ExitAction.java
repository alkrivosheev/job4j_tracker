package ru.job4j.ood.lsp.parking.actions;

public class ExitAction implements UserAction {

    @Override
    public void execute() {
        System.out.println("Завершение работы программы...");
        System.exit(0);
    }
}
