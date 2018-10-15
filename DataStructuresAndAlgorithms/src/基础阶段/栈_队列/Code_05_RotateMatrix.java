package 基础阶段.栈_队列;

import java.sql.SQLOutput;
import java.util.Arrays;

/**
 * create by pinkill on ${date}
 */
public class Code_05_RotateMatrix {
    public static void rotateMatrix(int[][] arr){
        int leftRow = 0;
        int leftCol = 0;
        int rightRow = arr.length - 1;
        int rightCol = arr[0].length - 1;
        while (leftRow < rightRow){
            rotateEdge(arr,leftRow++,leftCol++,rightRow--,rightCol--);
        }
    }
    public static void rotateEdge(int[][] matrix, int leftRow, int leftCol, int rightRow, int rightCol){
        int temp = 0;
        for (int i = 0; i < rightCol-leftCol; i++) {
            int tmp = matrix[leftRow][leftCol+i];
            matrix[leftRow][leftCol+i] = matrix[rightRow-i][leftCol];
            matrix[rightRow-i][leftCol] = matrix[rightRow][rightCol-i];
            matrix[rightRow][rightCol-i] = matrix[leftRow+i][rightCol];
            matrix[leftRow+i][rightCol] = tmp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        printMatrix(matrix);
        rotateMatrix(matrix);
        System.out.println("=========");
        printMatrix(matrix);
    }
}
