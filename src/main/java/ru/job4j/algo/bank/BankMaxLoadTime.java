package ru.job4j.algo.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankMaxLoadTime {

    public static int[] findMaxLoadTime(List<int[]> visitTimes) {
        List<Event> events = new ArrayList<>();
        for (int[] visitTime : visitTimes) {
            int arrivalTime = visitTime[0];
            int departureTime = visitTime[1];
            events.add(new Event(arrivalTime, EventType.ARRIVAL));
            events.add(new Event(departureTime, EventType.DEPARTURE));
        }
        Collections.sort(events);
        int currentLoad = 0;
        int maxLoad = 0;
        int maxLoadStartTime = 0;
        int maxLoadEndTime = 0;

        for (Event event : events) {
            if (event.type == EventType.ARRIVAL) {
                currentLoad++;
                if (currentLoad > maxLoad) {
                    maxLoad = currentLoad;
                    maxLoadStartTime = event.time;
                    maxLoadEndTime = 0;
                }
            } else {
                if (currentLoad == maxLoad && maxLoadEndTime == 0) {
                    maxLoadEndTime = event.time;
                }
                currentLoad--;
            }
        }
        return new int[]{maxLoadStartTime, maxLoadEndTime};
    }

    static class Event implements Comparable<Event> {
        int time;
        EventType type;

        Event(int time, EventType type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Event other) {
            if (this.time == other.time) {
                return this.type == EventType.ARRIVAL ? -1 : 1;
            }
            return Integer.compare(this.time, other.time);
        }
    }

    enum EventType {
        ARRIVAL, DEPARTURE
    }
}