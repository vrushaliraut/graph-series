package com.expense;

/*
* You are given an undirected graph represented as an adjacency list and a source vertex.
* Perform Breadth First Search (BFS) starting from the source vertex
* and print all reachable vertices in a level-by-level order.
* The output should follow the BFS traversal rule:
- Print the source vertex first.
- Print all immediate neighbors of the source vertex next.
- Then print the neighbors of those neighbors, and so on.

Input ::
- An undirected graph represented as an adjacency list.
- A source vertex s.

Output ::
- A list of vertices in BFS order.

- Constraints
The graph may contain cycles.
The graph can have up to V vertices and E edges.
1 ≤ V ≤ 10^5
0 ≤ E ≤ min(V * (V - 1) / 2, 10^6) (sparse graph assumption).

Examples
Example 1:
Input:

Graph: {
  0: [1, 2],
  1: [0, 3],
  2: [0, 3],
  3: [1, 2]
}
Source: 0

Output:
BFS Order: [0, 1, 2, 3]

* Example 2:
Input:
Graph: {
  0: [1, 2, 5],
  1: [0, 3],
  2: [0, 4],
  3: [1],
  4: [2],
  5: [0]
}
Source: 0

Output:
BFS Order: [0, 1, 2, 5, 3, 4]
* */

import java.util.*;

public class BFSOnGraph {
    public static void main(String[] args) {
        // Example 1: Simple connected graph
        Map<Integer, List<Integer>> graph1 = new HashMap<>();
        graph1.put(0, Arrays.asList(1, 2));
        graph1.put(1, Arrays.asList(0, 3));
        graph1.put(2, Arrays.asList(0, 3));
        graph1.put(3, Arrays.asList(1, 2));
        int totalVertices = 4;

        // TC- O(V + E) SC - O(V)
        System.out.println("BFS Order (Graph 1): " + bfs(graph1, 0));

        // TC- O(V + E) SC - O(V/8)
        System.out.println("BFS Order (Graph 1): " + bfsWithBitSet(graph1, 0, totalVertices));
        // Output: [0, 1, 2, 3]

        // Example 2: Graph with multiple components
        Map<Integer, List<Integer>> graph2 = new HashMap<>();
        graph2.put(0, Arrays.asList(1, 2));
        graph2.put(1, Arrays.asList(0, 3));
        graph2.put(2, Arrays.asList(0, 4));
        graph2.put(3, Arrays.asList(1));
        graph2.put(4, Arrays.asList(2));
        graph2.put(5, Arrays.asList(0));
        int totalVerticesGraph2 = 6;
        System.out.println("BFS Order (Graph 2): " + bfs(graph2, 0));
        System.out.println("BFS Order (Graph 2): " + bfsWithBitSet(graph2, 0, totalVerticesGraph2));

        // Output: [0, 1, 2, 5, 3, 4]

        // Example 3: Disconnected graph
        Map<Integer, List<Integer>> graph3 = new HashMap<>();
        graph3.put(0, Collections.emptyList());
        graph3.put(1, Collections.emptyList());
        graph3.put(2, Collections.emptyList());
        int totalVerticesGraph3 = 3;
        System.out.println("BFS Order (Graph 3): " + bfs(graph3, 0));
        System.out.println("BFS Order (Graph 3): " + bfsWithBitSet(graph3, 0, totalVerticesGraph3));
        // Output: [0]


    }
    /*
    * Initialization:
       - Create an empty queue.
       - Create a boolean array visited of size V (number of vertices) and initialize all entries to False.
       - Mark the source vertex as True in the visited array and enqueue it into the queue.

    Traversal:
       - While the queue is not empty:
       - Dequeue the front vertex from the queue.
       - Print the dequeued vertex.
       - Traverse the adjacency list of the dequeued vertex:
           For each unvisited neighbor:
                Mark it as visited.
                Enqueue it into the queue.
      Termination:
        The algorithm terminates when the queue becomes empty, ensuring all reachable vertices are processed.
    * */

    private static List<Integer> bfs(Map<Integer, List<Integer>> graphMap, int source) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            Integer element = queue.poll();
            result.add(element);
            for (Integer neighbour : graphMap.getOrDefault(element, Collections.emptyList())) {
                if (!visited.contains(neighbour)) {
                    queue.add(neighbour);
                    visited.add(neighbour);
                }
            }
        }
        return result;
    }


    private static List<Integer> bfsWithBitSet(Map<Integer, List<Integer>> graph1, int source,
                                               int totalVertices) {

        // Initialize
        // Traverse
        //Terminate

        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        BitSet bitSet = new BitSet(totalVertices);
        queue.add(source);
        bitSet.set(source);

        // Traverse
        while (!queue.isEmpty()) {
            Integer currentElement = queue.poll();
            result.add(currentElement);
            for (Integer neighbour : graph1.getOrDefault(currentElement, Collections.emptyList())) {
                if (!bitSet.get(neighbour)){
                    bitSet.set(neighbour);
                    queue.add(neighbour);
                }
            }
        }
        return result;
    }

}
