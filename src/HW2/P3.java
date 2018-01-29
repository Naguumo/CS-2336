package HW2;

import java.util.Scanner;

public class P3
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Let's Multiply Two Matrices!");
        System.out.print("Column Size of Matrix 1: ");
        int cm1 = in.nextInt();
        System.out.print("Row Size of Matrix 1 / Column Size of Matrix 2: ");
        int crx = in.nextInt();
        System.out.print("Row Size of Matrix 2: ");
        int rm2 = in.nextInt();
        
        double[][] m1 = new double[cm1][crx];
        double[][] m2 = new double[crx][rm2];
        
        
        System.out.println("Enter Matrix 1 [" + cm1 + "," + crx + "]:");
        for(int i = 0; i < m1.length; i++)
            for(int j = 0; j < m1[0].length; j++)
                m1[i][j] = in.nextDouble();
        
        in.findInLine("Matrix 2:");
        System.out.println("Enter Matrix 2 [" + crx + "," + rm2 + "]:");
        for(int i = 0; i < m2.length; i++)
            for(int j = 0; j < m2[0].length; j++)
                m2[i][j] = in.nextDouble();
        
        double[][] result = multiplyMatrix(m1,m2);
        System.out.println();
        System.out.println("The Resulting Matrix:");
        for(int i = 0; i < result.length; i++)
        {
            for(int j = 0; j < result[0].length; j++)
                System.out.print(String.format("%.2f", result[i][j]) + " ");
            System.out.println();
        }
        
        in.close();
    }
    
    public static double[][] multiplyMatrix(double[][] m1, double[][] m2)
    {
        double[][] result = new double[m1.length][m2[0].length];
        for(int i = 0; i < result.length; i++)
            for(int j = 0; j < result[0].length; j++)
                for(int k = 0; k < m2.length; k++)
                    result[i][j] += m1[i][k]*m2[k][j];
        return result;
    }
}
