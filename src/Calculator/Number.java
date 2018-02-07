//Ishaan Bharal (ixb170930)
package Calculator;

public class Number
{
    protected double nReal; //Stores Real Numbers
    
    public Number(double n){nReal = n;} //Constructor
    
    public double getNumber(){return nReal;}
    public void setNumber(double n){nReal = n;}
    @Override
    public String toString(){return String.format("%.2f", nReal);} //Formats with 2 Decimal Place Formatting
    @Override
    public boolean equals(Object a){return (a instanceof Number) ? nReal == ((Number)a).getNumber() : false;} //Required but Useless
}