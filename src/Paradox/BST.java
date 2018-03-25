//Ishaan Bharal(ixb170930)
package Paradox;

import java.lang.reflect.Method;

public class BST<E>
{
    Node<E> root;
    
    public BST()
    {
	root = null;
    }
    public BST(E item)
    {
	root = new Node(item);
    }
    
    private E getRoot()
    {
	return root.item;
    }
    public E get(int index)
    {
	return getRoot(); //TO DO
    }
    
    public void add(E item)
    {
	return; //TO DO
    }
    
    public void insert()
    {
	return; //TO DO
    }
    
    public int search()
    {
	return 0; //TO DO 
    }
    
    public E delete()
    {
	return null; //TO DO
    }
    
    public String traverse(String order) //Helper Function
    {
	switch(order)
	{
	    case "Prefix":
		return traversePrefix(root);
	    case "Infix":
		return traverseInfix(root);
	    case "Postfix":
		return traversePostfix(root);
	}
	return null;
    }
    private String traversePrefix(Node node)
    {
	return ""; //TO DO
    }
    
    private String traverseInfix(Node node)
    {
	return ""; //TO DO
    }
    
    private String traversePostfix(Node node)
    {
	return ""; //TO DO
    }
}