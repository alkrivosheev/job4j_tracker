package ru.job4j.algo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SmallestRangeFinder {
    /** Добавьте поля класса здесь, если это необходимо */

    public static int[] findSmallestRange(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) {
            return null;
        }
        Map<Integer, Integer> freqMap = new HashMap<>();
        int start = 0;
        int distinctCount = 0;
        int left = 0;
        int right = nums.length - 1;
        boolean found = false;
        for (int end = 0; end < nums.length; end++) {
            freqMap.put(nums[end], freqMap.getOrDefault(nums[end], 0) + 1);
            if (freqMap.get(nums[end]) == 1) {
                distinctCount++;
            }
            while (distinctCount == k) {
                found = true;
                if ((end - start) < (right - left)) {
                    left = start;
                    right = end;
                }
                freqMap.put(nums[start], freqMap.get(nums[start]) - 1);
                if (freqMap.get(nums[start]) == 0) {
                    distinctCount--;
                }
                start++;
            }
        }

        return found ? new int[]{left, right} : null;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}
