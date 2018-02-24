//Ishaan Bharal (ixb170930)

package TieFighter;

public class Payload<E> implements Comparable<E>
{
    private String pilot;
    private double area;
    private boolean flag;
    
    public <E extends Comparable<E>> Payload(String pilot){this.pilot = pilot;}

    @Override
    public int compareTo(E obj)
    {
        return obj instanceof Payload ? (int)(this.area - ((Payload)obj).area) : null;
    }

    public String getPilot(){return pilot;}
    public void setPilot(String pilot){this.pilot = pilot;}
    public double getArea(){return area;}
    public void setArea(double area){this.area = area;}
    public boolean isFlag(){return flag;}
    public void setFlag(boolean flag){this.flag = flag;}
}
