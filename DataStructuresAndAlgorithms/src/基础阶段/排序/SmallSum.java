package 基础阶段.排序;

/**
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组
 * 的小和。
 * 例子：
 * [1,3,4,2,5]
 * 1左边比1小的数，没有；
 * 3左边比3小的数，1；
 * 4左边比4小的数，1、3；
 * 2左边比2小的数，1；
 * 5左边比5小的数，1、3、4、2；
 * 所以小和为1+1+3+1+1+3+4+2=16
 *
 * 逆序对问题
 * 在一个数组中，左边的数如果比右边的数大，则折两个数构成一个逆序对，请打印所有逆序对。
 *
 * 将上个问题中从小到大排序变成从大到小排序即可
 */
public class SmallSum {
    public int mergeSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return 0;
        }
        return mergeSortProcess(arr, 0, arr.length - 1);
    }

    private int mergeSortProcess(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return mergeSortProcess(arr, left, mid) + mergeSortProcess(arr, mid + 1, right) + merge(arr, left, mid, right);
    }

    private int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int helpIndex = 0;
        int leftP = left;
        int rightP = mid + 1;
        int result = 0;
        while (leftP <= mid && rightP <= right) {
            result += arr[leftP] < arr[rightP] ? (right - rightP + 1) * arr[leftP] : 0;
            help[helpIndex++] = arr[leftP] < arr[rightP] ? arr[leftP++] : arr[rightP++];
        }
        while (leftP <= mid) {
            help[helpIndex++] = arr[leftP++];
        }
        while (rightP <= mid) {
            help[helpIndex++] = arr[rightP++];
        }
        helpIndex = 0;
        while (helpIndex < help.length) {
            arr[left + helpIndex] = help[helpIndex++];
        }
        return result;
    }
}
