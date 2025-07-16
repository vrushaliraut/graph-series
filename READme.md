# Pattern 1 - 
## BFS 
## BFS Naive Way 
```aiignore
Step-by-Step Explanation

Initialization:
    Create an empty queue.
    Create a boolean array visited of size V (number of vertices) and initialize all entries to False.
    Mark the source vertex as True in the visited array and enqueue it into the queue.

Traversal:
    While the queue is not empty:
    Dequeue the front vertex from the queue.
    Print the dequeued vertex.
    Traverse the adjacency list of the dequeued vertex:
    For each unvisited neighbor:
    Mark it as visited.
    Enqueue it into the queue.

Termination:
    The algorithm terminates when the queue becomes empty, ensuring all reachable vertices are processed.


```

## BFS Optimised Way - Bitset   
A bitset is a compact data structure that represents a sequence of bits (0s and 1s). Each bit corresponds to a boolean value (0 for false, 1 for true). It is highly efficient in terms of memory because it stores multiple boolean values in a single machine word (e.g., 32 or 64 bits per word).
In BFS, we use a visited array to track whether each vertex has been visited. For a graph with V vertices:

A boolean[] array requires V bytes of memory.
A BitSet requires only V / 8 bytes of memory (since each bit represents one vertex).

This reduction in memory usage becomes significant when dealing with very large graphs (e.g., graphs with millions of vertices). Here's a detailed breakdown:

1. Create a BitSet of size V (number of vertices).
2. Initialize a queue.
3. Mark the source vertex in the BitSet and enqueue it.
4. While the queue is not empty:
    - Dequeue a vertex.
    - Print the vertex.
    - For each unvisited neighbor:
        - Mark it in the BitSet.
        - Enqueue it.

## Optimization 2: Early Termination for Specific Goals
If the task involves finding specific properties (e.g., shortest path, connected components, or reachability), 
we can terminate the BFS traversal early once the desired result is found.

To find the shortest path from a source to a target:

Stop the BFS as soon as the target vertex is dequeued.
Return the path by backtracking using a parent array.
Ultra-Compact Pseudocode
1. Create a parent array to track the path.
2. Perform BFS until the target vertex is found.
3. Reconstruct the path using the parent array.
- Time Complexity: O(V + E) (early termination may reduce runtime in practice).
- Space Complexity: O(V) (for the parent array).


## Optimization 3: Iterative Component Discovery
For disconnected graphs, instead of explicitly iterating over all vertices, 
we can dynamically discover connected components during traversal.

Ultra-Compact Pseudocode

1. Create a visited BitSet of size V.
2. Initialize a counter for connected components.
3. For each unvisited vertex:
    - Increment the counter.
    - Perform BFS starting from this vertex.

Complexity
- Time Complexity: O(V + E)
- Space Complexity: O(V / 8) (due to BitSet).

## Optimization 4: Parallel BFS for Large Graphs
For extremely large graphs, BFS can be parallelized to improve performance. 
Each thread processes a subset of vertices or levels.

Ultra-Compact Pseudocode
1. Divide the graph into chunks.
2. Assign each chunk to a thread.
3. Perform BFS in parallel, synchronizing visited states across threads.

Complexity
- Time Complexity: O(V + E) (reduced in practice due to parallelism).
- Space Complexity: O(V) (shared visited structure).

- Parallelizing BFS for large graphs is a non-trivial task because it requires careful synchronization to ensure correctness while maintaining performance. Below is the Java 21 implementation of parallel BFS, where the graph is divided into chunks, and each thread processes a subset of vertices or levels.
