package ru.job4j.stream;

import java.util.*;
import java.util.stream.Stream;

public class Card {

    private Suit suit;
    private Value value;

    public enum Suit {
        Diamonds, Hearts, Spades, Clubs
    }

    public enum Value {
        V_6, V_7, V_8
    }

    @Override
    public String toString() {
        return "Card{" + "suit=" + suit + ", value=" + value + '}';
    }

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public static void main(String[] args) {
        List<Card> cards = Stream.of(Suit.values())
                .flatMap(suit -> Stream.of(Value.values())
                        .map(v -> new Card(suit, v)))
                .toList();

        System.out.println(cards);
    }
}

