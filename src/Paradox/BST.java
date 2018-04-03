//Ishaan Bharal(ixb170930)
package Paradox;

public class BST<E extends Combinable<E>>
{
    private Node<E> root;
    
    public BST(){root = null;}
    public BST(E item){root = new Node(item);}
    
    public Node<E> getRoot(){return root;}
    
    public void insert(E element)
    {
	if(root == null)
	{
	    root = new Node(element);
	    return;
	}
	insert(element, root);
    }
    private void insert(E element, Node<E> node)
    {
	if(element.compareTo(node.item) == 0)
	    node.item.combineWith(element);
	else if(element.compareTo(node.item) > 0)
	{
	    if(node.getLeft() == null)
		node.setLeft(new Node(element));
	    else
		insert(element, node.getLeft());
	}
	else
	{
	    if(node.getRight() == null)
		node.setRight(new Node(element));
	    else
		insert(element, node.getRight());
	}
    }
    
    public Node<E> search(E element){return search(element, root);}
    private Node<E> search(E element, Node<E> node)
    {
	if(node == null)
	    return null;
	
	if(element.compareTo(node.item) > 0)
	    return search(element, node.getLeft());
	else if(element.compareTo(node.item) < 0)
	    return search(element, node.getRight());
	return node;
    }
    
    public Node<E> delete(E element)
    {
        return delete(element, root, null);
    }
    private Node<E> delete(E element, Node<E> node, Node<E> parent)
    {
	//Element isn't in BST
	if(node == null)
	    return null;
	
	//Find Element to delete
	if(element.compareTo(node.item) > 0)
	    return delete(element, node.getLeft(), node);
	else if(element.compareTo(node.item) < 0)
	    return delete(element, node.getRight(), node);
	
	//Destroy element
	return destroy(node, parent);
    }
    private Node<E> destroy(Node<E> node, Node<E> parent)
    {
	if(node == root)
	{
	    root = null;
	    return node;
	}
	
	//Case of No Children
	if(node.getLeft() == null && node.getRight() == null)
	{
	    if(parent.getLeft() == node)
		parent.setLeft(null);
	    else
		parent.setRight(null);
	}
	//Case of Only Left Child
	else if(node.getLeft() != null && node.getRight() == null)
	{
	    if(parent.getLeft() == node)
		parent.setLeft(node.getLeft());
	    else
		parent.setRight(node.getLeft());
	}
	//Case of Only Right Child
	else if(node.getLeft() == null && node.getRight() != null)
	{
	    if(parent.getLeft() == node)
		parent.setLeft(node.getRight());
	    else
		parent.setRight(node.getRight());
	}
	//Case of 2 Children
	else
	{
	    Node<E> rightMost = node.getLeft();
	    Node<E> rightParent = node;
	    while(rightMost.getRight() != null)
	    {
		rightParent = rightMost;
		rightMost = rightMost.getRight();
	    }
	    node.item = rightMost.item;
	    
	    if(rightParent.getRight() == rightMost)
		rightParent.setRight(rightMost.getLeft());
	    else
		rightParent.setLeft(rightMost.getLeft());
	}
	
	return node;
    }
    
    public String traverse(String order, Executable e) //Helper Function
    {
	switch(order)
	{
	    case "Prefix":
		return traversePrefix(root,e);
	    case "Infix":
		return traverseInfix(root,e);
	    case "Postfix":
		return traversePostfix(root,e);
	}
	return null;
    }
    private String traversePrefix(Node node, Executable e)
    {
	if(node == null)
	    return "";
	return e.execute(node.item) + traversePrefix(node.getLeft(), e) + traversePrefix(node.getRight(), e);
    }
    
    private String traverseInfix(Node node, Executable e)
    {
	if(node == null)
	    return "";
	return traverseInfix(node.getLeft(), e) + e.execute(node.item) + traverseInfix(node.getRight(), e);
    }
    
    private String traversePostfix(Node node, Executable e)
    {
	if(node == null)
	    return "";
	return traversePostfix(node.getLeft(), e) + traversePostfix(node.getRight(), e) + e.execute(node.item);
    }
}