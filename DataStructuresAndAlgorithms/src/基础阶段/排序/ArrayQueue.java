package 基础阶段.排序;

/**
 * create by pinkill on ${date}
 */
public class ArrayQueue {
    private Integer[] arr;
    private Integer size;
    private Integer start;
    private Integer end;

    public ArrayQueue(int initSize) {
        if (initSize < 0) {
            throw new IllegalArgumentException("the initSize is less than zero!");
        }
        arr = new Integer[initSize];
        size = 0;
        start = 0;
        end = 0;
    }

    public Integer peek() {
        if (size == 0) {
            return null;
        }
        return arr[start];
    }

    public void push(Integer num) {
        if (size == arr.length) {
            throw new ArrayIndexOutOfBoundsException("the queue is full!");
        }
        size++;
        arr[end] = num;
        end = end == arr.length - 1 ? 0 : end + 1;
    }

    public Integer poll(){
        if (size==0){
            throw new ArrayIndexOutOfBoundsException("the queue is empty!");
        }
        size--;
        int tmp = start;
        //start = start == arr.length-1?0:start+1;
        start = (start+1) % arr.length;
        return  arr[start];
    }
}
