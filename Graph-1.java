import java.util.ArrayList;
interface Graph { // Graph class ADT
    // Initialize the graph with some number of vertices
    void init(int n);
    // Return the number of vertices
    int nodeCount();
    // Return the current number of edges. This operation must be a constant time
    //operation.
    int edgeCount();
    // Adds a new edge from node v to node w with weight wgt
    void addEdge(int v, int w, int wgt);
    // Get the weight value for an edge
    int getWeight(int v, int w);
    // Set the weight of v and w.
    void setWeight(int v, int w, int wgt);
    // Removes the edge from the graph.
    void removeEdge(int v, int w);
    // Returns true if and only if the graph has an edge between v and w.
    boolean hasEdge(int v, int w);
    // Returns an array containing vertex id's of the neighbors of v.
    int[] neighbors(int v);
    // Resets the Visited array (required for BFS) to all false.
    void resetVisited();
    // Performs a Breadth-First-Search starting at vertex, v. On PreVisit, the
   // current vertex's label/id should be appended to the end of an ArrayList. Do not
    //perform a PostVisit operation.
    // Index 0 of the array should be the starting vertex, v. Index 1 should be one
    //of v's neighbors and so on.
    // Once BFS is completed, the ArrayList is returned.
    ArrayList<Integer> BFS(int v);
    // Returns true if there is a path between v and w. Otherwise returns false. You
    //may use the BFS method (above) for this method.
    boolean hasPath(int v, int w);
    // Performs a topologicalSort if the graph and returns an ArrayList that contains
    //the vertex labels/id in topologically sorted order. You must use BFS
    ArrayList<Integer> topologicalSort();
}