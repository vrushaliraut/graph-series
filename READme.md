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

