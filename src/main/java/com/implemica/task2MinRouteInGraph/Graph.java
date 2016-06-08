package com.implemica.task2MinRouteInGraph;

/**
 * @author Viktoriia Silenko
 */

public class Graph {
	
	private static final int INF = 200000; // to identify that we have no rib between vertices 
	
	private int[][] contiguityMatrix; // contiguity matrix
	
	private int vertexNumber;
	
	
	
	public Graph(int vertexNumber) {
		
		this.vertexNumber = vertexNumber;
		
		contiguityMatrix = new int[vertexNumber][vertexNumber];
		for (int i = 0; i < vertexNumber; i++) {
			for (int j = 0; j < vertexNumber; j++) {
				if (i != j) {
					contiguityMatrix[i][j] = INF;
				}
			}
		}
	}
	
	public void setCost(int fromVertex, int toVertex, int cost) {
		contiguityMatrix[fromVertex][toVertex]= cost;
	}
	
	public void printContiguityMatrix () {
		for (int i = 0; i < vertexNumber; i++) {
			for (int j = 0; j < vertexNumber; j++) {
				
				System.out.print(contiguityMatrix[i][j] + "  ");
			}
			System.out.println();
		}
		
	}
	
	
	
	



	/**
     * Calculate a min cost from start vertex to each vertex in graph with positive weights, using Dijkstra Algorithm with O(vertexNumber^2) 
     * 
     * @param startVertex - index of start vertex
     */
	
	/* Dijkstra Algorithm O(vertexNumber^2) */
	private int[] dijkstraAlgorithm(int startVertex) {
		boolean[] usedVertex = new boolean[vertexNumber]; 
		int[] distancesToStartVertex = new int[vertexNumber]; // dist[v] = min distance between startVertex and v-vertex

		for (int i = 0; i < distancesToStartVertex.length; i++) { 
			distancesToStartVertex[i] = INF; // initially set all distances as infinity except of start
		}
		distancesToStartVertex[startVertex] = 0; // because it is distance to itself

		while (true) {
			int currNearestVertex = -1;
			for (int vertex = 0; vertex < vertexNumber; vertex++) 
				// select nearest by cost unused vertex
				if (!usedVertex[vertex] && distancesToStartVertex[vertex] < INF 
						&& (currNearestVertex == -1 || distancesToStartVertex[vertex] < distancesToStartVertex[currNearestVertex])) 
					currNearestVertex = vertex;
			if (currNearestVertex == -1)
				break; // all vertex are used => end
			usedVertex[currNearestVertex] = true; 
			for (int nv = 0; nv < vertexNumber; nv++)
				if (!usedVertex[nv] && contiguityMatrix[nv][currNearestVertex] < INF) // for all unused contiguous vertexes
					// recalculate distance
					distancesToStartVertex[nv] = Math.min(distancesToStartVertex[nv], distancesToStartVertex[currNearestVertex] + contiguityMatrix[nv][currNearestVertex]); 
		}
		return distancesToStartVertex;
	}
	
	public int getMinCostBetweenVertices (int startVertex, int endVertex) {
		int [] distancesToStartVertex = dijkstraAlgorithm(startVertex);
		return distancesToStartVertex[endVertex];
	}

}
