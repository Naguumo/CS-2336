//Ishaan Bharal (ixb170930)

package TieFighter;

import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Linker<E> 
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
{
    private LinkerNode<E> first;
    private LinkerNode<E> last;
    private int size = 0;
    
    public Linker(Collection<? extends E> items)
    {
        this();
        Object[] stuff = items.toArray();
        for (int i = 0; i < stuff.length; i++)
        {
            addLast((E)stuff[i]);
        }
    }
    
    public Linker(){super();}
    
    @Override
    public String toString()
    {
        //Needs Implementation//
        return super.toString();
    }
    
    public void sort()
    {
        //Needs Implementation//
    }
    
    @Override
    public E getFirst(){return first.item;}
    @Override
    public void addFirst(E item)
    {
        LinkerNode prevFirst = first;
        LinkerNode newFirst = new LinkerNode(item, null, prevFirst);
        first = newFirst;
        if(prevFirst == null)
            last = newFirst;
        else
            prevFirst.prev = newFirst;
        size++;
    }
    @Override
    public E getLast(){return last.item;}
    @Override
    public void addLast(E item)
    {
        LinkerNode prevLast = last;
        LinkerNode newLast = new LinkerNode(item, prevLast, null);
        last = newLast;
        if(prevLast == null)
            first = newLast;
        else
            prevLast.next = newLast;
        size++;
    }
    
    @Override
    public int size()
    {
        return size;
    }
    
    @Override
    public ListIterator<E> listIterator(int index)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean offerFirst(E e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean offerLast(E e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E removeFirst()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E removeLast()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E pollFirst()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E pollLast()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E peekFirst()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E peekLast()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeFirstOccurrence(Object o)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeLastOccurrence(Object o)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean offer(E e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E remove()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E poll()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E element()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E peek()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void push(E e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E pop()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<E> descendingIterator()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public E get(int index) 
    {
        if (!(index >= 0 && index < size))
            throw new IndexOutOfBoundsException("");
        if (index < (size >> 1)) {
            LinkerNode<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x.item;
        } else {
            LinkerNode<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x.item;
        }
    }
}