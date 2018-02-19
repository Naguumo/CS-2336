package HW4;

public class P1
{
    public static void main(String[] args)
    {
        String[] strs = {"1001","hello","1234"};
        for (String str : strs)
            try
            {
                System.out.println(str + " : " + bin2Dec(str));
            }catch(BinaryFormatException e)
            {
                System.out.println(e.getMessage());
            }
    }
    
    public static int bin2Dec(String str) throws BinaryFormatException
    {
        boolean[] bin = new boolean[str.length()];
        int num = 0;
        for(int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) == '1')
                bin[i] = true;
            else if(str.charAt(i) != '0')
                throw new BinaryFormatException(str);
            
            num += (bin[i]) ? Math.pow(2, str.length()-1-i) : 0;
        }
        return num;
    }
}

class BinaryFormatException extends Exception
{
    public BinaryFormatException(String str)
    {
        super(str + " is an Invalid String");
    }
}