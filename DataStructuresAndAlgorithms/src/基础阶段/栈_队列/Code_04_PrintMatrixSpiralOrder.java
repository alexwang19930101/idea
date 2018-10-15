package 基础阶段.栈_队列;

import 基础阶段.排序.MaxGap;

import javax.swing.plaf.synth.SynthSpinnerUI;

/**
 * create by pinkill on ${date}
 */
public class Code_04_PrintMatrixSpiralOrder {
    public static void PrintMatrixSpiralOrder(int[][] arr) {
        int leftRow = 0;
        int leftCol = 0;
        int rightRow = arr.length - 1;
        int rightCol = arr[0].length - 1;
        while (leftRow <= rightRow && leftCol <= rightCol){
            PrintEdge(arr,leftRow++,leftCol++,rightRow--,rightCol--);
        }
    }

    public static void PrintEdge(int[][] matrix, int leftRow, int leftCol, int rightRow, int rightCol) {
        if (leftRow == rightRow) {
            for (int i = leftCol; i <= rightCol; i++) {
                System.out.print(matrix[leftRow][i]);
            }
        } else if (leftCol == rightCol) {
            for (int i = leftRow; i < rightRow; i++) {
                System.out.print(matrix[i][leftCol]);
            }
        } else {
            int curRow = leftRow;
            int curCol = leftCol;
            while (curCol != rightCol) {
                System.out.print(matrix[curRow][curCol++] + " ");
            }
            while (curRow != rightRow) {
                System.out.print(matrix[curRow++][curCol] + " ");
            }
            while (curCol != leftCol) {
                System.out.print(matrix[curRow][curCol--] + " ");
            }
            while (curRow != leftRow) {
                System.out.print(matrix[curRow--][curCol] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 },
                            { 5, 6, 7, 8 },
                            { 9, 10, 11, 12 },
                            { 13, 14, 15, 16 } };
        PrintMatrixSpiralOrder(matrix);
    }
}
