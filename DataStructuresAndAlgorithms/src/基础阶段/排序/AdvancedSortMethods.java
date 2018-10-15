package 基础阶段.排序;

import utils.Verifaction;

import java.lang.reflect.InvocationTargetException;
import java.text.BreakIterator;

/**
 * create by pinkill on ${date}
 */
public class AdvancedSortMethods {

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 归并排序
     *
     * @param arr
     */
    public void mergeSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        mergeSortProcess(arr, 0, arr.length - 1);
    }

    private void mergeSortProcess(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        mergeSortProcess(arr, left, mid);
        mergeSortProcess(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int helpIndex = 0;
        int leftP = left;
        int rightP = mid + 1;

        while (leftP <= mid && rightP <= right) {
            help[helpIndex++] = arr[leftP] < arr[rightP] ? arr[leftP++] : arr[rightP++];
        }
        while (leftP <= mid) {
            help[helpIndex++] = arr[leftP++];
        }
        while (rightP <= right) {
            help[helpIndex++] = arr[rightP++];
        }

        for (helpIndex = 0; helpIndex < help.length; helpIndex++) {
            arr[left + helpIndex] = help[helpIndex];
        }
    }



    /**
     * 快速排序
     * 时间复杂度为O(nlogn),空间复杂度O（logn）
     *
     * @param arr
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        //优化枢轴
        swap(arr, getPivot(arr,L,R), R);
        int[] p = partition(arr, L, R);
        quickSort(arr, L, p[0] - 1);
        quickSort(arr, p[1] + 1, R);
    }

    private static  int getPivot(int[] arr,int left,int right){
        int mid = left + ((right-left)>>1);
        if (arr[left]<arr[right]){
            return  arr[left]>arr[mid]?left:arr[mid]<arr[right]?mid:right;
        }else {
            return  arr[right]>arr[mid]?right:arr[mid]<arr[left]?mid:left;
        }
    }
    private static int[] partition(int[] arr, int left, int right) {
        int lessPoint = left - 1;
        int morePoint = right+1;
        int pivot = arr[right];
        int cur = left;

        //System.out.println(arr[right]);
        while (cur < morePoint) {
            if (arr[cur] < pivot) {
                swap(arr, ++lessPoint, cur++);
            } else if (arr[cur] == pivot) {
                cur++;
            } else {
                swap(arr, cur, --morePoint);
            }
        }
        return new int[]{lessPoint + 1, morePoint-1};
    }




    /**
     * 堆排序算法
     * @param arr
     */
    public static void heapSort(int[] arr){
        if(null == arr|| arr.length < 2){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr,i);
        }
        int heapSize = arr.length;
        while (heapSize>0){
            swap(arr,0,--heapSize);
            heapify(arr,0,heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;
        while (leftChildIndex < heapSize) {
            int maxChildIndex = rightChildIndex < heapSize && arr[leftChildIndex] < arr[rightChildIndex] ? rightChildIndex : leftChildIndex;
            if (arr[maxChildIndex] <= arr[index]) {
                break;
            }
            swap(arr,maxChildIndex,index);
            index = maxChildIndex;
            leftChildIndex = index*2+1;
            rightChildIndex = leftChildIndex+1;
        }
    }

    private static void heapInsert(int[] arr, int index) {
        while (arr[(index-1)/2]<arr[index]){
            swap(arr,(index-1)/2,index);
            index = (index-1)/2;
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Verifaction.verify(AdvancedSortMethods.class, "heapSort");
        /*int[] arr = {68, 69, 52, 49, 3, -61, -61, -64, -50, -9, -79, -39, -35, -34, -67, 29, -19};
        System.out.println(Arrays.toString(partition(arr,0,arr.length-1)));
        System.out.println(Arrays.toString(arr));*/
    }
}
