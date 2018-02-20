//Ishaan Bharal (ixb170930)

package TieFighter;

import java.util.LinkedList;

public class Linker<E> extends LinkedList<E>
{
    private LinkerNode<E> head;
    private LinkerNode<E> tail;
    
    public Linker(LinkerNode h, LinkerNode t)
    {
        super();
        head = h;
        tail = t;
    }
    
    public Linker()
    {
    }

    private static class LinkerNode<E>
    {
        E obj;
        LinkerNode<E> next;
        LinkerNode<E> prev;

        private LinkerNode(E payload)
        {
            obj = payload;
        }

        public LinkerNode<E> getNext()
        {
            return next;
        }

        public void setNext(LinkerNode<E> next)
        {
            this.next = next;
        }

        public LinkerNode<E> getPrev()
        {
            return prev;
        }

        public void setPrev(LinkerNode<E> prev)
        {
            this.prev = prev;
        }
    }
}