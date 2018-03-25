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
        Scanner in = new Scanner(new File("pilot_routes.txt")); //Create Reader
        Linker<Payload> list = new Linker<>(); //Declare List
        
        //Iterate through Lines
        while(in.hasNextLine()) //Exit if theres no more text
        {
            String txt = in.nextLine(); //Take in Line
            if(!txt.matches("([\\w-']+\\s?)+(\\s-?\\d+\\.?\\d*,-?\\d+\\.?\\d*)+"))
                continue;//Makes sure input is valid
            
            //Add Object to List using Name
            list.add(new Payload(txt.split("(\\s-?\\d*\\.?\\d*,-?\\d*\\.?\\d*)+")[0]));
            
            //Split Line of text by space( )
            String[] line = txt.substring(list.getLast().getPilot().length()).trim().split(" ");
            
            //Iterate through coordinates
            ArrayList<Double[]> coords = new ArrayList<>();
            for (String piece : line)
            {
                try{
		String[] xy = piece.split(","); //Split each pair by comma(,)
		Double[] dub = {Double.parseDouble(xy[0]),Double.parseDouble(xy[1])};
                coords.add(dub); //Assign values to x or y coordinates
		}catch(NumberFormatException e){}
            }
            list.getLast().setArea(calculate(coords)); //Calculate and Assign Area
        }
        in.close(); //Close Reader
        
        //Create 2nd Reader / Writer
        Scanner cmd = new Scanner(new File("commands.txt"));
        PrintWriter rslt = new PrintWriter(new File("results.txt"));
        
        //Iterate through commands
        while(cmd.hasNextLine()) //Exit if theres no more text
        {
            String txt = cmd.nextLine(); //Takes in Line, Stores Output
            if(txt.matches("sort\\ (area|pilot)\\ (asc|dec)")) //Case for Sort
            {
                String[] line = txt.split(" "); //Split Command Parts by Space
                
                //Boolean Values to Compare By
                boolean area = line[1].equals("area");
                boolean asc = line[2].equals("asc");
                
                list.sort(area, asc); //Sort Function
                
                //Formatted Output
                txt = String.format("%-20sHead: %-10sTail: %s%n", 
                    txt.toUpperCase(),
                    (area)? String.format("%.2f",list.getFirst().getArea()) + "," : list.getFirst().getPilot() + ",",
                    (area)? String.format("%.2f",list.getLast().getArea()) : list.getLast().getPilot());
            }
            else if(txt.matches("\\d+\\.?\\d*")) //Case for Number Search
                //Formatted Output, calls Search function
                txt = String.format("%-20s%s%n",txt.toUpperCase(),(list.search(txt, true) == -1)? "Not Found" : "Found");
            else if(txt.matches("([\\w-']+\\s?)+")) //Case for Name Search
                //Formatted Output, calls Search function
                txt = String.format("%-20s%s%n",txt.toUpperCase(),(list.search(txt, false) == -1)? "Not Found" : "Found");
	    else
		continue;

            //Writes to Result File
            System.out.print(txt);
            rslt.write(txt);
        }
        
        // Prints Area Information To File
        PrintWriter out = new PrintWriter(new File("pilot_areas.txt"));
        System.out.print("\n" + list.toString());
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
        for (int i = 0; i < coords.size() - 1; i++)
        {
            if (coords.get(0)[0].equals(coords.get(i)[0]) && coords.get(0)[1].equals(coords.get(i)[1]) && i != 0)
                break;
            num += (coords.get(i + 1)[0] + coords.get(i)[0]) * (coords.get(i + 1)[1] - coords.get(i)[1]);
        }
        return .5 * Math.abs(num);
    }
}