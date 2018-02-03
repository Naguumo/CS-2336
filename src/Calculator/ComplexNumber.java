//Ishaan Bharal (ixb170930)
package Calculator;

public class ComplexNumber extends Number
{
    private double nImaginary;

    public ComplexNumber(double real, double imaginary)
    {
        super(real);
        nImaginary = imaginary;
    }
    
    public double getImaginaryNumber(){return nImaginary;}
    public void setImaginaryNumber(double n){nImaginary = n;}
    public String toString(){return (this.getNumber() != 0 ?
            String.format("%.2f", this.getNumber()) + 
                ((this.getImaginaryNumber() < 0)? "-" : "+") : "") +
            String.format("%.2fi", Math.abs(nImaginary));}
    public boolean equals(ComplexNumber a){return nImaginary == a.getImaginaryNumber() && this.getNumber() == a.getNumber();}
}