package com.code;

/*
* An adjacency matrix is a 2D array where the rows and columns represent vertices,
* and the entries indicate whether an edge exists between two vertices. Specifically:

matrix[i][j] = 1 means there is an edge between vertex i and vertex j.
matrix[i][j] = 0 means there is no edge between vertex i and vertex j.
* */

/*
- In an adjacency list, neighbors are explicitly stored as a list for each vertex.
- In an adjacency matrix,
neighbors must be determined by iterating through the row corresponding to the current vertex
 and checking which entries are 1.
*/

// Changes Required for DFS on Adjacency Matrix

/* DFS on Matrix
* 1. Finding neighbours -
*   - Instead of iterating through the adjacency list of a vertex,
*   - you iterate through the entire row of the adjacency matrix corresponding to the current vertex.
    - For each column j in the row,
    * if matrix[currentVertex][j] == 1, then j is a neighbor of currentVertex.

* 2. Traversal Logic
*  - Use a visited array or set to track visited vertices, just like in the adjacency list version.
*  - For each vertex, iterate through all possible vertices (0 to V-1) to check if they are neighbors

* 3. Handling disconnected graph
*  - For each vertex, iterate through all possible vertices (0 to V-1) to check if they are neighbors
* 4. Recursive and Iterative DFS
*   - Both recursive and iterative DFS can be implemented with an adjacency matrix.
* - The only difference is how neighbors are identified.
* */

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DFSOnAdjacencyMatrix {
    public static void main(String[] args) {
        // Example graph represented as an adjacency matrix
        int[][] graph = {
                {0, 1, 1, 0}, // Vertex 0 is connected to 1 and 2
                {1, 0, 0, 1}, // Vertex 1 is connected to 0 and 3
                {1, 0, 0, 0}, // Vertex 2 is connected to 0
                {0, 1, 0, 0}  // Vertex 3 is connected to 1
        };

        // Number of vertices in the graph
        int V = 4;

        // Perform DFS traversal
        System.out.println("DFS Traversal:");
        dfsTraversalForMatrix(graph, V);

        // Output: DFS Traversal: 0 1 3 2
        System.out.println("DFS Iterative Traversal :: ");
        dfsIterativeTraversal(graph, V);
    }

    /*
    * DFS(matrix, startVertex):
    stack = [startVertex]
    visited = {startVertex}
    while stack is not empty:
        current = stack.pop()
        process(current)
        for each vertex j from 0 to V-1:
            if matrix[current][j] == 1 and j not in visited:
                stack.push(j)
                visited.add(j)
    * */
    // Iterative traversal function
    private static void dfsIterativeTraversal(int[][] graph, int v) {
        // Step 1: Create a visited array to track visited vertices
        Set<Integer> visited = new HashSet<>();

        // Step 2: Iterate through all vertices to handle disconnected graphs
        for (int vertex = 0; vertex < v; vertex++) {
            if (!visited.contains(vertex)) {
                dfsIterativeOnMatrix(graph, vertex, visited);
            }
        }
    }

    private static void dfsIterativeOnMatrix(int[][] graph, int startVertex, Set<Integer> visited) {
        // Step 1: Initialize an explicit stack
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        // Step 2: Mark the starting vertex as visited
        visited.add(startVertex);

        // Step 3: Process the stack
        while (!stack.isEmpty()) {
            // Pop the top vertex from the stack
            int currentVertex = stack.pop();

            // Process the current vertex (e.g., print it)
            System.out.println("vertex :: " + currentVertex);
            // Push all unvisited neighbors onto the stack

            for (int neighbor = 0; neighbor < graph.length; neighbor++) {
                if (graph[currentVertex][neighbor] == 1 && !visited.contains(neighbor)){
                    stack.push(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

    private static void dfsTraversalForMatrix(int[][] graph, int v) {
        // Step 1: Create a visited set to track visited vertices
        Set<Integer> visited = new HashSet<>();

        // Step 2: Iterate through all vertices to handle disconnected graphs
        for (int vertex = 0; vertex < v; vertex++) {
            if (!visited.contains(vertex)) {
                // If the vertex is not visited, call DFS for it
                dfsRecursiveOnMatrix(graph, vertex, visited);
            }
        }

    }

    private static void dfsRecursiveOnMatrix(int[][] graph, int currentVertex, Set<Integer> visited) {
        // Step 1: Mark the current vertex as visited
        visited.add(currentVertex);

        // Step 2: Print or process the current vertex
        System.out.println("Current Vertex :: " + currentVertex);

        // Step 3: Traverse all vertices (neighbors) in the adjacency matrix
        for (int neighbor = 0; neighbor < graph.length; neighbor++) {
            if (graph[currentVertex][neighbor] == 1 && !visited.contains(neighbor)) {
                // If there is an edge and the neighbor is not visited, recursively call DFS
                dfsRecursiveOnMatrix(graph, neighbor, visited);
            }
        }
    }
}
