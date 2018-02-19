package HW4;

import java.util.ArrayList;

public class P2<E extends Comparable<E>> extends ArrayList<E>
{
    public static void main(String[] args)
    {
        int range = 10;
        
        //Integer Array
        P2<Integer> arrI = new P2<>();
        for(int i = 0; i < (int)(Math.random()*range) + 1; i++)
            arrI.add((int)(Math.random()*range)+1);
        
        arrI.sort();
        System.out.print("The Array Is: ");
        for(int i = 0; i < arrI.size(); i++)
            System.out.print(arrI.get(i) + ((arrI.size()-1 == i)?"\n":", "));
        
        int num = (int)(Math.random()*range)+1;
        System.out.printf("The number " + num + (arrI.search(num) == -1?
                    " is not contained" :
                    (" is at index " + arrI.search(num)))
                + " within the Array\n\n");
        
        ////////////////////////////////////////////////////////////////////
        
        //Double Array
        P2<Double> arrD = new P2<>();
        for(int i = 0; i < (int)(Math.random()*range) + 1; i++)
            arrD.add((Math.random()*range)+1);
        
        arrD.sort();
        System.out.print("The Array Is: ");
        for(int i = 0; i < arrD.size(); i++)
            System.out.printf("%.2f" + ((arrD.size()-1 == i)?"\n":", "), arrD.get(i));
        
        double dub = (Math.random()*range)+1;
        System.out.printf("The number %.2f" + (arrD.search(dub) == -1?
                    " is not contained" :
                    (" is at index " + arrD.search(dub)))
                + " within the Array\n\n", dub);
        
        ////////////////////////////////////////////////////////////////////
        
        //String Array
        P2<String> arrS = new P2<>();
        for(int i = 0; i < (int)(Math.random()*range) + 1; i++)
        {
            String str = "";
            for(int j = 0; j < (int)(Math.random()*2)+1; j++)
                str += (char)(((int)(Math.random()*26))+'a');
            arrS.add(str);
        }
        
        arrS.sort();
        System.out.print("The Array Is: ");
        for(int i = 0; i < arrS.size(); i++)
            System.out.print("\"" + arrS.get(i) + "\"" + ((arrS.size()-1 == i)?"\n":", "));
        
        String str = "";
        for(int j = 0; j < (int)(Math.random()*2)+1; j++)
                str += (char)(((int)(Math.random()*26))+'a');
        
        System.out.printf("The word \"" + str + (arrS.search(str) == -1?
                    "\" is not contained" :
                    (" is at index " + arrS.search(str)))
                + " within the Array\n\n");
    }
    
    public P2(){super();}
    
    public void sort() //Insertion Sort
    {
        for(int i = 0; i < size(); i++)
        {
            E obj = get(i);
            for(int j = i; j > 0 && obj.compareTo(get(j-1)) > 0; j--)
            {
                set(j, get(j-1));
                set(j-1, obj);
            }
        }
    }
    
    public int search(E obj) //Binary Search
    {
        int begin = 0;
        int end = size()-1;
        while(begin <= end)
        {
            int mid = begin + (end-begin)/2;
            int comparison = get(mid).compareTo(obj);
            if(comparison == 0)
                return mid;
            else if(comparison > 0)
                begin = mid + 1;
            else if(comparison < 0)
                end = mid - 1;
        }
        return -1;
    }
}
