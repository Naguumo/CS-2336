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
        
        //Declare Arrays
        String [] names = new String[20];
        double[][][] coords = new double[20][16][2];
        
        //Iterate through lines
        for(int i = 0; i < coords.length && in.hasNextLine(); i++) //Exit if theres no more text
        {
            String txt = in.nextLine();
            
            //Makes sure input is valid
            if(!txt.matches("\\w+\\s?\\w*(\\s-?\\d*\\.?\\d*,-?\\d*\\.?\\d*)+"))
                continue;
            
            //Assign first element as name of pilot
            names[i] = txt.split("(\\s-?\\d*\\.?\\d*,-?\\d*\\.?\\d*)+")[0];
            
            //Split Line of text by space( )
            String[] line = txt.substring(names[i].length()).split(" ");
            
            //Iterate through coordinates
            for(int j = 1; j < line.length; j++)
            {
                //Split each part by comma(,)
                String[] xy = line[j].split(",");
                
                //Assign values to x or y coordinates
                coords[i][j-1][0] = Double.parseDouble(xy[0]);
                coords[i][j-1][1] = Double.parseDouble(xy[1]);
            }
        }
        
        //Close Reader
        in.close();
        
        //Iterate for calculations
        double[] summations = new double[names.length];
        for(int i = 0; i < names.length; i++)
            summations[i] = calculate(coords[i]);
        
        //Output to text file
        output(names, summations);
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
    public static void output(String[] names, double[] areas) throws IOException
    {
        //Create Writer
        PrintWriter out = new PrintWriter(new File("pilot_areas.txt"));

        //Iterate through lines
        for(int i = 0; i < names.length; i++)
            if(names[i] != null) //Don't Write Blanks
            {
                System.out.printf("%-15s %-5.2f %n", names[i], areas[i]);
                out.format("%-15s %-5.2f %n", names[i], areas[i]);
            }

        //Close Writer
        out.close();
    }
}