package 基础阶段.排序;

/**
 * create by pinkill on ${date}
 */
public class MaxGap {
    public static int maxGap(int[] arr) {
        if (null == arr || arr.length < 2) {
            return 0;
        }
        int len = arr.length;
        int max = arr[0];
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        if (max == min) {
            return 0;
        }
        boolean[] bucketHasNum = new boolean[len];
        int[] bucketMaxs = new int[len];
        int[] bucketMins = new int[len];

        int bucketNum;
        for (int i = 0; i < len; i++) {
            bucketNum = (arr[i] - min) * len / (max - min);
            bucketMaxs[bucketNum] = bucketHasNum[bucketNum] ? Math.max(arr[i], bucketMaxs[bucketNum]) : arr[i];
            bucketMins[bucketNum] = bucketHasNum[bucketNum] ? Math.min(arr[i], bucketMins[bucketNum]) : arr[i];
            bucketHasNum[bucketNum] = true;
        }
        int maxGapResult = 0;
        int lastMax = bucketMaxs[0];
        for (int i = 1; i < len; i++) {
            if(bucketHasNum[i]){
                maxGapResult = Math.max(maxGapResult,bucketMins[i]-lastMax);
                lastMax = bucketMaxs[i];
            }
        }
        return maxGapResult;
    }
}
