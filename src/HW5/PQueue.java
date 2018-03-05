package HW5;

import java.util.LinkedList;

public class PQueue<E>
{
    private LinkedList<E> list = new LinkedList<>();
    
    public PQueue(){}
    
    public void offer(E e)
    {
        list.addLast(e);
    }
    
    public E remove()
    {
        return list.removeFirst();
    }
    
    public int getSize()
    {
        return list.size();
    }
}
