package com.code;

import java.util.*;

/*
* Initialization -
*   - Create a boolean array of size V and initialize all value to false
*   - Initialize an empty stack
* Start Traversal -
*  push the source vertex on to the stack and mark it as visited
*  while stack is not empty
*   - pop the vertex from the stack
*   - process the vertex - print it or store it in result
*   - Push all unvisited neighbours of the vertex onto the stack and mark them as visited
* Handling Disconnected Graphs:
    - If no source vertex is provided, iterate through all vertices.
    - For each unvisited vertex, push it onto the stack and start a new DFS traversal.
* */

public class DFSOnGraph {
    public static void main(String[] args) {
        // Example graph represented as an adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 3));
        graph.put(2, Arrays.asList(0));
        graph.put(3, Arrays.asList(1));

        // No of vertices in a graph
        int V = 4;

        // Perform DFS traversal
        /*System.out.println("DFS Traversal :: ");
        dfsTraversal(graph, V);*/

        System.out.println("DFS Traversal Iterative :: ");
        dfsTraversalIterative(graph, V);

        // Output - 0 1 3 2
    }

    /*
    * Initialization -
    * - Create a visited set to track visited vertices
    * - Initialized empty stack (explicit stack)
    *
    * Stack Traversal -
    * - push the source vertex onto the stack and mark it as visited
    * - while the stack is not empty
    *   - pop the top vertex from the stack
    *   - Process the vertex (print or store it in result)
    *   - Push all unvisited neighbours of the vertex onto the stack and mark them as visited
    * Handling Disconnected Graphs:
    * Iterate through all vertices.
    * For each unvisited vertex, push it onto the stack and start a new DFS traversal.
    * */
    private static void dfsTraversalIterative(Map<Integer, List<Integer>> graph, int v) {
        // Step 1: Create a visited set to track visited vertices
        Set<Integer> visited = new HashSet<>();

        // Step 2: Iterate through all vertices to handle disconnected graphs
        for (int vertex = 0; vertex < v; vertex++) {
            if (!visited.contains(vertex)){
                // If the vertex is not visited, call iterative DFS for it
                dfsIterative(graph, vertex, visited);
            }
        }
    }

    // Iterative DFS function
    private static void dfsIterative(Map<Integer, List<Integer>> graph,
                                     int startVertex, Set<Integer> visited) {
        // 1. explicit stack initialization
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);
        visited.add(startVertex);

        // 2. Process the stack
        while (!stack.isEmpty()){
            // Pop the top vertex from the stack
            int currentVertex = stack.pop();

            // process the current vertex
            System.out.println(currentVertex +" ");

            // Push all unvisited neighbours on to the stack
            if (graph.containsKey(currentVertex)){
                for(int neighbour : graph.get(currentVertex)){
                    if (!visited.contains(neighbour)){
                        stack.push(neighbour);
                        visited.add(neighbour);
                    }
                }
            }
        }
    }

    // Function to perform DFS traversal
    private static void dfsTraversal(Map<Integer, List<Integer>> graph, int v) {
        // Step 1: Create a visited array to track visited vertices
        boolean[] visited = new boolean[v];

        // Step 2: Iterate through all vertices to handle disconnected graphs
        for (int vertex = 0; vertex < v; vertex++) {
            if (!visited[vertex]) {
                // If the vertex is not visited, call DFS for it
                dfsRecursive(graph, vertex, visited);
            }
        }
    }

    // Recursive DFS function
    private static void dfsRecursive(Map<Integer, List<Integer>> graph,
                                     int currentVertex, boolean[] visited) {
        // Step 1: Mark the current vertex as visited
        visited[currentVertex] = true;

        // Step 2: Print or process the current vertex
        System.out.println(currentVertex + " ");

        // Step 3: Traverse all adjacent vertices of the current vertex
        if (graph.containsKey(currentVertex)) {
            // Check if the vertex has neighbors
            for (int neighbour : graph.get(currentVertex)) {
                if (!visited[neighbour]) {
                    // if the neighbour is not visited recursively call DFS for it
                    dfsRecursive(graph, neighbour, visited);
                }
            }
        }
    }
}
