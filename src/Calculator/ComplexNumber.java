//Ishaan Bharal (ixb170930)
package Calculator;

public class ComplexNumber extends Number
{
    private double nImaginary; //Stores Imaginary Numbers

    public ComplexNumber(double real, double imaginary) //Constructor
    {
        super(real);
        nImaginary = imaginary;
    }
    
    public double getImaginaryNumber(){return nImaginary;}
    public void setImaginaryNumber(double n){nImaginary = n;}
    public String toString(){return (this.getNumber() == 0)? //Checks if has Real Number Component -> Is Complex Number, Not Imaginary
                String.format("%.2fi", nImaginary): //Formats with 2 Decimal Place Formatting
                String.format("%.2f%s%.2fi",this.getNumber(), (nImaginary > 0)?"+":"-", Math.abs(nImaginary));} //Corrects to proper Operation Symbol
    public boolean equals(ComplexNumber a){return nImaginary == a.getImaginaryNumber() && this.getNumber() == a.getNumber();}
}