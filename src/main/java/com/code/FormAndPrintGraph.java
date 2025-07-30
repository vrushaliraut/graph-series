package com.code;

import java.util.ArrayList;
import java.util.List;

/*
* Input:
Graph: {
  0: [1, 2, 5],
  1: [0, 3],
  2: [0, 4],
  3: [1],
  4: [2],
  5: [0]
}
Source: 0
* */
public class FormAndPrintGraph {
    public static void main(String[] args) {
        // List Representation
        ListRepresentGraph representGraph = new ListRepresentGraph(6);
        // add edge
        representGraph.addEdge(0, 1);
        representGraph.addEdge(0, 2);
        representGraph.addEdge(0, 5);
        representGraph.addEdge(1, 3);
        representGraph.addEdge(2, 4);
        // print graph
        representGraph.printGraph();


        // Map Representation
        MapRepresentation representMap = new MapRepresentation(6);
        // add edge
        representMap.addEdge(0, 1);
        representMap.addEdge(0, 2);
        representMap.addEdge(0, 5);
        representMap.addEdge(1, 3);
        representMap.addEdge(2, 4);
        // print graph
        representMap.printGraph();

        // Always prefer to use Map<Integer, List<Integer>> because
        // We can store non-order vertex and strings as well
        // Non-Consecutive or String-Based City IDs
        // Dynamic Addition/Removal of Cities
    }

}

class ListRepresentGraph {
    private final int vertex;
    private final List<List<Integer>> adjList;

    public ListRepresentGraph(int vertex) {
        this.vertex = vertex;
        adjList = new ArrayList<>(vertex);
        for (int i = 0; i < vertex; i++) {
            adjList.add(new ArrayList<>()); // add new arrayList at each vertex
        }
    }


    public void addEdge(int u, int v) {
        // add edges
        adjList.get(u).add(v); // Add v to u's adjacency list

        adjList.get(v).add(u); // Add u to v's adjacency list

    }

    // print a graph
    public void printGraph() {
        for (int i = 0; i < adjList.size(); i++) {
            System.out.println("Adjacency list of vertex " + i + ": ");
            for (int j = 0; j < adjList.get(i).size(); j++) {
                System.out.println(adjList.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
