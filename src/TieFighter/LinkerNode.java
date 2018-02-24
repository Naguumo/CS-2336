package TieFighter;

public class LinkerNode<E>
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

    public LinkerNode<E> getNext(){return next;}
    public void setNext(LinkerNode<E> next){this.next = next;}
    public LinkerNode<E> getPrev(){return prev;}
    public void setPrev(LinkerNode<E> prev){this.prev = prev;}
}
