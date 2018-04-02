package HW6;

public class BST<E extends Comparable<E>>
{
    public static void main(String[] args)
    {
	System.out.println("Array 1:");
	BST<Integer> nums = new BST<>();
	nums.insert(50);
	nums.insert(15);
	nums.insert(35);
	nums.insert(16);
	nums.insert(82);
	nums.insert(150);
	nums.insert(1);
	nums.insert(3);
	nums.insert(66);
	
	nums.traverse();
	
	System.out.println("\nArray 2:");
	BST<Integer> other = nums.clone();
	
	other.traverse();
	System.out.println(nums.equals(other));
	
	System.out.println("\nArray 3:");
	BST<Integer> other2 = new BST<>();
	other2.insert(50);
	other2.insert(6);
	other2.insert(9);
	other2.insert(12);
	other2.insert(63);
	
	other2.traverse();
	System.out.println(nums.equals(other2));
    }
    
    private Node root;
    
    public BST(){}
    public BST(E item)
    {
	root = new Node(item);
    }
    
    public boolean search(E element)
    {
	Node<E> curr = root;
	while(curr != null)
	{
	    if(element.compareTo(curr.item) < 0)
		curr = curr.left;
	    else if(element.compareTo(curr.item) > 0)
		curr = curr.right;
	    else
		return true;
	}
	return false;
    }
    
    public boolean insert(E element)
    {
	if(root == null)
	    root = new Node(element);
	else
	{
	    Node<E> parent = null;
	    Node<E> curr = root;
	    
	    while(curr != null)
	    {
		if(element.compareTo(curr.item) < 0)
		{
		    parent = curr;
		    curr = curr.left;
		}
		else if(element.compareTo(curr.item) > 0)
		{
		    parent = curr;
		    curr = curr.right;
		}
		else
		    return false;
	    }
	    
	    if(element.compareTo(parent.item) < 0)
		parent.left = new Node(element);
	    else
		parent.right = new Node(element);
	}
	return true;
    }
    
    public boolean delete(E element)
    {
	Node<E> parent = null;
	Node<E> curr = root;
	while(curr != null)
	{
	    if(element.compareTo(curr.item) < 0)
	    {
		parent = curr;
		curr = curr.left;
	    }
	    else if(element.compareTo(curr.item) > 0)
	    {
		parent = curr;
		curr = curr.right;
	    }
	    else
		break;
	}
	
	if(curr == null)
	    return false;
	
	if(curr.left == null)
	{
	    if(parent == null)
		root = curr.right;
	    else
	    {
		if(element.compareTo(parent.item)< 0)
		    parent.left = curr.right;
		else
		    parent.right = curr.right;
	    }
	}
	else
	{
	    Node<E> rightMostParent = curr;
	    Node<E> rightMost = curr.left;
	    
	    while(rightMost.right != null)
	    {
		rightMostParent = rightMost;
		rightMost = rightMost.right;
	    }
	    
	    if(rightMostParent.right == rightMost)
		rightMostParent.right = rightMost.left;
	    else
		rightMostParent.left = rightMost.left;
	}
	return true;
    }
    
    public void traverse()
    {
	traverse(root);
	System.out.println("");
    }
    private void traverse(Node<E> element)
    {
	if(element == null)
	    return;
	System.out.print(element.item + " ");
	traverse(element.left);
	traverse(element.right);
    }
    
    @Override
    public BST<E> clone()
    {
	BST<E> out = new BST<>();
	this.clone(out, root);
	return out;
    }
    private void clone(BST<E> out, Node<E> element)
    {
	if(element == null)
	    return;
	out.insert(element.item);
	this.clone(out, element.left);
	this.clone(out, element.right);
    }
    
    public boolean equals(BST<E> other)
    {
	return equals(other.root, this.root);
    }
    private boolean equals(Node<E> other, Node<E> element)
    {
	if(element == null)
	{
	    if(other == null)
	        return true;
	    else
		return false;
	}
	return other.item.equals(element.item) && equals(other.left, element.left) && equals(other.right, element.right);
    }
}

class Node<E>
{
    E item;
    Node<E> left, right;
    
    public Node(E item)
    {
	this.item = item;
    }
    public Node(E item, Node<E> left, Node<E> right)
    {
	this.item = item;
	this.left = left;
	this.right = right;
    }
}