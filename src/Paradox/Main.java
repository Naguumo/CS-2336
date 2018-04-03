//Ishaan Bharal(ixb170930)
package Paradox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
	//Creates readers/writers
	Scanner in = new Scanner(new File("integrals.txt"));
	PrintWriter out = new PrintWriter(new File("answers.txt"));
	
	while(in.hasNextLine())
	{
	    //Takes in Line
	    String line = in.nextLine();
	    String res = "";
	    
	    //Uses REGEX to ensure correctness
	    if(line.matches("\\|\\ (\\-?\\d*x?(\\^\\-?\\d*)?\\ ?[+-]?\\s?)*\\ dx"))
		res = indefinite(line.substring(line.indexOf("| ")+2, line.indexOf(" dx")).replaceAll(" ", ""));
	    else if(line.matches("(\\-?\\d+)\\|(\\-?\\d+)\\ (\\-?\\d*x?(\\^\\-?\\d*)?\\ ?[+-]?\\s?)*dx"))
	    {
		//Sets statics to do calculations
		Payload.setDefinite();
		Payload.setDefiniteDown(Integer.parseInt(line.substring(0, line.indexOf("|"))));
		Payload.setDefiniteUp(Integer.parseInt(line.substring(line.indexOf("|")+1, line.indexOf(" "))));
		line = line.replaceFirst("(\\-?\\d+)\\|(\\-?\\d+)", "");
		res = definite(line.substring(0, line.indexOf(" dx")).replaceAll(" ", ""));
	    }
	    else
		continue;
	    
	    //Print to File
	    System.out.println(res + "\n--------------------------");
	    out.println(res);
	}
	
	//Close Reader/Writer
	in.close();
	out.close();
    }
    
    public static String indefinite(String s)
    {
	System.out.println("Indefinite: " + s);
	//Assemble BST
	BST<Payload> bst = parse(s);
	
	//Performs All Calculations
	String content =  bst.traverse("Infix",(Executable) (Object stuff) -> 
	{
	    //Converts Object into Payload
	    Payload item = (Payload)stuff;
	    
	    //Prepares String
	    return  item.getFormattedIntegral();
	}).replace("+ -", "- ");
	
	//Clear BST
	for(int i = -10; i <= 10; i++)
	    bst.delete(new Payload(i));
	
	//Return Results
	return content + "C";
    }
    public static String definite(String s)
    {
	System.out.println("Definite: " + s);
	//Assemble BST
	BST<Payload> bst = parse(s);
	
	//Performs All Calculations
	String content = bst.traverse("Infix",(Executable) (Object stuff) -> 
	{
	    //Converts Object into Payload
	    Payload item = (Payload)stuff;
	    
	    //Adds item to Definite Calculation
	    item.addDefinite();
	    
	    //Prepares String
	    return  item.getFormattedIntegral();
	}).replace("+ -", "- "); //Replace - with + as appropriate
	
	//Clear BST
	for(int i = -10; i <= 10; i++)
	    bst.delete(new Payload(i));
	
	//Replace last +
	content = content.substring(0, content.lastIndexOf("+")-1) + content.substring(content.lastIndexOf("+")+2);
	
	//Returns Results
	return content +
		", " + Payload.getDefiniteDown() + "|" + Payload.getDefiniteUp() 
		+ " = " + Payload.getDefinite();
    }
    
    //Parses into BST
    public static BST<Payload> parse(String s)
    {
	BST<Payload> bst = new BST<>();
	s = s.replaceAll("[\\+\\-\\ ]x", "1x");
	Matcher nums = Pattern.compile("\\-?\\d+").matcher(s);
	while(!s.isEmpty())
	{
	    Payload p = new Payload();
	    
	    boolean hasX = false;
	    //Case of Number With X
	    if(s.contains("x") && s.substring(0, s.indexOf("x")).matches("\\-?\\d+") && nums.find())
	    {
		hasX = true;
		p.setCoefficient(Integer.parseInt(nums.group()));
		s = s.replaceFirst(nums.group() + "x", "");
	    }
	    //Case of Just X
	    else if(s.charAt(0) == 'x')
	    {
		hasX = true;
		s = s.replaceFirst("x", "");
	    }
	    //Case of Just Number
	    else if(nums.find())
	    {
		p.setCoefficient(Integer.parseInt(nums.group()));
		s = s.replaceFirst(nums.group() + "", "");
	    }
	    
	    //Case of Exponent With X
	    if(hasX && !s.isEmpty() && s.charAt(0) == '^' && nums.find())
	    {
		p.setExponent(Integer.parseInt(nums.group()));
		s = s.replaceFirst("\\^" + nums.group(), "");
	    }
	    //Case of No Exponent
	    else if(hasX)
		p.setExponent(1);
	    
	    //Insert into BST
	    bst.insert(p);
	    
	    //Remove any + signs to avoid errors
	    if(!s.isEmpty() && s.charAt(0) == '+')
		s = s.replaceFirst("\\+", "");
	}
	return bst;
    }
}