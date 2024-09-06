package ru.job4j;

import java.util.LinkedList;
import java.util.Queue;

public class BattleShip {

    private static final int[][] DIRECTIONS = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public int countAliveShips(int[][] sea) {
        int shipCount = 0;
        int rows = sea.length;
        int cols = sea[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (sea[i][j] == 1) {
                    shipCount++;
                    bfs(sea, i, j, rows, cols);
                }
            }
        }
        return shipCount;
    }

    private void bfs(int[][] sea, int startX, int startY, int rows, int cols) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        sea[startX][startY] = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1];
            for (int[] direction : DIRECTIONS) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if (isInBounds(newX, newY, rows, cols) && sea[newX][newY] == 1) {
                    sea[newX][newY] = 0;
                    queue.add(new int[]{newX, newY});
                }
            }
        }
    }

    private boolean isInBounds(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
