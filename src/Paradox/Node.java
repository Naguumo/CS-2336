//Ishaan Bharal(ixb170930)
package Paradox;

public class Node<E>
{
    E item;
    Node<E> left, right;

    public Node(E item)
    {
	this.item = item;
	left = right = null;
    }
    public Node(E item, Node<E> left, Node<E> right)
    {
	this.item = item;
	this.left = left;
	this.right = right;
    }

    public Node<E> getLeft()
    {
	return left;
    }

    public void setLeft(Node<E> left)
    {
	this.left = left;
    }

    public Node<E> getRight()
    {
	return right;
    }

    public void setRight(Node<E> right)
    {
	this.right = right;
    }
}
