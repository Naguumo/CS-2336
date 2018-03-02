//Ishaan Bharal (ixb170930)

package TieFighter;

public class Linker<E>
{
    private LinkerNode<E> first, last;
    private int size = 0;
    
    //Constructors
    private Linker(LinkerNode<E> node){first = last = node;}//Useless but Required
    public Linker(){}
    
    ///////////////////////////////////////////////////////////////////////////
    
    //Getter Method
    public E get(int index){return getNode(index).item;}
    
    //Getter for Nodes using index
    private LinkerNode<E> getNode(int index)
    {
        if (!(index >= 0 && index < size))
            throw new IndexOutOfBoundsException("");
         
        LinkerNode<E> node = first;
        if (index < (size >> 1))
            for (int i = 0; i < index; i++)
                node = node.next;
        else 
        {
            node = last;
            for (int i = size - 1; i > index; i--)
                node = node.prev;
        }
        return node;
    }
    
    public void add(E e){add(size, e);}
    public final void add(int index, E item)
    {
        if(index == 0)
        {
            LinkerNode<E> prevFirst = first;
            LinkerNode<E> newFirst = new LinkerNode<>(item, null, prevFirst);
            first = newFirst;
            if(prevFirst == null)
                last = newFirst;
            else
                prevFirst.prev = newFirst;
        }
        else if(index >= size)
        {
            LinkerNode prevLast = last;
            LinkerNode newLast = new LinkerNode(item, prevLast, null);
            last = newLast;
            if(prevLast == null)
                first = newLast;
            else
                prevLast.next = newLast;
        }
        else
        {
            LinkerNode<E> prev = first;
            for(int i = 1; i < index; i++)
                prev = prev.next;
            LinkerNode<E> next = prev.next;
            LinkerNode<E> newNode = new LinkerNode<>(item, prev, prev.next);
            prev.next = next.prev = newNode;
        }
        size++;
    }
    
    public E remove(int index)
    {
        if(index < 0 || index >= size)
            return null;
        
        size--;
        if(index == 0)
        {
            E thing = first.item;
            if(first.next != null)
                first.next.prev = null;
            first = first.next;
            last = first == null ? null : last;
            return thing;
        }
        else if(index == size - 1)
        {
            E thing = last.item;
            if(last.prev != null)
                last.prev.next = null;
            last = last.prev;
            first = last == null ? null : first;
            return thing;
        }
        
        LinkerNode<E> prev = first;
        for(int i = 1; i < index; i++)
            prev = prev.next;
        E thing = prev.next.item;
        LinkerNode<E> next = prev.next.next;
        prev.next = next;
        next.prev = prev;
        return thing;
    }
    ///////////////////////////////////////////////////////////////////////////
    
    //Merge Sort
    //Split Portion
    private void sort(Linker<E> arr)
    {
        if(arr.size <= 1)
            return;
        
        int i = 0; //Iterator
        
        Linker<E> aList = new Linker<>(); //First Half
        while(i < arr.size/2)
            aList.add(arr.get(i++));

        Linker<E> bList = new Linker<>(); //Second Half
        while(i < arr.size)
            bList.add(arr.get(i++));
        
        //Sort Sub Lists
        sort(aList);
        sort(bList);
        
        //Combine 2 Lists
        sort((Linker<Payload>)aList, (Linker<Payload>)bList, (Linker<Payload>)arr);
    }
    
    //Merge Portion
    private void sort(Linker<Payload> a, Linker<Payload> b, Linker<Payload> arr)
    {
        while(arr.size != 0)
            arr.remove(0);
        
        int c1=0,c2=0,c3=0; //Counters for Each List
        while(c1 < a.size && c2 < b.size)
        {
            if(a.get(c1).compareTo(b.get(c2)) < 0)
                arr.add(c3++, a.get(c1++)); 
            else
                arr.add(c3++, b.get(c2++));
        }
        
        while(c1 < a.size)
            arr.add(c3++, a.get(c1++));
        while(c2 < b.size)
            arr.add(c3++, b.get(c2++));
    }
    
    //Helper Functions
    public void sort(){sort(true,true);}
    public void sort(boolean area, boolean asc)
    {
        if(size <= 1 || first == null || last == null || !(first.item instanceof Payload))
            return; //No Sorting If Not Actually A List
        
        //Set Proper Flags
        Payload.setFlag(area);
        Payload.setFlag2(asc);
        
        sort((Linker<E>)this);//Sort Recursively
    }
    
    ///////////////////////////////////////////////////////////////////////////
    
    //Iterated Search
    public int search(String txt, boolean area)
    {
        double num = area ? Double.parseDouble(txt) : 0;
        for(int i = 0; i < size; i++)
        {
            if(!(get(i) instanceof Payload))
                continue;
            if(area ? Math.round(((Payload)get(i)).getArea()*100) == num*100 : ((Payload)get(i)).getPilot().equals(txt))
                return i;
        }
        return -1;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    
    @Override
    public String toString(){return toString(first);}
    private String toString(LinkerNode node){return node == null ? "" : node.item.toString() + toString(node.next);}//Recursive Implementation
    
    //Getter Methods
    public E getFirst(){return first.item;}
    public E getLast(){return last.item;}
    public int size(){return size;}
    
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