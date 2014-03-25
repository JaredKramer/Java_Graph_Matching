Graph Matching in Java
===================

These three classes (Match.java, graph.java and Tuple.java)
are used to determine if sub-graph isomorphisms exist between two given graphs.
The algorithm uses a depth-first resursive search method to compare every possible
combination of edges in the model graph and data graph.

The algorithms workflow is essentially as follows:

Open two properly formatted input files,
Create graphs for each input file, including an adjacency matrix for each,
Recursively search for sub-graph isomorphism between the two graphs,
Output isomorphisms as they are found.
