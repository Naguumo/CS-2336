//Ishaan Bharal (ixb170930)

package TieFighter2;

public class Payload<E> implements Comparable<E>
{
    private String pilot;
    private double area;
    private static boolean flag = true; //True = Area, False = Pilot
    private static boolean flag2 = true; //True = Ascending, False = Descending
    
    public <E extends Comparable<E>> Payload(String pilot){this.pilot = pilot;}

    @Override
    public int compareTo(E obj)
    {
        return obj instanceof Payload ? 
                (flag ? 
                    (flag2 ? (int)(this.area - ((Payload)obj).area) : (int)(((Payload)obj).area - this.area)) : 
                    (flag2 ? (this.pilot.compareTo(((Payload)obj).pilot)) : ((Payload)obj).pilot.compareTo(this.pilot))) : 
                null;
    }
    
    @Override
    public String toString(){return String.format("%-15s %-5.2f %n", this.pilot, this.area);}

    public String getPilot(){return pilot;}
    public void setPilot(String pilot){this.pilot = pilot;}
    public double getArea(){return area;}
    public void setArea(double area){this.area = area;}
    public static boolean getFlag(){return flag;}
    public static void setFlag(boolean flag){Payload.flag = flag;}
    public static boolean getFlag2(){return flag2;}
    public static void setFlag2(boolean flag){Payload.flag2 = flag;}
}
