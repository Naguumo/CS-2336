//Ishaan Bharal (ixb170930)

package TieFighter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
{
    public static void main(String[] args)
    {
        //Create Reader
        Scanner in = null;
        try
        {
            in = new Scanner(new File("pilot_routes.txt"));
        } catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Declare Arrays
        String [] names = new String[20];
        double[][][] coords = new double[20][16][2];
        
        //Iterate through lines
        for(int i = 0; i < coords.length; i++)
        {
            //Exit if theres no more text
            if(!in.hasNext()) break;
            
            //Split Line of text by space( )
            String[] line = in.nextLine().split(" ");
            names[i] = line[0];//Assign first element as name of pilot
            
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
            num += (coords[i+1][0]+coords[i][0])*(coords[i+1][1]-coords[i][1]);
        return .5 * Math.abs(num);
    }
    
    //Print Information to text file
    public static void output(String[] names, double[] areas)
    {
        try
        {
            //Create Writer
            FileWriter out = new FileWriter(new File("pilot_areas.txt"));
            
            //Iterate through lines
            for(int i = 0; i < names.length; i++)
                if(names[i] != null) //Don't Write Blanks
                    out.write(names[i] + " \t" + areas[i] + " \n");
            
            //Close Writer
            out.close();
        } catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}