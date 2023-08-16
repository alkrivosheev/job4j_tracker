package ru.job4j.queue;

import java.util.Deque;

public class ReconstructPhrase {

    private final Deque<Character> descendingElements;

    private final Deque<Character> evenElements;

    public ReconstructPhrase(Deque<Character> descendingElements, Deque<Character> evenElements) {
        this.descendingElements = descendingElements;
        this.evenElements = evenElements;
    }

    private String getEvenElements() {
        StringBuilder resStr = new StringBuilder();
        int index = 1;
        for (Character ch : evenElements) {
            if (index % 2 != 0) {
                resStr.append(ch);
            }
            index++;
        }
        return resStr.toString();
    }

    private String getDescendingElements() {
        StringBuilder resStr = new StringBuilder();
        int size = descendingElements.size();
          for (int i = 0; i < size; i++) {
                resStr.append(descendingElements.pollLast());
            }
        return resStr.toString();
    }

    public String getReconstructPhrase() {
        return getEvenElements() + getDescendingElements();
    }
}
