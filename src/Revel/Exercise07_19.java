package Revel;

import java.util.Scanner;

public class Exercise07_19
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter List:");
        int[] list = new int[in.nextInt()];
        for(int i = 0; i < list.length; i++)
            list[i] = in.nextInt();
        
        System.out.println((isSorted(list))? "The list is already sorted" : "The list is not sorted");
    }
    
    public static boolean isSorted(int[] list)
    {
        for(int i = 0; i < list.length-1; i++)
            if(list[i] > list[i+1])
                return false;
        return true;
    }
}
