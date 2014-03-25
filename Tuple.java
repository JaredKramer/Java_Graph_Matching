/*
Jared Kramer
CSE 373
Homework 5: Graph Matching

This is a class for a basic tuple.
Each tuple has two int values.
Each tuple represents an edge, 
where each int value is a vertex in the graph.

*/

public class Tuple{ 

  public final int x; 
  public final int y; 
  
  /*
  This is the constructor for a tuple.
  It takes two int values and creates a tuple.
  */
  
  public Tuple(int x, int y) { 
    this.x = x; 
    this.y = y; 
  } 
  
  /*
  This method returns a string representing 
  the int values in a tuple.
  It is used for printing purposes.
  */
  public String toString(){
  	 return "(" + this.x + "," + this.y + ")";
  }
  
} 
