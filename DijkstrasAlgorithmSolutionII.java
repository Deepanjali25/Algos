/* You are given an integer start and a list edges of pairs of integers.
The list is what's called an adjacency list, and it represents a graph. The number of vertices in the graph is
equal to the length of edges, where each index i in edges contains vertex i's outbound edges, in no particular
order. Each individual edge is represented by a pair of two numbers, [destination, distance] , where the
destination ig a positive integer denoting the destination vertex and the distance is a positive integer
representing the length of the edge (the distance from vertex i to vertex destination ). Note that these edges are
directed, meaning that you can only travel from a particular vertex to its destination—not the other way around
(unless the destination vertex itself has an outbound edge to the original vertex).
Write a function that computes the lengths of the shortest paths between start and all of the other vertices in
the graph using Dijkstra’s algorithm and returns them in an array. Each index i in the output array should
represent the length of the shortest path between 'start' and vertex i . If no path is found from start to vertex
i, then output[i] should be -1.
Note that the graph represented by edges won't contain any self-loops (vertices that have an outbound edge to
themselves) and will only have positively weighted edges (i.e., no negative distances).

Sample Input:
start = 0
edges = [ [[1, 7]], [[2, 6], [3, 20], [4, 3]], [[3, 14]], [[4, 2]], [], [], ]
Sample Output:
[0, 7, 13, 27, 10, -1]
Hint1 Dijkstra's algorithm works by visiting vertices in the graph, one by one, all the while keeping track of the
current shortest distances from the start vertex to all other vertices and continuously updating these shortest
distances. More specifically, the algorithm keeps track of unvisited vertices and visits the unvisited vertex with
the shortest distance at any point in time, naturally starting with the start vertex. Whenever the algorithm
visits an unvisited vertex, it looks at all of its outbound edges and tries to update the shortest distances from
the start to the destinations in the edges, using the current shortest distance to the current vertex as a base.
Once the algorithm has visited all of the vertices and considered all of their edges, it is guaranteed to have
found the shortest path to each vertex. How can you implement this algorithm?
Hint2 The most challenging part of Dijkstra's algorithm is determining how to efficiently find the vertex with the
current shortest distance. Can you think of a data structure that could be used to keep track of the distances and
to efficiently retrieve the vertex with the current shortest distance at each step?
Hint3 Create an array that can store the final shortest distances between the start vertex and all other vertices,
as well as a min-heap that will hold all of the unvisited vertices and their current shortest distances. For both
the final distances array and the min-heap, initialize all vertices except for the start node as having a distance
of infinity; the start node will have a distance 0. Next, write a while loop that will run until the min-heap is
empty. At every iteration in the loop, remove the vertex from the top of the heap (the vertex with the shortest
distance), loop through all of its edges, and for each edge, update the shortest distance of the destination
vertex to be the minimum of the destination's current shortest distance and the currently visited vertex's
distance plus the current edge's weight. Once the heap is empty, all of the vertices will have been visited, and
you'll have the shortest distances to all vertices stored in your distances array.
Optimal Space & Time Complexity - O((v + e) * log(v)) time | O(v) space - where v is the number of vertices and e
is the number of edges in the input graph. */
package FamousAlgorithms;
import java.util.*;
public class DijkstrasAlgorithmSolutionII {
    public int[] dijkstrasAlgorithm(int start, int [][][] edges) {
        int numberOfVertices = edges.length;
        int[] minDistances = new int[numberOfVertices];
        Arrays.fill(minDistances, Integer.MAX_VALUE);
        minDistances[start] = 0;
        List<Item> minDistancesPairs = new ArrayList<Item>();
        for (int i = 0; i < numberOfVertices; i++) {
            Item item = new Item(i, Integer.MAX_VALUE);
            minDistancesPairs.add(item);
        }
        MinHeap minDistancesHeap = new MinHeap(minDistancesPairs);
        minDistancesHeap.update(start, 0);
        while (!minDistancesHeap.isEmpty()) {
            Item heapItem = minDistancesHeap.remove();
            int vertex = heapItem.vertex;
            int currentMinDistance = heapItem.distance;
            if(currentMinDistance == Integer.MAX_VALUE) {
                break;
            }
            for (int[] edge : edges[vertex]) {
                int destination = edge[0];
                int distanceToDestination = edge[1];
                int newPathDistance = currentMinDistance + distanceToDestination;
                int currentDestinationDistance = minDistances[destination];
                if (newPathDistance < currentDestinationDistance) {
                    minDistances[destination] = newPathDistance;
                    minDistancesHeap.update(destination, newPathDistance);
                }
            }
        }
        int[] finalDistances = new int[minDistances.length];
        for (int i = 0; i < minDistances.length; i++) {
            int distance = minDistances[i];
            if (distance == Integer.MAX_VALUE) {
                finalDistances[i] = -1;
            }
            else {
                finalDistances[i] = distance;
            }
        }
        return finalDistances;
    }
    static class Item {
        int vertex;
        int distance;
        public Item(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    };
    static class MinHeap {
        Map<Integer, Integer> vertexMap = new HashMap<Integer, Integer>();
        List<Item> heap = new ArrayList<Item>();
        public MinHeap(List<Item> array) {
            for (int i = 0; i < array.size(); i++) {
                Item item = array.get(i);
                vertexMap.put(item.vertex, item.vertex);
            }
            heap = buildHeap(array);
        }
        List<Item> buildHeap(List<Item> array) {
            int firstParentIdx = (array.size()-2)/2;
            for (int currentIdx = firstParentIdx+1; currentIdx >= 0; currentIdx--) {
                siftDown(currentIdx, array.size() -1, array);
            }
            return array;
        }
        boolean isEmpty() {
            return heap.size() == 0;
        }
        void siftDown(int currentIdx, int endIdx, List<Item> heap) {
            int childOneIdx = currentIdx * 2 + 1;
            while(childOneIdx <= endIdx) {
                int childTwoIdx = currentIdx * 2 + 2 <= endIdx ? currentIdx * 2 + 2 : -1;
                int idxToSwap;
                if (childTwoIdx != -1 && heap.get(childTwoIdx).distance < heap.get(childOneIdx).distance) {
                    idxToSwap = childTwoIdx;
                }
                else {
                    idxToSwap = childOneIdx;
                }
                if (heap.get(idxToSwap).distance < heap.get(currentIdx).distance) {
                    swap(currentIdx, idxToSwap);
                    currentIdx = idxToSwap;
                    childOneIdx = currentIdx * 2 + 1;
                }
                else {
                    return;
                }
            }
        }
        void siftUp(int currentIdx) {
            int parentIdx = (currentIdx - 1) / 2;
            while(currentIdx > 0 && heap.get(currentIdx).distance < heap.get(parentIdx).distance) {
                swap(currentIdx, parentIdx);
                currentIdx = parentIdx;
                parentIdx = (currentIdx-1) / 2;
            }
        }
        Item remove() {
            if(isEmpty()) {
                return null;
            }
            swap(0, heap.size()-1);
            Item lastItem = heap.get(heap.size()-1);
            int vertex = lastItem.vertex;
            int distance = lastItem.distance;
            heap.remove(heap.size()-1);
            vertexMap.remove(vertex);
            siftDown(0, heap.size() -1, heap);
            return new Item(vertex, distance);
        }
        void update(int vertex, int value) {
            heap.set(vertexMap.get(vertex), new Item(vertex, value));
            siftUp(vertexMap.get(vertex));
        }
        void swap(int i, int j) {
            vertexMap.put(heap.get(i).vertex, j);
            vertexMap.put(heap.get(j).vertex, i);
            Item temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }
}
