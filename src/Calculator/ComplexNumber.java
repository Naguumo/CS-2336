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
    public String toString(){return (this.getNumber() != 0 ? //Checks if has Real Number Component
            String.format("%.2f", this.getNumber()) + //Formats with 2 Decimal Place Formatting
                ((this.getImaginaryNumber() < 0)? "-" : "+") : "") + //Checks for proper Operation Symbol
            String.format("%.2fi", Math.abs(nImaginary));} //Formats with 2 Decimal Place Formatting
    public boolean equals(ComplexNumber a){return nImaginary == a.getImaginaryNumber() && this.getNumber() == a.getNumber();}
}