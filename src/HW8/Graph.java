package HW8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph
{
    public static void main(String[] args) throws FileNotFoundException
    {
	System.out.println("Graph #1");
	Graph graph = createGraph("graph1.txt");
	graph.dfs(1);
	System.out.println("Empty After: " + graph.isEmpty());
	System.out.print("10: " + graph.isConnected(10) + "\n");
	System.out.print("8: " + graph.isConnected(8) + "\n");
	
	System.out.println("");
	
	System.out.println("Graph #2");
	Graph graph2 = new Graph();
	System.out.println("Empty Before: " + graph2.isEmpty());
	graph2.dfs(1);
	graph2 = createGraph("graph2.txt");
	System.out.println("Empty After: " + graph2.isEmpty());
	graph2.dfs(1);
	System.out.print("8: " + graph2.isConnected(8) + "\n");
	System.out.print("4: " + graph.isConnected(4) + "\n");
    }
    
    private ArrayList<Integer>[] adjacent;
    private int size;
    private final int maxSize;
    
    public Graph()
    {
	adjacent = new ArrayList[11];
	for(int i = 0; i < adjacent.length; i++)
	    adjacent[i] = new ArrayList<>();
	maxSize = 11;
    }
    public Graph(int max)
    {
	adjacent = new ArrayList[max+1];
	for(int i = 0; i < adjacent.length; i++)
	    adjacent[i] = new ArrayList<>();
	maxSize = max+1;
    }
    
    private void addEdge(int origin, int end)
    {
	adjacent[origin].add(end);
	size++;
    }
    
    public static Graph createGraph(String str) throws FileNotFoundException
    {
	Scanner in = new Scanner(new File(str));
	Graph graph = new Graph(in.nextInt());
	
	while(in.hasNextLine())
	{
	    String line = in.nextLine();
	    if(line.isEmpty())
		continue;
	    
	    //First Number is point
	    int point = Integer.parseInt(line.substring(0, line.indexOf(" ")));
	    line = line.replaceFirst("\\d ", "");
	    
	    //Rest of Numbers are connections
	    String[] parts = line.split(" ");
	    for (String part : parts)
		graph.addEdge(point, Integer.parseInt(part));
	}
	
	in.close();
	return graph;
    }
    
    public boolean isConnected(int root)
    {
	boolean[] visited = new boolean[maxSize];
	dfs(root, visited);
	System.out.println("");
	for(int i = 1; i < visited.length; i++)
	{
	    if(visited[i] == false)
		return false;
	}
	return true;
    }

    public void dfs(int root)
    {
	boolean[] visited = new boolean[maxSize];
	dfs(root, visited);
	System.out.println("");
    }
    private void dfs(int point, boolean[] visited)
    {
	if(maxSize <= point)
	{
	    System.out.print("ERROR");
	    return;
	}
	visited[point] = true;
	System.out.print(point + " ");
	for(int i = 0; i < adjacent[point].size(); i++)
	{
	    int temp = adjacent[point].get(i);
	    if(visited[temp] == false)
		dfs(temp, visited);
	}
    }
    
    public boolean isEmpty(){return size == 0;}
}
