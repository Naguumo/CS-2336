package HW2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class P2
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner in = new Scanner(new File("Salary.txt"));
        double tAssist = 0, tAssoc = 0, tFull = 0;
        double aAssist = 0, aAssoc = 0, aFull = 0;
        
        while(in.hasNextLine())
        {
            String line = in.nextLine();
            String[] parts = line.split(" ");
            switch(parts[2])
            {
                case "assistant":
                    aAssist++;
                    tAssist += Double.parseDouble(parts[3]);
                    break;
                case "associate":
                    aAssoc++;
                    tAssoc += Double.parseDouble(parts[3]);
                    break;
                case "full":
                    aFull++;
                    tFull += Double.parseDouble(parts[3]);
                    break;
            }
        }
        
        System.out.println("Total Income:");
        System.out.println("Assistant Professors - " + String.format("$%.2f", tAssist));
        System.out.println("Associate Professors - " + String.format("$%.2f", tAssoc));
        System.out.println("Full Professors - " + String.format("$%.2f", tFull));
        System.out.println("Total All Faculty - " + String.format("$%.2f", tAssist + tAssoc + tFull));
        System.out.println();
        System.out.println("Average Income:");
        System.out.println("Assistant Professors - " + String.format("$%.2f", tAssist/aAssist));
        System.out.println("Associate Professors - " + String.format("$%.2f", tAssoc/aAssoc));
        System.out.println("Full Professors - " + String.format("$%.2f", tFull/aFull));
        System.out.println("Total All Faculty - " + String.format("$%.2f", (tAssist + tAssoc + tFull)/(aAssist + aAssoc + aFull)));
        
        in.close();
    }
}
