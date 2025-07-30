package com.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MapRepresentation {
    private Map<Integer, List<Integer>> adjList;
    private int vertext;

    public MapRepresentation(int vertex) {
        this.vertext = vertex;
        adjList = new HashMap<>(vertex);
        for (int i = 0; i < vertex; i++) {
            adjList.put(i, new ArrayList<>());
        }
    }

    void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    void printGraph() {
        for (int i = 0; i < adjList.size(); i++) {
            System.out.println("Adjacency list of vertex  " + i + " : ");
            for (int j = 0; j < adjList.get(i).size(); j++) {
                System.out.println(adjList.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
