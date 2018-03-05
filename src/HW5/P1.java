package HW5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class P1
{
    public static void main(String[] args) throws FileNotFoundException
    {
        PStack<Character> stack = new PStack<>();
        
        Scanner in = new Scanner(new File("input.txt"));
        while(in.hasNextLine())
        {
            char[] line = in.nextLine().toCharArray();
            for(int i = 0; i < line.length; i++)
                stack.push(line[i]);
        }
        
        PrintWriter out = new PrintWriter(new File("output.txt"));
        while(stack.getSize() > 0)
            out.write(stack.pop());
        
        in.close();
        out.close();
    }
}
