/*
Jared Kramer

This class (along with the accompanying graph.java and Tuple.java)
are used to determine if sub-graph isomorphisms exist between two given graphs.
The algorithm uses a depth-first resursive search method to compare every possible
combination of edges in the model graph and data graph.

The algorithms workflow is essentially as follows:

Open two properly formatted input files,
Create graphs for each input file, including an adjacency matrix for each,
Recursively search for sub-graph isomorphism between the two graphs,
Output isomorphisms as they are found.

*/

import java.io.*;

public class Match{

	public static void main(String[] args){
		
		//read the input files
		String[] model = readFile(args[0]);
		String[] data = readFile(args[1]);
		
		//create graphs
		graph datag = new graph(data);
		graph modelg = new graph(model);
		
		//search and output isomorphisms
		Tuple[] h = new Tuple[modelg.V.length];  
 		search(modelg, datag, h);
	}
	
	/*
	This is the search method that determines whether there is 
	isomorphism between the model and the data.  The algorithm follows
	the pseudo-code given in class except for one part that I have noted below.
	*/
	private static void search(graph M, graph D, Tuple[] h){
		int v = M.V[0];
		int q = 0; //q keeps track of the index in the Tuple array where a new tuple is placed
		for(; q < h.length && h[q] != null; q++){
			;			
		}
		for(int i = 0; i < D.V.length; i++){
			int w = D.V[i];
		 	Tuple t = new Tuple(v, w);
			h[q] = t; //add a new tuple
			boolean OK = true;
			for (int k = 0; k < M.V.length; k++){
				boolean shouldBreak = false;
				for (int j = 0; j < M.V.length; j++){
					if (M.matrix[M.V[k]][M.V[j]] == 0){
						continue;
					}
					/*
					This is part of the pseudo-code that I tried to incorporate
					I could not quite get it to work, I believe the problem is 
					in the big if statement. Instead I wrote a different chunk of code
					that determines in the th should be printed.
					*/
// 					if ( (M.V[k] == v && searchH(M.V[j], h) != null) || (M.V[j] == v && searchH(M.V[k], h) != null) ){
// 						if(D.matrix[searchH(M.V[k], h).y][searchH(M.V[j], h).y] == 0){
// 							OK = false;
// 							shouldBreak = true;
// 							break;
// 						}
// 					}
				}
				if(shouldBreak == true){
					break;
				}
			}
			if (OK == true){
				if(M.V.length == 1){
				boolean isGood = true;
				/*
				This is what determines if h should be printed.
				Essentially it checks h against the adjacency matrices,
				and if the edges are present, it prints them out.
				*/
					for(int g = 0; g < h.length && isGood == true; g++){
						for(int f = g+1; f < h.length && isGood == true; f++){
							if(M.matrix[h[g].x][h[f].x] == 1 && D.matrix[h[g].y][h[f].y] == 0){
								isGood = false;
							}
						}
					}
					if(isGood == true){
						System.out.println(printH(h));
					}
				}
				/*
				Here I rebuild the vertex arrays and attached them to a new graph
				for use as an argument in the recursive call.
				*/
				else{		
					int[] modelTemp = rebuildV(v, M.V);
					int[] dataTemp = rebuildV(D.V[i], D.V);

					Tuple[] h2 = new Tuple[h.length];
					for(int p = 0; p < h.length; p++){
						h2[p] = h[p];
					}
					graph M2 = new graph();
					graph D2 = new graph();
					
					M2.V = modelTemp;
					D2.V = dataTemp;
					M2.matrix = M.matrix;
					D2.matrix = D.matrix;
					
					//recursive call
					search(M2, D2, h2);
				}
			
			}
		}
		
	}
	
	
	/*
	This method opens an input file, builds a string out of the contents
	and then returns that string as an array.  That array represents the data that
	will be used to build the adjacency matrix. 
	The actual matrix is built inside on the graph class.
	*/
	private static String[] readFile(String file){
		try{
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line;
			String data = "";
			while((line = in.readLine()) != null){
				data = data + '\n' + line;			}
			in.close();
			String[] data_array = data.split("\\n");
			return data_array;
		}
		catch(IOException e){
			System.out.println(e);
		}
		String[] shouldNeverReturn = new String[1];
		return shouldNeverReturn;
	}

	/*
	This method searches the tuple array for an int value.
	It returns the tuple containing the value is found, null otherwise.
	It is meant to be used in the class pseudo code.
	*/
	private static Tuple searchH(int n, Tuple[] tups){
		for (int i = 0; i < tups.length; i++){
			if(tups[i] == null){
				return null;
			}
			if (n == tups[i].x){
				return tups[i];
			}
		}
		return null;
	}
	
	
	/*
	This method rebulds the vertex array for a graph.
	this essentially removes a value from the vertex array.
	Because it is a simple array, I had to rebuild it to remove a value.
	*/
	private static int[] rebuildV(int v, int[] V){
		int[] temp = new int[V.length-1];
		boolean flag = false;
		for (int i = 0; i < V.length; i++){
			if(V[i] != v)
			{
				if(flag == true)
				{
					temp[i-1] = V[i];
				}
				else
				{
					temp[i] = V[i];
				}
			}
			else
			{
				flag = true;
			}
		}
		return temp;
	}
	
	/*
	This method is used to print the output when an isomorphism is found.
	It formats and then returns the tuple array.
	*/
	private static String printH(Tuple[] h){
		String answer = "ISOMORPHISM:\t{";
		for(int i = 0; i < h.length; i++){
			answer = answer + h[i] + (i < h.length-1? ",": "");
		}
		
		answer = answer + "}";
		return answer;
	}

}
