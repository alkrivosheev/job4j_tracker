package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Calendar;
import java.util.List;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class Cinema3DTest {
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(data -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyOnOccupiedSeatThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        cinema.buy(account, 1, 1, date);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, date))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Seat already taken");
    }

    @Test
    public void whenFindSessionByCriteriaThenReturnCorrectSessions() {
        Cinema cinema = new Cinema3D();
        Session session1 = new Session3D();
        Session session2 = new Session3D();
        cinema.add(session1);
        cinema.add(session2);
        List<Session> sessions = cinema.find(data -> true);
        assertThat(sessions).contains(session1, session2);
    }

    @Test
    public void whenBuyWithInvalidDateThenThrowException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar pastDate = Calendar.getInstance();
        pastDate.set(2020, Calendar.JANUARY, 1);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, pastDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid date");
    }
}