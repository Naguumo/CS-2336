//Ishaan Bharal (ixb170930)
package Calculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    private static final String REG_REAL = "\\-?\\d+\\.?\\d*"; //REGEX for Real Number
    private static final String REG_IMAGINARY = REG_REAL + "i"; //REGEX for Imaginary Number
    private static final String REG_COMPLEX = REG_REAL + "[+\\-]" + REG_REAL + "i"; //REGEX for Complex Number
    
    private static final String REG_PLUSMINUS = "[+\\-\\*\\/]"; //REGEX for +,-,*,/
    private static final String REG_COMPARE = "<|>|=|(\\/=)"; //REGEX for <,>,=,/=
    
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner in = new Scanner(new File("expressions.txt")); //Setup Reader
        PrintWriter out = new PrintWriter(new File("results.txt")); //Setup Writer
        
        escape: while(in.hasNextLine()) //Iterate through each line of File
        {
            String line = in.nextLine(); //Read in Line
            if(line.equals("")) //Skip Iteration if Blank Line
                continue;
            
            //Match Pattern and Seperate Parts
            Matcher pos = Pattern.compile(REG_COMPLEX + "|" + REG_IMAGINARY + "|" + REG_REAL + "|" + REG_COMPARE + "|" + REG_PLUSMINUS).matcher(line);
            String[] parts = {"","",""};
            for(int i = 0; i < parts.length; i++)
            {
                //Place matched pattern into array
                if(pos.find())
                    parts[i] = pos.group();
                //Skip Iteration if Invalid Input
                if(parts[i].isEmpty())
                    continue escape;
            }
            
            //Create Number/ComplexNumber Objects for Inputs and Evaluate
            Object res = evaluate(getObject(parts[0]), getObject(parts[2]), parts[1]);
            
            //Formatted Output, Writes to File
            System.out.printf("%-30s%-1s%n", line, res.toString());
            out.format("%-30s%-1s%n", line, res.toString());
        }
        
        //Close Reader/Writer
        in.close();
        out.close();
    }
    
    //Returns Corresponding Object for Input Type
    public static Object getObject(String str)
    {
        if(str.matches(REG_COMPLEX))
            //Checks for Proper Substring Match and Assigns to Values
            return new ComplexNumber(
                Double.parseDouble(str.substring(0, str.lastIndexOf(str.substring(1).contains("-") ? "-" : "+"))),
                Double.parseDouble(str.substring(str.lastIndexOf(str.substring(1).contains("-") ? "-" : "+"), str.indexOf("i"))));
        else if(str.matches(REG_IMAGINARY))
            return new ComplexNumber(0, Double.parseDouble(str.substring(0, str.indexOf("i"))));
        else if(str.matches(REG_REAL))
            return new Number(Double.parseDouble(str));
        return null;
    }
    
    //Does Math
    public static Object evaluate(Object a, Object b, String op)
    {
        //Cast As ComplexNumbers Regardless of Actuality
        ComplexNumber cA = (a instanceof ComplexNumber)? (ComplexNumber)a : new ComplexNumber(((Number)a).getNumber(), 0);
        ComplexNumber cB = (b instanceof ComplexNumber)? (ComplexNumber)b : new ComplexNumber(((Number)b).getNumber(), 0);
        ComplexNumber result = new ComplexNumber(0,0);
        
        //Varied Action based on Operator
        switch(op)
        {
            case "-":
                //Makes Second Number Negative, Then Processes Same as +
                cB.setImaginaryNumber(-1*cB.getImaginaryNumber());
                cB.setNumber(-1*cB.getNumber());
                //Doesn't break, continues on to same processes as addition
            case "+":
                //Adds Both Attributes Independently
                result = new ComplexNumber(
                        cA.getNumber() + cB.getNumber(),
                        cA.getImaginaryNumber() + cB.getImaginaryNumber());
                break;
            case "*":
                //(a1 + a2i)(b1 + b2i) -> (a1*b1-a2*b2) + (a1*b2+a2*b1)i
                result = new ComplexNumber(
                        cA.getNumber()*cB.getNumber()-cA.getImaginaryNumber()*cB.getImaginaryNumber(),
                        cA.getNumber()*cB.getImaginaryNumber()+cA.getImaginaryNumber()*cB.getNumber());
                break;
            case "/":
                ComplexNumber conjugate = new ComplexNumber(cB.getNumber(),cB.getImaginaryNumber()*-1); //Find Conjugate
                //FOIL Numerator
                ComplexNumber numer = new ComplexNumber( //A*conjugate
                        cA.getNumber()*conjugate.getNumber()-cA.getImaginaryNumber()*conjugate.getImaginaryNumber(),
                        cA.getNumber()*conjugate.getImaginaryNumber()+cA.getImaginaryNumber()*conjugate.getNumber());
                //FOIL Denominator
                double denom = cB.getNumber()*conjugate.getNumber()-cB.getImaginaryNumber()*conjugate.getImaginaryNumber(); //B*conjugate
                //Set As Results
                result = new ComplexNumber(
                        numer.getNumber()/denom,
                        numer.getImaginaryNumber()/denom);
                break;
            //Comparison Operators
            case "<":
                return cA.compareTo(cB) < 0;
            case ">":
                return cA.compareTo(cB) > 0;
            case "=":
                return cA.equals(cB);
            case "//=":
                return !cA.equals(cB);
        }
        return (result.getImaginaryNumber() == 0)? new Number(result.getNumber()) : result;
    }
}