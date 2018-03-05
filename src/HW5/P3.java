package HW5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class P3
{
    public static void main(String[] args) throws FileNotFoundException
    {
        PQueue<Character> queue1 = new PQueue<>();
        Scanner in1 = new Scanner(new File("input.txt"));
        while(in1.hasNextLine())
        {
            char[] line = in1.nextLine().toCharArray();
            for(int i = 0; i < line.length; i++)
                queue1.offer(line[i]);
        }
        in1.close();
        
        PQueue<Character> queue2 = new PQueue<>();
        Scanner in2 = new Scanner(new File("input2.txt"));
        while(in2.hasNextLine())
        {
            char[] line = in2.nextLine().toCharArray();
            for(int i = 0; i < line.length; i++)
                queue2.offer(line[i]);
        }
        in2.close();
        
        if(queue1.getSize() != queue2.getSize())
        {
            System.out.println("Files are not the same");
            return;
        }
        
        while(queue1.getSize() > 0)
        {
            if(!queue1.remove().equals(queue2.remove()))
            {
                System.out.println("Files are not the same");
                return;
            }
        }
        
        System.out.println("Files are Identical");
    }
}
