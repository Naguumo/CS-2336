package Revel;

import java.util.Scanner;

public class Exercise19_05
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter 10 integers: ");
        Integer[] nums = new Integer[10];
        for(int i = 0; i < nums.length; i++)
        {
            nums[i] = in.nextInt();
        }
        in.close();
        System.out.println("The max number is " + max(nums));
    }
    
    public static <E extends Comparable<E>> E max(E[] list)
    {
        E num = list[0];
        for(int i = 0; i < list.length; i++)
        {
            if(list[i].compareTo(num) > 0)
                num = list[i];
        }
        return num;
    }
}
