//Ishaan Bharal (ixb170930)
package Calculator;

public class Number
{
    private double nReal;
    
    public Number(double n){nReal = n;}
    
    public double getNumber(){return nReal;}
    public void setNumber(double n){nReal = n;}
    public String toString(){return String.format("%.2f", nReal);}
    public boolean equals(double a){return nReal == a;}
}