package 基础阶段.排序;

import utils.Verifaction;

import java.lang.reflect.InvocationTargetException;

/**
 * 时间复杂度：极限描述
 */
public class BaseSortMethodes {
    /**
     * 冒泡排序时间复杂度O(n^2),空间复杂度O(1).稳定
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        for (int end = arr.length - 1; end > 0; end--) {
            for (int start = 0; start < end; start++) {
                if (arr[start] > arr[start + 1]) {
                    swap(arr, start, start + 1);
                }
            }
        }
    }

    /**
     * 选择排序时间复杂度O(n^2),空间复杂度O(1)
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }

        int minIndex;
        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * 插入排序时间复杂度O(n^2),空间复杂度O(1)
     *
     * @param arr
     */
    public static void insertionSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    /**
     * 交换arr数组中i和j位置的值
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Verifaction.verify(BaseSortMethodes.class,"selectionSort");
    }
}
