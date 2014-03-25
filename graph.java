/*
Jared Kramer

This is a class for a graph.
Each graph has the following fields:

int[][] matrix
	This is the adjacency matrix for this graph.
	The axes of the matrix are the vertices of the graph,
	and the values in the matrix are either 0 is the edge does not exist
	or 1 is the edge does exist.
	
String name
	This is the name as given by the first line of the input file
	For reference purposes only.
	
int edgeCount
	This is the number of edges in the graph.
	It is used in creating the adjacency matrix.
	
int nodeCount
	This is the number of nodes in the graph.
	It is used in creating the adjacency matrix.
	It is also used in creating the vertex array.
	
int[] V
	This is the array of vertices for the graph.
*/

import java.util.Arrays;
public class graph{

	public int[][] matrix;
	public String name;
	public int nodeCount;
	public int edgeCount;
	public int[] V;
	
	
	/*
	Empty constructor for a graph.
	This is used for rebuilding and reassigning the vertex arrays
	in the recursive search method in the Match class.
	*/
	public graph(){
		
	}
	
	
	/*
	This is the main constructor for the graph.
	It takes a string array as an argument.
	Each value in the string array is a line from the input file.
	The first three lines are the name, nodecount and edgecount.
	
	Next, the proper size matrix is created and populated with 0s
	via the makeMatrix method.
	
	Next, the array is sliced and sent to fillMatrix, 
	which changes 0s to 1s if the edge is present in the graph.
	
	Lastly, the vertex array is populated with a very simple vList method,
	which adds values starting at 1 and ending at nodeCount..
	*/
	public graph(String[] data){
		try{
			this.name = data[1];
			this.nodeCount = Integer.parseInt(data[2]);
			this.edgeCount = Integer.parseInt(data[3]);
			this.matrix = this.makeMatrix(this.nodeCount);
			
			String[] edges = Arrays.copyOfRange(data, 4, data.length);
			this.matrix = this.fillMatrix(this.matrix, edges);
			this.V = vList(this.nodeCount);
		}
		catch(NumberFormatException e){
			System.out.println(e);
		}
	}
	
	
	/*
	This method creates a 2d array that represents the adjacency matrix for the graph.
	Here, the matrix is constructed and populated with zeros.
	*/
	private int[][] makeMatrix(int n){
		int[][] matrix = new int[n+1][n+1];
		for (int row = 1; row < n+1; row++){
			for (int col = 1; col < n+1; col++){
				matrix[row][col] = 0;
			}
		}
		return matrix;
	}
	
	
	/*
	This is the method for filling in the adjacency array.
	This method parses the string array of edges and uses the resulting int values
	as indices in the 2d array, changing the 0s to 1s.
	A 1 represents that an edge is present inthe graph.
	*/
	private int[][] fillMatrix(int[][] matrix, String[] edges){
		for (int i = 0; i < edges.length; i++){
			String[] temp = edges[i].split(" ");
			int first = Integer.parseInt(temp[0]);
			int second = Integer.parseInt(temp[1]);
			matrix[first][second] = 1;
			matrix[second][first] = 1;
		}
		return matrix;
	}
	
	
	/*
	This is the method for filling the vertex array.
	It simply constructs an array with values from 1 to nodeCount.
	*/
	private int[] vList(int n){
		V = new int[n];
		for (int i = 0; i < n; i++){
			V[i] = i+1;
		}
		return V;
	}
}
