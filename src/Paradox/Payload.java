//Ishaan Bharal(ixb170930)
package Paradox;

public class Payload implements Combinable<Payload>
{
    private int coefficient, exponent;
    private static int definitedown, definiteup;
    private static double definite;
    
    public Payload()
    {
	coefficient = 1;
	exponent = 0;
    }
    public Payload(int e)
    {
	coefficient = 1;
	exponent = e;
    }
    public Payload(int c, int e)
    {
	coefficient = c;
	exponent = e;
    }

    public int getCoefficient(){return coefficient;}
    public void setCoefficient(int coefficient){this.coefficient = coefficient;}

    public int getExponent(){return exponent;}
    public void setExponent(int exponent){this.exponent = exponent;}
    
    //Develops Formatted Antiderivative
    public String getIntExponent(){return exponent+1 + "";}
    public String getFormattedIntegral()
    {
	int expoInt = exponent+1;
	double num = ((double)coefficient) / ((double)expoInt);
	
	if(exponent == -1)
	    return coefficient + "lnx + ";
	
	String coff, expo;
	if(num == 0)
	    return "";
	else if(num == 1)
	    coff = "";
	else if(num == -1)
	    coff = "-";
	else
	{
	    if(num == Math.floor(num))
		coff = (int)num + "";
	    else
		coff = "(" + coefficient + "/" + expoInt + ")";
	}
	
	if(expoInt == 0)
	    expo = "";
	else if(expoInt == 1)
	    expo = "x";
	else
	    expo = "x^" + getIntExponent();

	return  coff + expo + " + ";
    }
    
    //Case for Definite Integrals
    public static int getDefiniteDown(){return definitedown;}
    public static void setDefiniteDown(int definitelow){Payload.definitedown = definitelow;}

    public static int getDefiniteUp(){return definiteup;}
    public static void setDefiniteUp(int definiteup){Payload.definiteup = definiteup;}
    
    //Develops Antiderivative for Definite
    public static String getDefinite(){return (definite == Math.floor(definite)) ? (int)definite + "" : String.format("%.3f", definite);}
    public static void setDefinite(){definite = 0;}
    public void addDefinite()
    {
	if(exponent == -1)
	{
	    definite += (coefficient * Math.log(definiteup)) - (coefficient * Math.log(definitedown));
	    return;
	}
	double coff = (double)coefficient / ((double)exponent + 1);
	definite += (coff * Math.pow(definiteup, exponent + 1)) - (coff * Math.pow(definitedown, exponent + 1));
    }

    @Override
    public int compareTo(Payload o){return this.exponent - o.exponent;}
    @Override
    public void combineWith(Payload o){this.coefficient += o.coefficient;}
    @Override
    public String toString(){return coefficient + "x^" + exponent;}
}