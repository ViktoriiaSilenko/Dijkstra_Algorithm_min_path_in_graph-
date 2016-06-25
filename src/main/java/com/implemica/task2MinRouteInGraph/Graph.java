package com.implemica.task2MinRouteInGraph;

import java.io.PrintStream;
import java.util.AbstractMap.SimpleEntry;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Viktoriia Silenko
 */

public class Graph {
	
	private final int INF = 200000; // To identify that we have no rib between vertices.
	// We use 200000, because the cost of each path (which is the sum of costs of all direct connections belongning to this path) is at most 200000
	
	private int[][] contiguityMatrix; // contiguity matrix
	
	private int vertexNumber;
	
	private Map <Entry<Integer,Integer>, Integer> lazyCache = new HashMap <Entry<Integer,Integer>, Integer> (); // use lazy cashe 
	//because data (distances between cities) is changed rarely
		
	
	public Graph(int vertexNumber) {
		
		this.vertexNumber = vertexNumber;
		
		contiguityMatrix = new int[vertexNumber][vertexNumber];
		/*for (int i = 0; i < vertexNumber; i++) {
			for (int j = 0; j < vertexNumber; j++) {
				if (i != j) {
					contiguityMatrix[i][j] = INF;
				}
			}
		}*/
	}
	
	public int getVertexNumber() {
		return vertexNumber;
	}
	
	public void setCost(int fromVertex, int toVertex, int cost) {
		contiguityMatrix[fromVertex][toVertex]= cost;
		lazyCache.clear();
	}
	
	public void printContiguityMatrix(PrintStream printStream) {
		for (int i = 0; i < vertexNumber; i++) {
			for (int j = 0; j < vertexNumber; j++) {

				printStream.print(contiguityMatrix[i][j] + "  ");
			}
			printStream.println();
		}
	}

	/**
     * Calculate a min cost from start vertex to each vertex in graph with positive weights, using Dijkstra Algorithm with O(vertexNumber^2) 
     * 
     * @param startVertex - index of start vertex
     */
	
	/* Dijkstra Algorithm O(vertexNumber^2) */
	private int[] dijkstraAlgorithm(int startVertex) {
		
		BitSet unvisitedVertexes = new BitSet(vertexNumber); // непосещенные вершины

		for (int i = 0; i < vertexNumber; i++) {
			unvisitedVertexes.set(i); 
		}
		int [] minDistances = new int[vertexNumber]; // кратчайшие расстояния до i-вершины из startVertex-вершины             
		
		//minDistances[startVertex] = 0;
		for (int i = 0; i < vertexNumber; i++) {
			if (i != startVertex) {
				minDistances[i] = INF;
			}
		}
		
		while (!unvisitedVertexes.isEmpty()) {
			int minDistance = INF;
			int vertexWithMinDistance = -1;

			for (int i = unvisitedVertexes.nextSetBit(0); i != -1; i = unvisitedVertexes.nextSetBit(i+1)) {
				if (minDistances[i] < minDistance) {
					minDistance = minDistances[i];
					vertexWithMinDistance = i;
				}
			}
		
			unvisitedVertexes.clear(vertexWithMinDistance);

			for (int i = unvisitedVertexes.nextSetBit(0); i != -1; i = unvisitedVertexes.nextSetBit(i+1)) {
				int neighborVertex = contiguityMatrix[vertexWithMinDistance][i];
				if (neighborVertex != 0) { // if rib between vertices exists
					if (minDistances[i] > minDistances[vertexWithMinDistance] + neighborVertex) {
						minDistances[i] = minDistances[vertexWithMinDistance] + neighborVertex;
					}
				}
			}
		}
		
		return minDistances;
	}
	
	
	public int getMinCostBetweenVertices (int startVertex, int endVertex) {

		Entry<Integer,Integer> vertexesPairKey = new SimpleEntry<>(startVertex, endVertex);
	
		if (lazyCache.containsKey(vertexesPairKey)) {
            return lazyCache.get(vertexesPairKey);
        } else {
			int [] distancesToStartVertex = dijkstraAlgorithm(startVertex);
			int minDistanceValue = distancesToStartVertex[endVertex];
			lazyCache.put(vertexesPairKey, minDistanceValue);
			return minDistanceValue;
        }
	}

}
