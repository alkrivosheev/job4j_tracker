package ru.job4j.hmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        int sum = 0;
        int count = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                sum += subject.score();
                count++;
            }
        }
        return (double) sum / count;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        double average = 0;
        List<Label> rsl = new ArrayList<>();
        for (Pupil pupil : pupils) {
            average = averageScore(List.of(new Pupil(pupil.name(), pupil.subjects())));
            rsl.add(new Label(pupil.name(), average));
        }
        return rsl;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        double average = 0;
        List<Label> rsl = new ArrayList<>();
        LinkedHashMap<String, Integer> subj = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                if (subj.containsKey(subject.name())) {
                    for (Map.Entry entry : subj.entrySet()) {
                        if (entry.getKey() == subject.name()) {
                            entry.setValue(subject.score() + (int) entry.getValue());
                        }
                    }
                } else {
                    subj.put(subject.name(),  (int) subject.score());
                }
            }
        }
        for (Map.Entry entry : subj.entrySet()) {
            average = (int) entry.getValue() / pupils.size();
            rsl.add(new Label(entry.getKey().toString(), average));
        }
        return rsl;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        int sum;
        List<Label> rsl = new ArrayList<>();
        for (Pupil pupil : pupils) {
            sum = 0;
            for (Subject subject : pupil.subjects()) {
                sum += subject.score();
            }
            rsl.add(new Label(pupil.name(), sum));
        }
        rsl.sort(Comparator.naturalOrder());
        return rsl.get(rsl.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        LinkedHashMap<String, Integer> subj = new LinkedHashMap<>();
        List<Label> rsl = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                if (subj.containsKey(subject.name())) {
                    for (Map.Entry entry : subj.entrySet()) {
                        if (entry.getKey() == subject.name()) {
                            entry.setValue(subject.score() + (int) entry.getValue());
                        }
                    }
                } else {
                    subj.put(subject.name(),  (int) subject.score());
                }
            }
        }
        for (Map.Entry entry : subj.entrySet()) {
            rsl.add(new Label(entry.getKey().toString(), (int) entry.getValue()));
        }
        rsl.sort(Comparator.naturalOrder());

        return rsl.get(rsl.size() - 1);
    }
}
