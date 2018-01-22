package HW1;

import java.util.Scanner;

public class P2
{
    public static void main(String[] args)
    {
        System.out.println("This will determine the mean and standard deviation of 10 numbers");
        Scanner in = new Scanner(System.in);
        
        System.out.println("Please enter the numbers with spaces between each number");
        double[] nums = new double[10];
        double total = 0;
        for(int i = 0; i < nums.length; i++)
        {
            nums[i] = in.nextDouble();
            total += nums[i];
        }
        in.close();
        
        double mean = (double)total/nums.length;
        double summation = 0;
        for(int i = 0; i < nums.length; i++)
        {
            summation += Math.pow((nums[i]-mean),2);
        }
        double stddev = Math.sqrt(summation/nums.length);
        System.out.println("The mean of those numbers is " + mean);
        System.out.println("The standard deviation of those numbers is " + stddev);
    }
}
