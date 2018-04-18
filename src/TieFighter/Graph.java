package TieFighter;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph
{
    private int size;
    private LinkedList<Pathway> graph;
    
    public Graph()
    {
	graph = new LinkedList<>();
	size = 0;
    }
    
    public void insertPoint(int point)
    {
	Pathway temp = new Pathway(point);
	if(graph.contains(temp))
	    return;
	
	graph.add(temp);
	size++;
    }
    
    public void insertConnection(int point, int endpoint, int weight)
    {
	Pathway temp = new Pathway(point);
	if(graph.contains(temp))
	{
	    temp = graph.get(graph.indexOf(temp));
	    boolean addable = true;
	    for(Integer i : temp.connections)
		if(i.equals(endpoint))
		    addable = false;
	    if(addable)
	    {
		temp.connections.add(endpoint);
		temp.weights.add(weight);
	    }
	}
    }
    
    public int getIndex(int point){return graph.contains(new Pathway(point)) ? graph.indexOf(new Pathway(point)) : -1;}
    private Pathway getPath(int index){return graph.get(index);}
    public int getPoint(int index){return getPath(index).origin;}
    public LinkedList<Integer> getNeighbors(int index){return getPath(index).connections;}
    public LinkedList<Integer> getWeights(int index){return getPath(index).weights;}
    
    public int size(){return size;}
    
    public void dfs(int point)
    {
	Pathway p = new Pathway(point);
	if(graph.contains(p))
	{
	    p = graph.get(getIndex(point));
	    dfs(p, new ArrayList<>());
	    System.out.println("");
	}
	else
	    System.out.println("INVALID");
    }
    private void dfs(Pathway point, ArrayList<Integer> visited)
    {
	System.out.print(point.origin + " ");
	
	visited.add(point.origin);
	for(int i = 0; i < point.connections.size(); i++)
	{
	    int temp = point.connections.get(i);
	    if(!visited.contains(temp) && graph.contains(new Pathway(temp)))
		dfs(graph.get(getIndex(temp)), visited);
	}
    }
    
    private class Pathway
    {
	public int origin;
	public LinkedList<Integer> connections;
	public LinkedList<Integer> weights;
	
	public Pathway(int origin)
	{
	    this.origin = origin;
	    connections = new LinkedList<>();
	    weights = new LinkedList<>();
	}
	
	@Override
	public boolean equals(Object o)
	{
	    if(!(o instanceof Pathway))
		return false;
	    Pathway item = (Pathway)o;
	    return item.origin == this.origin;
	}
    }
}
