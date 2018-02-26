//Ishaan Bharal (ixb170930)

package TieFighter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
            if(!txt.matches("[\\w-']+\\s?[\\w-']*(\\s-?\\d*\\.?\\d*,-?\\d*\\.?\\d*)+"))
                continue;
            
            //Add Object to List with Name
            list.addLast(new Payload(txt.split("(\\s-?\\d*\\.?\\d*,-?\\d*\\.?\\d*)+")[0]));
            
            //Split Line of text by space( )
            String[] line = txt.substring(list.getLast().getPilot().length()).trim().split(" ");
            
            //Iterate through coordinates
            ArrayList<Double[]> coords = new ArrayList<>();
            for (String piece : line)
            {
                //Split each part by comma(,)
                String[] xy = piece.split(",");
                //Assign values to x or y coordinates
                Double[] dub = {Double.parseDouble(xy[0]),Double.parseDouble(xy[1])};
                coords.add(dub);
            }
            //Iterate for calculations
            list.getLast().setArea(calculate(coords));
        }
        
        //Close Reader
        in.close();
        
        //Create 2nd Reader / Writer
        Scanner cmd = new Scanner(new File("commands.txt"));
        PrintWriter rslt = new PrintWriter(new File("results.txt"));
        
        while(cmd.hasNextLine())
        {
            String txt = cmd.nextLine();
            if(txt.matches("sort\\ (area|pilot)\\ (asc|dec)")) //Case for Sort
            {
                String[] line = txt.split(" ");
                boolean area = false, asc = false;
                switch(line[1])
                {
                    case "area":
                        area = true;
                        break;
                    case "pilot":
                        area = false;
                        break;
                }
                
                switch(line[2])
                {
                    case "asc":
                        asc = true;
                        break;
                    case "dec":
                        asc = false;
                        break;
                }
                
                list.sort(area, asc);
                System.out.printf("%-20sHead: %-5s,%-5sTail: %s%n", txt,
                    (area)? String.format("%.2f",list.getFirst().getArea()): list.getFirst().getPilot(),"",
                    (area)? String.format("%.2f",list.getLast().getArea()) : list.getLast().getPilot());
                rslt.format("%-20s%n", txt);
            }
            else if(txt.matches("\\d+\\.?\\d*")) //Case for Number Search
            {
                System.out.printf("%-15s%s%n",txt,(list.search(txt, true) == -1)? "Not Found" : "Found");
                rslt.format("%-20s%s%n",txt,(list.search(txt, true) == -1)? "Not Found" : "Found");
            }
            else if(txt.matches("[\\w-']+\\ ?[\\w-']*")) //Case for Name Search
            {
                System.out.printf("%-15s%s%n",txt,(list.search(txt, false) == -1)? "Not Found" : "Found");
                rslt.format("%-20s%s%n",txt,(list.search(txt, false) == -1)? "Not Found" : "Found");
            }
            else //Case for Invalid Input
                System.out.println("FAILED: " + txt);
        }
        
        // Prints Information To File
        PrintWriter out = new PrintWriter(new File("pilot_areas.txt"));
        System.out.println();
        System.out.print(list.toString());
        out.write(list.toString());
        
        //Close Reader / Writers
        cmd.close();
        rslt.close();
        out.close();
    }

    //Perform Summation Action
    private static double calculate(ArrayList<Double[]> coords)
    {
        double num = 0;
        for(int i = 0; i < coords.size()-1; i++)
        {
            if(coords.get(0)[0].equals(coords.get(i)[0]) && coords.get(0)[1].equals(coords.get(i)[1]) && i != 0)
                break;
            num += (coords.get(i+1)[0] + coords.get(i)[0]) * (coords.get(i+1)[1] - coords.get(i)[1]);
        }
        return .5 * Math.abs(num);
    }
}