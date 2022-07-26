package ru.job4j.collection.map.task;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double score = 0;
        int count = 0;
        for (Label label : averageScoreByPupil(pupils)) {
            score += label.score();
            count++;
        }
        return score / count;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double score = 0;
            int count = 0;
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
                count++;
            }
            list.add(new Label(pupil.name(), score / count));
        }
        return list;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        for (Pupil pupil : pupils) {
            putMap(map, pupil);
            count++;
        }
        for (String name : map.keySet()) {
            list.add(new Label(name, (double) map.get(name) / count));
        }
        return list;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double score = 0;
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
            }
            list.add(new Label(pupil.name(), score));
        }
        list.sort(Comparator.naturalOrder());
        return list.get(list.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (Pupil pupil : pupils) {
            putMap(map, pupil);
        }
        for (String name : map.keySet()) {
            list.add(new Label(name, (double) map.get(name)));
        }
        list.sort(Comparator.naturalOrder());
        return list.get(list.size() - 1);
    }

    private static void putMap(Map<String, Integer> map, Pupil pupil) {
        for (Subject subject : pupil.subjects()) {
            map.putIfAbsent(subject.name(), 0);
            map.computeIfPresent(subject.name(), (key,value) -> value + subject.score());
        }
    }
}