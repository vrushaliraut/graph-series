package com.code;

/*
1. Divide the graph into chunks.
2. Assign each chunk to a thread.
3. Perform BFS in parallel, synchronizing visited states across threads.

* */
// Time Complexity: O(V + E) (reduced in practice due to parallelism).
// Space Complexity: O(V) (shared visited structure).

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelBFS {
    public static void main(String[] args) throws InterruptedException {
        // Example: Large graph with 1 million vertices
        int totalVertices = 1_000_000;
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // Generate a random sparse graph
        Random random = new Random();
        for (int i = 0; i < totalVertices; i++) {
            graph.put(i, Arrays.asList(
                    (i + 1) % totalVertices,
                    (i + 2) % totalVertices,
                    (i + 3) % totalVertices
            ));
        }

        // Perform parallel BFS
        long startTime = System.currentTimeMillis();
        List<Integer> bfsOrder = parallelBFS(graph, totalVertices);
        long endTime = System.currentTimeMillis();

        // Print results
        System.out.println("BFS Order Size: " + bfsOrder.size());
        System.out.println("Time Taken: " + (endTime - startTime) + " ms");

    }

    private static List<Integer> parallelBFS(Map<Integer, List<Integer>> graph,
                                            int totalVertices) throws InterruptedException {
        // Result list to store the BFS traversal order (thread-safe)
        List<Integer> result = Collections.synchronizedList(new ArrayList<>());

        // BitSet to track visited vertices shared across threads
        BitSet visited = new BitSet(totalVertices);

        // Thread pool for parallel execution
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // AtomicInteger to assign unique IDs to threads for debugging
        AtomicInteger atomicInteger = new AtomicInteger(0);

        // Submit task for each vertex as a potential source
        for (int vertex = 0; vertex < totalVertices; vertex++) {
            if (!visited.get(vertex)){
                // Submit a BFS task for a unvisited vertex
                int finalVertex = vertex;
                executorService.submit(() -> {
                    // Local queue for this thread
                    Queue<Integer> localQueue = new LinkedList<>();
                    localQueue.add(finalVertex);
                    visited.set(finalVertex);

                    // Perform BFS for this threads chunk
                    while (!localQueue.isEmpty()){
                        int currentVertex = localQueue.poll();
                        synchronized (result){
                            result.add(currentVertex); // add to result
                        }

                        // Traverse neighbour
                        for(int neighbour : graph.getOrDefault(currentVertex, Collections.emptyList())){
                            if (!visited.get(neighbour)){
                                synchronized(visited){
                                    if (!visited.get(neighbour)){ // Double check locking
                                        visited.set(neighbour);
                                        localQueue.add(neighbour);
                                    }
                                }
                            }
                        }
                    }

                });
                executorService.shutdown();
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                // Return the BFS traversal order
                return result;

            }
        }
        return null;
    }
}
