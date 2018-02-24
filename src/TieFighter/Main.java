//Ishaan Bharal (ixb170930)

package TieFighter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        //Create Reader
        Scanner in = new Scanner(new File("pilot_routes.txt"));
        
        //Declare List
        Linker<Payload> list = new Linker<>();
        
        //Iterate through Lines
        while(in.hasNextLine()) //Exit if theres no more text
        {
            //Take in Line
            String txt = in.nextLine();
            
            //Makes sure input is valid
            if(!txt.matches("\\w+\\s?\\w*(\\s-?\\d*\\.?\\d*,-?\\d*\\.?\\d*)+"))
                continue;
            
            //Add Object to List with Name
            list.addLast(new Payload(txt.split("(\\s-?\\d*\\.?\\d*,-?\\d*\\.?\\d*)+")[0]));
            
            //Split Line of text by space( )
            String[] line = txt.substring(list.getLast().getPilot().length()).trim().split(" ");
            
            //Iterate through coordinates
            double[][] coords = new double[16][2];
            for(int i = 0; i < line.length; i++)
            {
                //Split each part by comma(,)
                String[] xy = line[i].split(",");
                
                //Assign values to x or y coordinates
                coords[i][0] = Double.parseDouble(xy[0]);
                coords[i][1] = Double.parseDouble(xy[1]);
            }
            //Iterate for calculations
            list.getLast().setArea(calculate(coords));
        }
        
        //Close Reader
        in.close();
        
        //Create 2nd Reader
        Scanner cmd = new Scanner(new File("commands.txt"));
        while(cmd.hasNextLine())
        {
            String txt = cmd.nextLine();
            if(!txt.matches("(sort (area|pilot) (asc))|(\\w+ ?\\w*\\n)|(\\d+)"))
            {
                //Needs Implementation//
            }
            //Needs Implementation//
        }
        
        //Close Reader
        cmd.close();
        
        //Output to text file
        outputAreas(list);
    }

    //Perform Summation Action
    public static double calculate(double[][] coords)
    {
        double num = 0;
        for(int i = 0; i < coords.length-1; i++)
        {
            if(coords[0][0] == coords[i][0] && coords[0][1] == coords[i][1] && i != 0)
                break;
            num += (coords[i+1][0] + coords[i][0]) * (coords[i+1][1] - coords[i][1]);
        }
        return .5 * Math.abs(num);
    }
    
    //Print Information to text file
    public static <E> void outputAreas(Linker<E> list) throws IOException
    {
        PrintWriter out = new PrintWriter(new File("pilot_areas.txt"));
        if(!(list.getFirst() instanceof Payload))
            return;
        for(int i = 0; i < list.size(); i++)
        {
            Payload item = (Payload)list.get(i);
            if(item != null)
            {
                System.out.printf("%-15s %-5.2f %n", item.getPilot(), item.getArea());
                out.format("%-15s %-5.2f %n", item.getPilot(), item.getArea());
            }
        }
    }
    
    public static void outputResults()
    {
        //Needs Implementation//
    }
}