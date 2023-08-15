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
        while (evenElements.iterator().hasNext()) {
            if (index % 2 != 0) {
                resStr.append(evenElements.poll());
            } else {
                evenElements.poll();
            }
            index++;
        }
        return resStr.toString();
    }

    private String getDescendingElements() {
        StringBuilder resStr = new StringBuilder();
        while (descendingElements.iterator().hasNext()) {
                resStr.append(descendingElements.pollLast());
            }
        return resStr.toString();
    }

    public String getReconstructPhrase() {
        return getEvenElements() + getDescendingElements();
    }
}
