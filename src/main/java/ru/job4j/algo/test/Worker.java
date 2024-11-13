package ru.job4j.algo.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;

public class Worker {

    private static Map<Integer, Worker> workerMap;
    private static Map<Integer, List<Integer>> hierarchyMap;
    private int id;
    private String name;
    private int managerId;

    public Worker(int id, String name, int managerId) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
    }

    protected int getId() {
        return this.id;
    }

    protected int getManagerId() {
        return this.managerId;
    }

    public String getName() {
        return name;
    }

    public static void workerHierarchy(List<Worker> workers) {
        workerMap = new HashMap<>();
        hierarchyMap = new HashMap<>();
        for (Worker worker : workers) {
            workerMap.put(worker.getId(), worker);
            if (worker.getManagerId() != 0) {
                hierarchyMap
                        .computeIfAbsent(worker.getManagerId(), k -> new ArrayList<>())
                        .add(worker.getId());
            }
        }
    }

    public static List<Worker> findManagers(int workerId) {
        List<Worker> managers = new ArrayList<>();
        Worker currentWorker = workerMap.get(workerId);
        while (currentWorker != null && currentWorker.getManagerId() != 0) {
            Worker manager = workerMap.get(currentWorker.getManagerId());
            if (manager != null) {
                managers.add(manager);
                currentWorker = manager;
            } else {
                break;
            }
        }
        return managers;
    }

    public static List<Worker> countSubworkers(int workerId) {
        List<Worker> subworkers = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(workerId);
        while (!queue.isEmpty()) {
            int currentManagerId = queue.poll();
            List<Integer> subworkerIds = hierarchyMap.get(currentManagerId);
            if (subworkerIds != null) {
                for (int subworkerId : subworkerIds) {
                    subworkers.add(workerMap.get(subworkerId));
                    queue.add(subworkerId);
                }
            }
        }
        return subworkers;
    }

    public static void main(String[] args) {
        List<Worker> workers = List.of(
                new Worker(1, "Директор Аркадий", -1),
                new Worker(2, "Техдир Сергей", 1),
                new Worker(3, "Инженер1 Василий", 2),
                new Worker(4, "Инженер2 Екатерина", 2)
        );
        Worker.workerHierarchy(workers);
        System.out.println("------------Руководители Екатерины---------------");
        List<Worker> managers = Worker.findManagers(4);
        managers.forEach(manager -> System.out.println(manager.name));
        System.out.println("---------------Подчиненные Аркадия--------------");
        List<Worker> subworkers = Worker.countSubworkers(1);
        subworkers.forEach(subworker -> System.out.println(subworker.name));
    }
}
