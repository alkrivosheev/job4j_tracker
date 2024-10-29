package ru.job4j.algo.sliding.window;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        List<Event> events = new ArrayList<>();

        for (Interval interval : intervals) {
            events.add(new Event(interval.start, true));
            events.add(new Event(interval.end, false));
        }

        Collections.sort(events);

        int maxOverlaps = 0;
        int currentOverlaps = 0;
        int maxStart = -1;
        int maxEnd = -1;
        int currentStart = -1;

        for (Event event : events) {
            if (event.isStart) {
                currentOverlaps++;
                if (currentOverlaps > maxOverlaps) {
                    maxOverlaps = currentOverlaps;
                    maxStart = event.time;
                    currentStart = event.time;
                }
            } else {
                if (currentOverlaps == maxOverlaps) {
                    maxEnd = event.time;
                }
                currentOverlaps--;
            }
        }

        return new int[]{maxStart, maxEnd};
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(7, 8));

        int[] result = findMaxOverlapInterval(intervals);

        System.out.println("Interval that overlaps the maximum number of intervals: [" + result[0] + ", " + result[1] + "]");
    }
}