package TieFighter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
	//Create New Graph
	Graph galaxy = new Graph();
	
	//Iterate through and parse Galaxy
	Scanner in1 = new Scanner(new File("galaxy.txt"));
	while(in1.hasNextLine())
	{
	    //Error Prevention
	    String line = in1.nextLine();
	    if(line.isEmpty())
		continue;
	    
	    //First Number is Point
	    String first = line.substring(0, line.indexOf(" "));
	    int point = Integer.parseInt(first);
	    galaxy.insertPoint(point);
	    line = line.replaceFirst(first + " ", "");
	    
	    //Numbers after are Edges,Weights
	    String[] parts = line.split(" ");
	    for(String part : parts)
	    {
		String[] temp = part.split(",");
//		System.out.println(point + " : " + temp[0] + " : " + temp[1]);
		galaxy.insertConnection(point, Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
	    }
	    
	    //Test Depth First Search
//	    galaxy.dfs(point);
	}
	in1.close();
	
	//Storage Unit for Output
	class Output
	{
	    public String name;
	    public int length;
	    public boolean valid;
	}
	ArrayList<Output> list = new ArrayList<>();
	
	//Iterate through and parse Routes
	Scanner in2 = new Scanner(new File("pilot_routes.txt"));
	while(in2.hasNextLine())
	{
	    //Error Prevention
	    String line = in2.nextLine();
	    if(line.isEmpty())
		continue;
	    
	    //Find and Remove Name
	    Output x = new Output();
	    Matcher findName = Pattern.compile("([a-zA-Z-']+\\ ?)+").matcher(line);
	    if(findName.find())
		x.name = findName.group();
	    line = findName.replaceFirst("");
	    
	    //Split by ( )
	    String[] parts = line.split(" ");
	    
	    //Validate Path
	    boolean valid = true;
	    int length = 0;
	    for(int i = 0; i < parts.length-1; i++)
	    {
		int index = galaxy.getIndex(Integer.parseInt(parts[i]));
		int indexNeighbor = galaxy.getNeighbors(index).indexOf(Integer.parseInt(parts[i+1]));
		if(indexNeighbor == -1)
		{
		    valid = false;
		    break;
		}
		length += galaxy.getWeights(index).get(indexNeighbor);
	    }
	    
	    //Store in ArrayList
	    x.length = valid ? length : -1;
	    x.valid = valid;
	    list.add(x);
	}
	in2.close();
	
	//Sort Output
	for(int i = 0; i < list.size(); i++)
	    for(int j = list.size()-1; j > i; j--)
		//Case of equal length && greater name || greater length
		if((list.get(i).length == list.get(j).length && list.get(i).name.compareTo(list.get(j).name) > 0)
		    || list.get(i).length < list.get(j).length)
		{
		    Output temp = list.get(i);
		    list.set(i, list.get(j));
		    list.set(j, temp);
		}
	
	//Print Out to File
	PrintWriter out = new PrintWriter(new File("patrols.txt"));
	for(Output s : list)
	{
	    String str = String.format("%-25s %-8s %-10s",
		    s.name,
		    s.valid ? s.length : "",
		    s.valid ? "Valid" : "Invalid");
//	    System.out.println(str);
	    out.println(str);
	}
	out.close();
    }
}
