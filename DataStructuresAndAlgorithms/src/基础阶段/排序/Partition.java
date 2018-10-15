package 基础阶段.排序;

/**
 * create by pinkill on ${date}
 */
public class Partition {
    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        while (L < more) {
            if (arr[L] < pivot) {
                swap(arr, ++less, L++);
            } else if (arr[L] == pivot) {
                L++;
            } else {
                swap(arr, --more, L);
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


}
