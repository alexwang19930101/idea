package 基础阶段.排序;


public class  BucketSort{
    public static void bucketSort(int[] arr){
        if(null == arr || arr.length < 2){
            return;
        }
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max,arr[i]);
            min = Math.min(min,arr[i]);
        }
        int[] bucket = new int[max-min+1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]-min]++;
        }
        int arrIndex = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0){
                arr[arrIndex++] = i+min;
            }
        }
    }
}