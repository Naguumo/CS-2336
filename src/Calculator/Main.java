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
        
        while(in.hasNextLine()) //Iterate through each line of File
        {
            String line = in.nextLine(); //Read in Line
            if(line.equals("")) //Skip Iteration if Blank Line
                continue;
            
            //Match Pattern and Seperate Parts
            Matcher pos = Pattern.compile(REG_COMPLEX + "|" + REG_IMAGINARY + "|" + REG_REAL + "|" + REG_COMPARE + "|" + REG_PLUSMINUS).matcher(line);
            String[] parts = {"","",""};
            for(int i = 0; i < parts.length; i++)
                if(pos.find())
                    parts[i] = pos.group();
            
            //Skip Iteration if Invalid Input
            if(parts[0].isEmpty() || parts[1].isEmpty() || parts[2].isEmpty())
                continue;
            
            //Create Number/ComplexNumber Objects for Inputs
            Object ob1 = (!parts[0].equals(""))? getObject(parts[0]) : null;
            Object ob2 = (!parts[2].equals(""))? getObject(parts[2]) : null;
            Object res = (ob2 == null)? ob1 : evaluate(ob1, ob2, parts[1]);
            
            //Output Original Values
            String inNum1 = "", inNum2 = "", outNum = "";
            if(ob1 instanceof ComplexNumber || ob1 instanceof Number)
                inNum1 = ob1.toString();
            if(ob2 instanceof ComplexNumber || ob2 instanceof Number)
                inNum2 = ob2.toString();
            
            //Output Result
            if(res instanceof ComplexNumber || res instanceof Number)
                outNum = res.toString();
            else if(res instanceof Boolean)
                outNum = ((Boolean) res) + "";
            
            //Formatted Output, Writes to File
            System.out.printf("%-30s%-1s %n", inNum1 + " " + parts[1] + " " + inNum2, outNum);
            out.format("%-30s%-1s %n", inNum1 + " " + parts[1] + " " + inNum2, outNum);
        }
        
        //Close Reader/Writer
        in.close();
        out.close();
    }
    
    //Returns Corresponding Object for Input Type
    public static Object getObject(String str)
    {
        //Everything is ComplexNumber till end
        double real = 0, imaginary = 0;
        if(str.matches(REG_COMPLEX))
        {
            real = (str.contains("-")) ? 
                    Double.parseDouble(str.substring(0, str.indexOf("-"))) :
                    Double.parseDouble(str.substring(0, str.indexOf("+")));
            imaginary = (str.contains("-")) ? 
                    Double.parseDouble(str.substring(str.indexOf("-"), str.indexOf("i"))) * -1 :
                    Double.parseDouble(str.substring(str.indexOf("+"), str.indexOf("i")));
        }
        else if(str.matches(REG_IMAGINARY))
            imaginary = Double.parseDouble(str.substring(0, str.indexOf("i")));
        else if(str.matches(REG_REAL))
            real = Double.parseDouble(str);
        return (imaginary == 0)? 
                new Number(real) :
                new ComplexNumber(real, imaginary);
    }
    
    //Does Math
    public static Object evaluate(Object a, Object b, String op)
    {
        //Cast As ComplexNumbers Regardless of Actuality
        ComplexNumber comA = (a instanceof ComplexNumber)? (ComplexNumber) a :
                (a instanceof Number)? new ComplexNumber(((Number)a).getNumber(), 0) : new ComplexNumber(0,0);
        ComplexNumber comB = (b instanceof ComplexNumber)? (ComplexNumber)b :
                (b instanceof Number)? new ComplexNumber(((Number)b).getNumber(), 0) : new ComplexNumber(0,0);
        ComplexNumber result = new ComplexNumber(0,0);
        
        //Varied Action based on Operator
        switch(op)
        {
            case "-":
                //Makes Second Number Negative, Then Processes Same as +
                comB.setImaginaryNumber(-1*comB.getImaginaryNumber());
                comB.setNumber(-1*comB.getNumber());
            case "+":
                //Adds Both Attributes Independently
                result = new ComplexNumber(
                        comA.getNumber() + comB.getNumber(),
                        comA.getImaginaryNumber() + comB.getImaginaryNumber());
                break;
            case "*":
                //(a1 + a2i)(b1 + b2i) -> (a1*b1-a2*b2) + (a1*b2+a2*b1)i
                result = new ComplexNumber(
                        comA.getNumber()*comB.getNumber()-comA.getImaginaryNumber()*comB.getImaginaryNumber(),
                        comA.getNumber()*comB.getImaginaryNumber()+comA.getImaginaryNumber()*comB.getNumber());
                break;
            case "/":
                ComplexNumber conjugate = new ComplexNumber(comB.getNumber(),comB.getImaginaryNumber()*-1); //Find Conjugate
                //FOIL Numerator
                ComplexNumber numer = new ComplexNumber( //A*conjugate
                        comA.getNumber()*conjugate.getNumber()-comA.getImaginaryNumber()*conjugate.getImaginaryNumber(),
                        comA.getNumber()*conjugate.getImaginaryNumber()+comA.getImaginaryNumber()*conjugate.getNumber());
                //FOIL Denominator
                ComplexNumber denom = new ComplexNumber( //B*conjugate
                        comB.getNumber()*conjugate.getNumber()-comB.getImaginaryNumber()*conjugate.getImaginaryNumber(),
                        comB.getNumber()*conjugate.getImaginaryNumber()+comB.getImaginaryNumber()*conjugate.getNumber());
                //Set As Results
                result = new ComplexNumber(
                        numer.getNumber()/denom.getNumber(),
                        numer.getImaginaryNumber()/denom.getNumber());
                break;
            case "<":
                return Math.abs(comA.getNumber()+comA.getImaginaryNumber()) < Math.abs(comB.getNumber()+comB.getImaginaryNumber());
            case ">":
                return Math.abs(comA.getNumber()+comA.getImaginaryNumber()) > Math.abs(comB.getNumber()+comB.getImaginaryNumber());
            case "=":
                return comA.equals(comB);
            case "//=":
                return !comA.equals(comB);
        }
        return (result.getImaginaryNumber() == 0)? new Number(result.getNumber()) : result;
    }
}