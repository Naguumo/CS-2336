package HW5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class P2
{
    public static void main(String[] args) throws FileNotFoundException
    {
        PQueue<Character> queue = new PQueue<>();
        
        Scanner in = new Scanner(new File("input.txt"));
        while(in.hasNextLine())
        {
            char[] line = in.nextLine().toCharArray();
            for(int i = 0; i < line.length; i++)
                queue.offer(line[i]);
        }
        
        PrintWriter out = new PrintWriter(new File("output.txt"));
        while(queue.getSize() > 0)
            out.write((queue.remove() + "").toUpperCase());
        
        in.close();
        out.close();
    }
}
