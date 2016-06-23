package com.implemica.task2MinRouteInGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Viktoriia Silenko
 */

public class Graph {
	
	private final int INF = 200000; // To identify that we have no rib between vertices.
	// We use 200000, because the cost of each path (which is the sum of costs of all direct connections belongning to this path) is at most 200000
	
	private int[][] contiguityMatrix; // contiguity matrix
	
	private int vertexNumber;
		
	
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
	}
	
	public void printContiguityMatrix (PrintSource printSource) {
		if (printSource == PrintSource.CONSOLE) {
			for (int i = 0; i < vertexNumber; i++) {
				for (int j = 0; j < vertexNumber; j++) {
					
					System.out.print(contiguityMatrix[i][j] + "  ");
				}
				System.out.println();
			}
		}
	}

	/**
     * Calculate a min cost from start vertex to each vertex in graph with positive weights, using Dijkstra Algorithm with O(vertexNumber^2) 
     * 
     * @param startVertex - index of start vertex
     */
	
	/* Dijkstra Algorithm O(vertexNumber^2) */
	private int[] dijkstraAlgorithm(int startVertex) {
		
		List <Integer> visitedVertexes = new ArrayList<>(); // посещенные вершины
		int [] minDistances = new int[vertexNumber]; // кратчайшие расстояния до i-вершины из startVertex-вершины             
		
		//minDistances[startVertex] = 0;
		for (int i = 0; i < vertexNumber; i++) {
			if (i != startVertex) {
				minDistances[i] = INF;
			}
			
		}
		
		while (visitedVertexes.size() <= vertexNumber-1) {
			int minD = INF;
			int v = -1;
			for (int i = 0; i < vertexNumber; i++) {
				if (!visitedVertexes.contains(i)) {
					if (minDistances[i] < minD) {
						minD = minDistances[i];
						v = i;
					}
				}
			}
			visitedVertexes.add(v);
			
			for (int i = 0; i < vertexNumber; i++) {
				if (!visitedVertexes.contains(i) && contiguityMatrix[v][i] != 0) {
					if (minDistances[i] > minDistances[v] + contiguityMatrix[v][i]) {
						minDistances[i] = minDistances[v] + contiguityMatrix[v][i];
					}
				}
			}
		}
		
		return minDistances;
	}
	
	
	public int getMinCostBetweenVertices (int startVertex, int endVertex) {
		int [] distancesToStartVertex = dijkstraAlgorithm(startVertex);
		return distancesToStartVertex[endVertex];
	}

}
