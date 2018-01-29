package Revel;

import java.util.Scanner;

public class Exercise04_21
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a SSN(DDD-DD-DDDD):");
        String s = in.next();
        
        if(s.matches("\\d{3}-\\d{2}-\\d{4}"))
            System.out.println(s + " is a valid social security number");
        else
            System.out.println(s + " is not a valid social security number");
    }
}