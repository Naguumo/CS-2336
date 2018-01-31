//Ishaan Bharal (ixb170930)
package Calculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main
{
    private static final String REG_NUMBER = "\\-?\\d+\\.?\\d*+"; //Matches any double as a string
    private static final String REG_COMPLEX = REG_NUMBER + "\\s[\\+\\-]\\s" + REG_NUMBER + "i"; //Matches any complex number
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner in = new Scanner(new File("expressions.txt")); //Setup Reader
        PrintWriter out = new PrintWriter(new File("results.txt")); //Setup Writer
        
        System.out.println("Original:\t\tValue:");
        
        while(in.hasNextLine())
        {
            String line = in.nextLine(); //Read in Line
//            if(line.matches(REG_NUMBER + "\\s[\\+\\-]\\s" + REG_NUMBER + "i")) //Case of Complex Numbers
//            {
//                System.out.println("Complex");
//                double real = Double.parseDouble(line.substring(0,line.indexOf(" ")));
//                double imaginary = Double.parseDouble(line.substring(line.lastIndexOf(" "), line.indexOf("i")));
//                ComplexNumber num = new ComplexNumber(real, (line.contains("-")?-1:1)*imaginary);
//                
//                System.out.print(num.toString() + "\t\t");
//                System.out.print(num.toString());
//            }
//            else if(line.matches(REG_NUMBER + "\\s(\\<|\\>|\\=|(\\/\\=))\\s" + REG_NUMBER)) //Case of Comparison
//            {
//                System.out.println("Comparison");
//                Number a = new Number(Double.parseDouble(line.substring(0,line.indexOf(" "))));
//                Number b = new Number(Double.parseDouble(line.substring(line.lastIndexOf(" "), line.length())));
//                
//                boolean result = false;
//                String operator = line.charAt(line.indexOf(" ")+1) + "";
//                switch(operator)
//                {
//                    case "<":
//                        result = a.compareTo(b) < 0;
//                        break;
//                    case ">":
//                        result = a.compareTo(b) > 0;
//                        break;
//                    case "=":
//                        result = a.equals(b);
//                        break;
//                    case "/=":
//                        result = !a.equals(b);
//                        break;
//                }
//                System.out.print(a.toString() + " " + operator + " " + b.toString() + "\t\t" + result);
//            }
//            else if(line.matches(REG_NUMBER + "i"))
//            {
//                System.out.println("Imaginary");
//                ComplexNumber num = new ComplexNumber(0, Double.parseDouble(line.substring(0,line.length()-1)));
//                
//                System.out.print(num.toString() + "\t\t");
//                System.out.print(num.toString());
//            }
//            else if(line.matches(REG_NUMBER))
//            {
//                System.out.println("Real");
//                Number num = new Number(Double.parseDouble(line));
//                
//                System.out.print(num.toString() + "\t\t");
//                System.out.print(num.toString());
//            }
//            else
//            {
//                System.out.println("Error");
//                continue;
//            }
            System.out.println();
        }
        
        in.close();
        out.close();
    }
}