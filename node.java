/* Ashwin Babu 1001392860
Assignment-1 */
import java.util.*;
import java.io.*;

public class node
{
	String name;
	int depth_of_node;
	int dist_cost;
	node parent;
	ArrayList<node>children = new ArrayList<node>();

	public node(String name,int depth_of_node, int dist_cost,node parent,ArrayList<node>children) 
	{
		this.name=name;
		this.depth_of_node=depth_of_node;
		this.dist_cost=dist_cost;
		this.parent=parent;
		this.children=children;
	}

	public node(String name,int depth_of_node, int dist_cost,node parent)
	{
		this.name=name;
		this.depth_of_node=depth_of_node;
		this.dist_cost=dist_cost;
		this.parent=parent;
	}

	String get_name()
	{
		return this.name;
	}

	int get_depth()
	{
		return this.depth_of_node;
	}

	int get_cost()
	{
		return this.dist_cost;
	}

	node get_Parent()
	{
		return this.parent;
	}

	ArrayList<node> get_children()
	{
		return this.children;
	}

	void set_children(ArrayList<node>children)
	{
		 this.children=children;

	}
}

