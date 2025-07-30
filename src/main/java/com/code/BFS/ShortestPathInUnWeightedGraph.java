package com.code.BFS;

/*
* You are given an unweighted graph (directed or undirected) and a source vertex.
* Your task is to compute the shortest distance (in terms of the number of edges) from the source vertex to every other vertex in the graph.
* If a vertex is unreachable from the source, its distance should be considered infinite.

Input ::
A graph represented as an adjacency list.
An integer source representing the source vertex.

Output:
An array distance[] where distance[i] represents the shortest distance from the source vertex to vertex i.
* If a vertex is unreachable, its value should be ∞ (or a large value like Integer.MAX_VALUE).

Constraints:
- The graph can be directed or undirected.
- The graph is unweighted (all edges have equal weight).
- 1 ≤ V ≤ 10^5, where V is the number of vertices.
- 0 ≤ E ≤ 10^5, where E is the number of edges.
- 0 ≤ source < V.
* */

/*
*
Graph: [[1, 2], [2, 3], [3], []]
Source: 0

    0
   / \
  1   2
 / \   \
2   3   3

Output:
Distance: [0, 1, 1, 2]
Explanation:
Distance from 0 to 0 = 0 (self-loop).
Distance from 0 to 1 = 1 (direct edge).
Distance from 0 to 2 = 1 (direct edge).
Distance from 0 to 3 = 2 (via path 0 → 1 → 3).

Example 2:
Input:

Graph: [[1, 4], [2, 3], [3], [4], []]
Source: 0

    0
   / \
  1   4
 / \
2   3
 \ /
  4

Output:
Distance: [0, 1, 2, 2, 1]

Explanation:
Distance from 0 to 0 = 0.
Distance from 0 to 1 = 1.
Distance from 0 to 2 = 2 (via path 0 → 1 → 2).
Distance from 0 to 3 = 2 (via path 0 → 1 → 3).
Distance from 0 to 4 = 1 (direct edge).

Example 3:
Input:

Graph: [[1], [2], [0], []]
Source: 3

    0 ←--→ 2
     ↖   ↙
       1
       ↑
       3

Output:

Distance: [∞, ∞, ∞, 0]

Explanation:
Vertex 3 is the source, so its distance is 0.
Vertices 0, 1, and 2 are unreachable from vertex 3, so their distances are ∞.
* */

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
* Edge Cases:

Empty Graph:
*   If the graph has no vertices or edges, the output should be an empty array or an array with all distances
* as ∞ except for the source vertex.
Single Vertex Graph:
*   If the graph contains only one vertex (the source), the distance to itself is  0.
Disconnected Graph:
*   Some vertices may be unreachable from the source. Their distances should be marked as ∞.
Self-Loops:
*   A vertex connected to itself does not affect the shortest path calculation since it adds no additional edges.
Cycles in the Graph:
*   Cycles do not impact the shortest path calculation in BFS because BFS explores each vertex only once.
* */
public class ShortestPathInUnWeightedGraph {
    public static void main(String[] args) {
        // Example 1
        List<List<Integer>> graph1 = Arrays.asList(
                Arrays.asList(1, 2), // Neighbours of vertex 0
                Arrays.asList(2, 3), // Neighbours of vertex 1
                Arrays.asList(3), // Neighbours of vertex 2
                Arrays.asList()
        );

        System.out.println("Example 1: ");
        shortestDistance(graph1, 0); // Source vertex = 0

        // Example 2
        List<List<Integer>> graph2 = Arrays.asList(
                Arrays.asList(1, 4), // Neighbours of vertex 0
                Arrays.asList(2, 3), // Neighbours of vertex 1
                Arrays.asList(3), // Neighbours of vertex 2
                Arrays.asList(4), // Neighbours of vertex 3
                Arrays.asList() // Neighbours of vertex 4
        );
        System.out.println("\n Example 2");
        shortestDistance(graph2, 0); // Source vertex = 0

        // Example 3
        List<List<Integer>> graph3 = Arrays.asList(
                Arrays.asList(1), // Neighbours of vertex 0
                Arrays.asList(2), // Neighbours of vertex 1
                Arrays.asList(0), // Neighbours of vertex 2
                Arrays.asList() // Neighbours of vertex 3
        );
        System.out.println("\n Example 3");
        shortestDistance(graph3, 3); // Source vertex = 0
    }


    private static void shortestDistance(List<List<Integer>> graph, int source) {
        // 1. Define graph size
        int V = graph.size();

        // define visited array of size V
        boolean[] visited = new boolean[V];

        // define distance array of size v
        int[] distance = new int[V];

        // declare queue and initialise queue with source , mark visited and distance of souce as a 0
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        distance[source] = 0;

        while (!queue.isEmpty()){
            int u = queue.poll();
            for (int neighbour : graph.get(u)){
                if (!visited[neighbour]){
                    queue.add(neighbour);
                    distance[neighbour] = distance[u] + 1;
                    visited[neighbour] = true;
                }
            }
        }

        // print distance array
        System.out.println(Arrays.toString(Arrays.stream(distance).toArray()));
    }
}
