package HW1;

import java.util.Scanner;

public class P1
{
    public static void main(String[] args)
    {
        System.out.println("This will determine whether one rectangle is inside or overlapping another");
        Scanner in = new Scanner(System.in);
        
        //Rectrangle 1
        System.out.println("Please enter the center coordinates(x and y), width, and height of the larger rectangle (with spaces between each number)");
        double x1 = in.nextDouble();
        double y1 = in.nextDouble();
        double w1 = in.nextDouble();
        double h1 = in.nextDouble();
        
        double l1 = x1-w1/2;
        double r1 = x1+w1/2;
        double b1 = y1-h1/2;
        double t1 = y1+h1/2;
        //System.out.println(l1 + " " + r1 + " " + b1 + " " + t1);
        
        //Rectangle 2
        System.out.println("Please enter the center coordinates(x and y), width, and height of the smaller rectangle (with spaces between each number)");
        double x2 = in.nextDouble();
        double y2 = in.nextDouble();
        double w2 = in.nextDouble();
        double h2 = in.nextDouble();
        
        double l2 = x2-w2/2;
        double r2 = x2+w2/2;
        double b2 = y2-h2/2;
        double t2 = y2+h2/2;
        //System.out.println(l2 + " " + r2 + " " + b2 + " " + t2);
        
        if(l1 <= l2 && r1 >= r2 && b1 <= b2 && t1 >= t2)
            System.out.println("The rectangle is inside the other");
        else if((l1 < r2 && t1 > b2 && l1 > l2 && t1 < t2)
                ||(r1 > l2 && t1 > b2 && r1 < r2 && t1 < t2)
                ||(l1 < r2 && b1 < t2 && l1 > l2 && b1 > b2)
                ||(r1 > l2 && b1 < t2 && r1 < r2 && b1 > b2))
            System.out.println("The two rectangles are overlapping");
        else
            System.out.println("The two rectangles neither overlap nor envelop each other");
        
    }
}
