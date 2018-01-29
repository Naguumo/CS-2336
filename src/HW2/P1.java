package HW2;

import java.util.Scanner;

public class P1
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Amount to Return: ");
        String s = in.next();
        
        int dollar = Integer.parseInt(s.substring(0, s.indexOf('.')));
        int cent = Integer.parseInt(s.substring(s.indexOf('.')+1));
        
        int[][] dChange = {{0,100},
                            {0,50},
                            {0,20},
                            {0,10},
                            {0,5},
                            {0,1}};
        int[][] cChange = {{0,50},
                            {0,25},
                            {0,10},
                            {0,5},
                            {0,1}};
        
        System.out.println("Your Change is:");
        for(int i = 0; i < dChange.length; i++)
        {
            dChange[i][0] = dollar/dChange[i][1];
            dollar %= dChange[i][1];
            System.out.println("$" + dChange[i][1] + "\t x" + dChange[i][0]);
        }
        
        for(int i = 0; i < cChange.length; i++)
        {
            cChange[i][0] = cent/cChange[i][1];
            cent %= cChange[i][1];
            System.out.println("$" + cChange[i][1] + "\t x" + cChange[i][0]);
        }
        
        in.close();
    }
}
