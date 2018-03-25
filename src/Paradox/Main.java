//Ishaan Bharal(ixb170930)
package Paradox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Scanner;
import jdk.nashorn.internal.objects.NativeArray;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
	Scanner in = new Scanner(new File("integrals.txt"));
	PrintWriter out = new PrintWriter(new File("answers.txt"));
	
	while(in.hasNextLine())
	{
	    //Parses Line into Array
	    String line = in.nextLine(); //Take in Entire Line
	    BST<Payload> integrals = new BST<>();//Create BST
	    
	    String[] content = line.substring(2/*after first space*/).split(" "); //Split into parts to parse in
	    for(int i = 0; i < content.length; i++)
	    {
		//May Use Switch Statement to implement Trig Functions
		int coefficient = Integer.parseInt(content[i].substring(0, content[i].indexOf("x^"))); //Coefficient is before x^
		int exponent = Integer.parseInt(content[i].substring(content[i].indexOf("x^"))); //Exponent is after x^
		integrals.add(new Payload(coefficient, exponent)); //Add to BST
	    }
	    
	    if(true/*line has number before/after | without spaces*/)
	    {
		//Case for Definite Integral
		Payload.setDefinitedown(0/*line substring before |*/);
		Payload.setDefiniteup(0/*line substring after |*/);
		line = integrals.traverse("Inorder");
	    }
	    else
	    {
		//Case for Indefinite Integral
		line = integrals.traverse("Inorder");
	    }
	    
	    out.write(line);
	}
	
	in.close();
	out.close();
    }
}
