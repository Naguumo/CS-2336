package HW5;

import java.util.LinkedList;

public class PStack<E>
{
    private LinkedList<E> list = new LinkedList<>();
    
    public PStack(){}
    
    public void push(E e)
    {
        list.addLast(e);
    }
    
    public E pop()
    {
        return list.removeLast();
    }
    
    public E peek()
    {
        return list.getLast();
    }
    
    public int getSize()
    {
        return list.size();
    }
}
