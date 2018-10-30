/* Ashwin Babu 100139260
Assignment 1 */

import java.util.*;
import java.io.*;


 public class find_route
 {
	private static String Parse_string; 

 	public static void Adding_children(node origin_city,ArrayList<file> route_found) // void method adding the child nodes to the parent nodes
 	{	
 		ArrayList<node> children=new ArrayList<node>(); // ARRAYLIST TO ADD THE CHILDREN OF THE NODE

 		for(file obj:route_found) // traverse through the route_found
 		{	
 			if(obj.get_first_city().equals(origin_city.get_name())) // if city 1 is source_city then add all the cities and distance to the node
 			{
 				node child=new node(obj.get_second_city(),origin_city.get_depth()+1,origin_city.get_cost()+obj.getdistance(),origin_city);
 				
 				children.add(child);
 			}
 			else if(obj.get_second_city().equals(origin_city.get_name())) // if city 2 is source_city then add all the cities and distance to the node
 			{
 				node child=new node(obj.get_first_city(),origin_city.get_depth()+1,origin_city.get_cost()+obj.getdistance(),origin_city);
 				
 				children.add(child);
 			}
 		}
 		origin_city.set_children(children);
	}

	public static int get_distance(String source_city,String destination_city,ArrayList<file>route_found) //getting the distance between the two cities on the route
    {	

    	int dist=0;
    	
    	for(int i=0;i<route_found.size();i++)
    	{
    		if(source_city.equals(route_found.get(i).first_city) || source_city.equals(route_found.get(i).second_city) ) //if the source_city is saved as the first city in the file or second city in the file
    		{
    			if(destination_city.equals(route_found.get(i).second_city) || destination_city.equals(route_found.get(i).first_city) )	
    			{
    				dist=route_found.get(i).distance;
    			}
    		}
    		
    	}
    	return dist;
	}




	public static void print_route(node destination_city,ArrayList<file> route_found) //printing the path
    {	
    	file distance=new file();
    	
    	ArrayList<String>parent_node= new ArrayList<String>();
    	
    	System.out.println("distance: "+destination_city.get_cost()+ "km");
    	
    	System.out.println("route:");
		
		while(destination_city.get_Parent()!=null) // print the destination_city path
		{	
			
			System.out.println(destination_city.get_Parent().get_name()+" to "+ destination_city.get_name()+":"+ get_distance(destination_city.get_name(),destination_city.get_Parent().get_name(),route_found)+ "km");
			
			parent_node.add(destination_city.get_name());
			
			parent_node.add(destination_city.get_Parent().get_name());

			destination_city=destination_city.get_Parent();
		}   	
    }
 
 	public static void main(String[] args)
	{	

		String file_name= args[0]; // to get the route_found

		String source_city=args[1]; // to get the source_city city input

		String destination_city=args[2]; // to get the destination_city city input

		String route_array[]; //array for splitting

		ArrayList<String> visited_nodes=new ArrayList<String>();

		int distance;

		ArrayList<file> route_found=new ArrayList<file>();

		node origin_city=new node(source_city,0,0,null);

		try
		{
			BufferedReader br = new BufferedReader(new FileReader(file_name));
		
			while((Parse_string = br.readLine()) != null) 
			{
                if(Parse_string.equals("END OF INPUT"))
                    break;

                route_array= Parse_string.split(" ");
                
                file new_info= new file();

                new_info.first_city=route_array[0]; //saving the txt file to a class with the route_found as objects.

                new_info.second_city=route_array[1];

                new_info.distance=Integer.parseInt(route_array[2]);

                route_found.add(new_info); // save the route_found as an object of file in java

			}
            br.close();
		}

		catch(FileNotFoundException excep) 
           	{
            	System.out.println("Could not find the file");                
        	}
        
        catch(IOException excep) 
        	{
            	System.out.println("Cannot read the file");                  
        	}


       PriorityQueue<node> fringe=new PriorityQueue<node>(1000, new Comparator<node>() //Priority queue to perform UCS
       {
            public int compare(node node1, node node2)
            {
                
                if (node1.get_cost()>node2.get_cost()) 
                	{
                		return 1;
                	}
                if (node1.get_cost()==node2.get_cost())
	                {
	                	return 0;
	                }

                return -1;
            }
        }
        );			
            

	   fringe.add(origin_city); //adding the node to the fringe

	   while(!fringe.isEmpty())
	   {
			node temp=fringe.poll(); // poll is a function to get the first item from the node 

	   		if(temp.get_name().equals(destination_city))
	   		{
	   			print_route(temp,route_found);
	   			
	   			break;
	   		}

			if(!visited_nodes.contains(temp.get_name()))  // if the node was not visited then add it to the fringe  
			
			{	
				visited_nodes.add(temp.get_name());  

				Adding_children(temp,route_found);
			
				for(int i=0;i<temp.get_children().size();i++)
					{
						node child= temp.get_children().get(i);
			
						fringe.add(child); 
					}
		    }
	   
	   		if(fringe.isEmpty())
	   		{
	   			System.out.println("distance= infinity");// if a route does not exist
	   	
	   			System.out.println("route:"+'\n'+"none");
	   		}
		}

	}
}

 