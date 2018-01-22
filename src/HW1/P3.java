package HW1;

import java.util.Scanner;

public class P3
{
    public static void main(String[] args)
    {
        System.out.println("This will convert a number to hexadecimal, please enter it below");
        Scanner in = new Scanner(System.in);
        hexConvert(in.nextInt());
        in.close();
    }
    
    public static String hexxed = "";
    public static void hexConvert(int num)
    {
        int hex = num % 16;
        switch(hex){
            case 10:
                hexxed = "A" + hexxed;
                break;
            case 11:
                hexxed = "B" + hexxed;
                break;
            case 12:
                hexxed = "C" + hexxed;
                break;
            case 13:
                hexxed = "D" + hexxed;
                break;
            case 14:
                hexxed = "E" + hexxed;
                break;
            case 15:
                hexxed = "F" + hexxed;
                break;
            default:
                hexxed = hex + hexxed;
                break;
        }
        
        if(num/16 != 0)
            hexConvert(num/16);
        else
            System.out.println("That number in hexadecimal is " + hexxed);
    }
}
