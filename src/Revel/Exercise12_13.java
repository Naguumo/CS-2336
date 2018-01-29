package Revel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exercise12_13
{
    public static void main(String[] args) throws FileNotFoundException
    {
        String s = "";
        if(args[0] != null)
            s = args[0];
        File f = new File(s);
        Scanner in2 = new Scanner(f);
        
        int chars = 0;
        int words = 0;
        int lines = 0;
        while(in2.hasNextLine())
        {
            String line = in2.nextLine();
            chars += line.length();
            String[]split = line.split(" ");
            words += split.length;
            lines++;
        }
        
        System.out.println("File " + f.getName() + " has");
        System.out.println(chars + " characters");
        System.out.println(words + " words");
        System.out.println(lines + "lines");
    }
}
