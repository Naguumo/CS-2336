package Revel;

import java.util.Scanner;

public class Exercise09_13
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter the number of rows and columns in the array: ");
        double[][] arr = new double[in.nextInt()][in.nextInt()];
        System.out.println("Enter the array:");
        for(int i = 0; i < arr.length; i++)
            for(int j = 0; j < arr[0].length; j++)
                arr[i][j] = in.nextDouble();
        
        Location loc = Location.locateLargest(arr);
        System.out.println("The location of the largest element is " + loc.maxValue + " at (" + loc.row + ", " + loc.column + ")");
        
        in.close();
    }
}

class Location
{
    public int row;
    public int column;
    public double maxValue;
    
    public Location(){}
    
    public static Location locateLargest(double[][] arr)
    {
        Location l = new Location();
        for(int i = 0; i < arr.length; i++)
            for(int j = 0; j < arr[0].length; j++)
                if(arr[i][j] > l.maxValue)
                {
                    l.maxValue = arr[i][j];
                    l.row = i;
                    l.column = j;
                }
        return l;
    }
}