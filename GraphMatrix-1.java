import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphMatrix implements Graph {
    private int [][] adjMatrix;
    private int numVertices;
    private int numEdges;
    private boolean [] visited;

    @Override
    public void init(int n) { // initializes the graph, takes an integer as an argument
        this.numVertices = n;
        adjMatrix = new int[n][n];
    }

    @Override
    public int nodeCount() { // returns the number of nodes
        return numVertices;
    }

    @Override
    public int edgeCount() { // returns an integer which is current number of edges
       return numEdges;
    }


    @Override
    public void addEdge(int v, int w, int wgt) { // adding an edge by giving two vertices a weight between the two, increment numedges
        if (wgt == 0) {
            return;
        }
        adjMatrix[v][w] = wgt;
        numEdges++;
    }

    @Override
    public int getWeight(int v, int w) { //returns the weight of the edge
        return adjMatrix[v][w];
    }

    @Override
    public void setWeight(int v, int w, int wgt) { // setting the weight of the edge
        if (v > nodeCount() || w > nodeCount()) {
            System.out.println("node is not in bounds");
            return;
        }
        adjMatrix[v][w] = wgt;
    }

    @Override
    public void removeEdge(int v, int w) { // sets the weights of two vertices in both directions to 0 so there is no edge
        if (adjMatrix[v][w] != 0) {
            adjMatrix[v][w] = 0;
            numEdges--;
        }
    }

    @Override
    public boolean hasEdge(int v, int w) { // boolean that returns true if there is an edge between two vertices
        if (v > nodeCount() || w > nodeCount()) {
            System.out.println("node is not in bounds");
            return false;
        }
        else if(adjMatrix[v][w] != 0 && adjMatrix[w][v] != 0){ // if there is a weight for an edge both to and from a vertex, then it has an edge
            return true;
        }
        return false;
    }

    @Override
    public int[] neighbors(int v) {
        if (v > nodeCount()) { //case to check if node is in the map
            System.out.println("node out of bounds");
            return null;
        }
        int count = 0;
        for (int i = 0; i < numVertices; i++) { // for loop to count the number of edges
            if(adjMatrix[v][i] > 0) {
                count++;
            }
        }
        int [] numNeighbors = new int [count];
        count = 0;

        for (int i = 0; i < numVertices; i++) { // counts number of vertices
            if (adjMatrix[v][i] > 0) {
                numNeighbors[count] = i;
                count++;
            }
        }

        return numNeighbors;
    }

    @Override
    public void resetVisited() { //function to set the visited vertex to false
        for (int i = 0; i< numVertices; i++) {
            visited[i] = false;
        }

    }

    @Override
    public ArrayList<Integer> BFS(int v) { // this function performs a BFS starting at vertex v, returns an arrayList
        visited = new boolean[numVertices];
        Queue<Integer> que = new LinkedList<>();
        ArrayList<Integer> returnVal = new ArrayList<>();

        returnVal.add(v); // adding search value to arrayList for return
        que.add(v);

        visited[v] = true; //setting visited to true

        int visit;

        while (!que.isEmpty()) {
            visit = que.remove(); // removing visited items from q
            System.out.println(visit + " ");
            for (int i = 0; i < visited.length; i++) {
                if(!visited[i] && adjMatrix[visit][i] > 0) { // if not already visited, add to queue
                    que.add(i);
                    returnVal.add(i);
                    visited[i] = true;
                }
            }
        }
        return returnVal;
    }

    @Override
    public boolean hasPath(int v, int w) { // returns true if a path exists between two vertices
        resetVisited();
        ArrayList<Integer> temp = BFS(v);
        if(temp.contains(w)) { // if w is in the result of the BFS, then the item is in the graph
            return true;        // if the item is in the graph, then a path exists
        }
        return false;
    }

    @Override
    public ArrayList<Integer> topologicalSort() {
        int [] indegree = new int[numVertices];

        for (int i = 0; i < numVertices; i++) {
            int [] temp = neighbors(i);
            for (int item : temp) {
                indegree[item]++; //calculating incoming edges
            }
        }
        Queue<Integer> Q = new LinkedList <Integer> (); // enqueing items whose indegree is 0
        for (int i = 0; i < numVertices; i++) {
            if(indegree[i] == 0) {
                Q.add(i);
            }
        }

        int numVisited = 0; // count of visited vertices

        ArrayList<Integer> topSort = new ArrayList<>();
        while (!Q.isEmpty()) {
            int item = Q.poll(); // dequeue the item and add it to list of topological sorted items
            topSort.add(item);

            for (int node : neighbors(item)) { //iterate through all neighboring nodes and decrease their indegree by one
                if (--indegree[node] == 0) {
                    Q.add(node); // add it to queue once indegree is 0
                }
            }
            numVisited++; // increment numVisited

        }
        if (numVisited > numVertices) { //check to make sure there are no cycles
            System.out.println("already visited, there exists a cycle");
            return null;
        }
        for (int node : topSort) { //printing topologically sorted items
            System.out.println(node + " ");
        }
        return topSort;
    }

    public static void main(String[] args) {
        GraphMatrix graph = new GraphMatrix();
        graph.init(7);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2,3, 3);
        graph.addEdge(3, 4, 4);
        graph.addEdge(1, 5, 5);
        System.out.println(graph.edgeCount());
        //graph.addEdge(6,7,9);
        graph.removeEdge(1,5);
        System.out.println(graph.edgeCount());
        graph.addEdge(1, 5, 5);
        System.out.println(graph.edgeCount());
        System.out.println(graph.nodeCount());
        System.out.println("top sort:");
        graph.topologicalSort();
        graph.BFS(1);
        boolean j = graph.hasPath(5,4);
        System.out.println(j);







    }
}
