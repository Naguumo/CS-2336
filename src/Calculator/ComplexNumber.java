//Ishaan Bharal (ixb170930)
package Calculator;

public class ComplexNumber extends Number
{
    protected double nImaginary; //Stores Imaginary Numbers

    public ComplexNumber(double real, double imaginary) //Constructor
    {
        super(real);
        nImaginary = imaginary;
    }
    
    public double getImaginaryNumber(){return nImaginary;}
    public void setImaginaryNumber(double n){nImaginary = n;}
    @Override
    public String toString(){return (nReal == 0)? //Checks if has Real Number Component -> Is Complex Number, Not Imaginary
                String.format("%.2fi", nImaginary): //Formats with 2 Decimal Place Formatting
                String.format("%.2f%s%.2fi",nReal, (nImaginary > 0)?"+":"-", Math.abs(nImaginary));} //Corrects to proper Operation Symbol
    @Override
    public boolean equals(Object a){return (a instanceof ComplexNumber)? nImaginary == ((ComplexNumber)a).getImaginaryNumber() && nReal == ((ComplexNumber)a).getNumber() : false;}
    public int compareTo(Object a){return (a instanceof ComplexNumber) ? (int)(Math.sqrt(Math.pow(nReal,2)+Math.pow(nImaginary,2))-Math.sqrt(Math.pow(((ComplexNumber)a).getNumber(),2)+Math.pow(((ComplexNumber)a).getImaginaryNumber(),2))) : 0;}
}