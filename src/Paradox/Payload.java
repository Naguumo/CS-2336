//Ishaan Bharal(ixb170930)
package Paradox;

public class Payload
{
    private int coefficient, exponent;
    private static double definitedown, definiteup;
    
    public Payload()
    {
	coefficient = 1;
	exponent = 1;
    }
    public Payload(int c, int e)
    {
	coefficient = c;
	exponent = e;
    }

    public int getCoefficient()
    {
	return coefficient;
    }
    public void setCoefficient(int coefficient)
    {
	this.coefficient = coefficient;
    }

    public int getExponent()
    {
	return exponent;
    }
    public void setExponent(int exponent)
    {
	this.exponent = exponent;
    }
    
    //Develops Antiderivative
    public double getIntegralExponent()
    {
	return exponent+1;
    }
    public double getIntegralCoefficient()
    {
	return coefficient / (exponent+1);
    }

    //Case for Definite Integrals
    public static double getDefinitelow()
    {
	return definitedown;
    }
    public static void setDefinitedown(double definitelow)
    {
	Payload.definitedown = definitelow;
    }

    public static double getDefiniteup()
    {
	return definiteup;
    }
    public static void setDefiniteup(double definiteup)
    {
	Payload.definiteup = definiteup;
    }
    
    //Develops Antiderivative for Definite
    public double getDefinite()
    {
	return ((coefficient / (exponent+1)) * Math.pow(definiteup, exponent+1)) - ((coefficient / (exponent+1)) * Math.pow(definitedown, exponent+1));
    }
}
