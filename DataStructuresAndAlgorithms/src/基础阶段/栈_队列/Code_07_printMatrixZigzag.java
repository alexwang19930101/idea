package 基础阶段.栈_队列;

/**
 * create by pinkill on ${date}
 */
public class Code_07_printMatrixZigzag {
    public static void printMatrixZigzag(int[][] arr) {
        int upRow = 0;
        int upCol = 0;
        int downRow = 0;
        int downCol = 0;
        boolean fromUp = false;
        while (upRow < arr.length) {
            printLine(arr, fromUp, upRow, upCol, downRow, downCol);
            upRow = upCol != arr[0].length - 1 ? upRow : upRow + 1;
            upCol = upCol != arr[0].length - 1 ? upCol + 1 : upCol;
            downCol = downRow != arr.length - 1 ? downCol : downCol + 1;
            downRow = downRow != arr.length - 1 ? downRow + 1 : downRow;
            fromUp = !fromUp;
        }
    }

    private static void printLine(int[][] arr, boolean fromUp, int upRow, int upCol, int downRow, int downCol) {
        if (fromUp) {
            while (upRow <= downRow) {
                System.out.print(arr[upRow++][upCol--] + " ");
            }
        } else {
            while (downCol <= upCol) {
                System.out.print(arr[downRow--][downCol++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4},
                            {5, 6, 7, 8},
                            {9, 10, 11, 12}};
        printMatrixZigzag(matrix);
    }
}
