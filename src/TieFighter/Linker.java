//Ishaan Bharal (ixb170930)

package TieFighter;

public class Linker<E>
{
    private LinkerNode<E> first;
    private LinkerNode<E> last;
    private int size = 0;
    
    //Constructor
    public Linker(E[] stuff){for (E thing : stuff)this.addLast(thing);}
    public Linker(){}
    
    ///////////////////////////////////////////////////////////////////////////
    
    public E get(int index){return getNode(index).item;}
    private LinkerNode<E> getNode(int index)
    {
        if (!(index >= 0 && index < size))
            throw new IndexOutOfBoundsException("");
        if (index < (size >> 1)) 
        {
            LinkerNode<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        }
        else 
        {
            LinkerNode<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
    
    public int indexOf(E e)
    {
        int index = 0;
        for(LinkerNode<E> x = first; x != null; x = x.next)
        {
            if(e == null ? x.item == null : e.equals(x.item))
                return index;
            index++;
        }
        return -1;
    }
    
    public void add(int index, E e)
    {
        if(index == 0)
            addFirst(e);
        else if(index >= size)
            addLast(e);
        else
        {
            LinkerNode<E> prev = first;
            for(int i = 1; i < index; i++)
                prev = prev.next;
            LinkerNode<E> next = prev.next;
            LinkerNode<E> newNode = new LinkerNode<>(e, prev, prev.next);
            prev.next = newNode;
            next.prev = newNode;
            size++;
        }
    }
    
    public E remove(int index)
    {
        if(index < 0 || index >= size)
            return null;
        else if(index == 0)
            return removeFirst();
        else if(index == size - 1)
            return removeLast();
        else
        {
            LinkerNode<E> prev = first;
            for(int i = 1; i < index; i++)
                prev = prev.next;
            LinkerNode<E> curr = prev.next;
            LinkerNode<E> next = curr.next;
            prev.next = next;
            next.prev = prev;
            size--;
            return curr.item;
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    
    public void sort(LinkerNode<E> node, boolean asc)
    {
        System.out.println(node.item);
    }
    
    public void sort(boolean area, boolean asc)
    {
        //No Sorting If Not Actually A List
        if(size <= 1 || first == null || last == null)
            return;
        
        //Set Proper Flags
        for(int i = 0; i < size; i++)
        {
            if(!(get(i) instanceof Payload))
                return;
            Payload<E> p = (Payload)get(i);
            p.setFlag(area);
        }
        
        sort(getNode((size-1)/2), asc);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    
    public int search(String txt, boolean area)
    {
        double num = area ? Double.parseDouble(txt) : 0;
        for(int i = 0; i < size; i++)
        {
            if(!(get(i) instanceof Payload))
                continue;
            Payload p = (Payload)get(i);
            if(area ? Math.round(p.getArea()*100) == num*100 : p.getPilot().equals(txt))
                return i;
        }
        return -1;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    
    public int size(){return size;}
    
    @Override
    public String toString(){return first.item.toString() + toString(first.next) + last.item.toString();}
    private String toString(LinkerNode node){return node == last ? "" : node.item.toString() + toString(node.next);}//Recursive Implementation
    
    ///////////////////////////////////////////////////////////////////////////
    
    //Methods For First Pointer
    public E getFirst(){return first.item;}
    
    public void addFirst(E item)
    {
        LinkerNode<E> prevFirst = first;
        LinkerNode<E> newFirst = new LinkerNode<>(item, null, prevFirst);
        first = newFirst;
        if(prevFirst == null)
            last = newFirst;
        else
            prevFirst.prev = newFirst;
        size++;
    }
    
    public E removeFirst()
    {
        if(size == 0)
            return null;
        LinkerNode<E> f = first;
        LinkerNode<E> next = f.next;
        next.prev = null;
        first = next;
        size--;
        if(first == null)
            last = null;
        return f.item;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    
    //Methods For Last Pointer
    public E getLast(){return last.item;}
    
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
    
    public E removeLast()
    {
        if(size == 0)
            return null;
        LinkerNode<E> l = last;
        LinkerNode<E> prev = l.prev;
        prev.next = null;
        last = prev;
        size--;
        if(last == null)
            first = null;
        return l.item;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    
    //Node Class
    private class LinkerNode<E>
    {
        E item;
        LinkerNode<E> next;
        LinkerNode<E> prev;

        public LinkerNode(E obj, LinkerNode prev, LinkerNode next)
        {
            this.item = obj;
            this.prev = prev;
            this.next = next;
        }
        
        //Redundant and Unnecessary
        public LinkerNode<E> getNext(){return next;}
        public void setNext(LinkerNode<E> next){this.next = next;}
        public LinkerNode<E> getPrev(){return prev;}
        public void setPrev(LinkerNode<E> prev){this.prev = prev;}
    }
}