package sparse;

import java.util.ArrayList;
import java.util.function.IntConsumer;

/**
 * @auther 郭永钢
 * @data 2020/9/9 8:36
 * @desc: 稀疏矩阵的数据结构，可以用来压缩
 */

public class SparseMatrix {
    public static void main(String[] args) {
        int[][] ints = create();
        printMatrix(ints);
        int[][] storage = storage(ints);
        printMatrix(storage);
        int[][] restore = restore(storage);
        printMatrix(restore);
    }

    /*生成棋盘*/
    public static int[][] create() {
        int[][] a = new int[10][10];
        a[4][4] = 1;
        a[2][1] = 1;
        a[3][1] = 2;
        a[6][4] = 2;
        return a;
    }

    /*稀疏矩阵进行存储*/
    public static int[][] storage(int[][] a) {

        int count = 0;
        for (int aa[] : a
        ) {
            for (int aaa : aa
            ) {
                if (aaa != 0) {
                    count++;
                }
            }
        }

        int[][] sparse = new int[count + 1][3];
        int row = 0, clu = 0;
        sparse[row][0] = a.length;
        sparse[row][1] = a[0].length;
        sparse[row++][2] = count;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] != 0) {
                    sparse[row][0] = i;
                    sparse[row][1] = j;
                    sparse[row++][2] = a[i][j];
                }
            }
        }

        return sparse;
    }

    /*打印稀疏矩阵*/
    public static void printMatrix(int[][] a) {
        for (int aa[] : a
        ) {
            for (int aaa : aa
            ) {
                System.out.print(aaa + "\t");
            }
            System.out.println();
        }
    }

    /*矩阵恢复*/
    public static int[][] restore(int[][] sparse){
        int[][] a = new int[sparse[0][0]][sparse[0][1]];
        for (int i = 1; i < sparse.length; i++) {
            a[sparse[i][0]][sparse[i][1]]=sparse[i][2];
        }
        return a;
    }
}
