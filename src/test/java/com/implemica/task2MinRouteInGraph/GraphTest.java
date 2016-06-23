package com.implemica.task2MinRouteInGraph;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GraphTest {
	
	@Test
	public void testGetMinCostBetweenVertices() {
		Graph graph = new Graph (4);
		graph.setCost(0, 1, 1);
		graph.setCost(0, 2, 3);
		graph.setCost(1, 0, 1);
		graph.setCost(1, 2, 1);
		graph.setCost(1, 3, 4);
		graph.setCost(2, 0, 3);
		graph.setCost(2, 1, 1);
		graph.setCost(2, 3, 1);
		graph.setCost(3, 1, 4);
		graph.setCost(3, 2, 1);
		
		assertEquals(3, graph.getMinCostBetweenVertices(0, 3));
		assertEquals(2, graph.getMinCostBetweenVertices(2, 0));
	}
	
	
	@Test
	public void testGetMinCostBetweenVertices2() {
		Graph graph = new Graph (4);
		graph.setCost(0, 1, 100);
		graph.setCost(0, 2, 100);
		graph.setCost(1, 0, 200);
		graph.setCost(1, 2, 200);
		graph.setCost(1, 3, 200);
		graph.setCost(2, 0, 3);
		graph.setCost(2, 1, 1);
		graph.setCost(2, 3, 1);
		graph.setCost(3, 1, 4);
		graph.setCost(3, 2, 1);
		
		assertEquals(101, graph.getMinCostBetweenVertices(0, 3));
		assertEquals(4, graph.getMinCostBetweenVertices(3, 0));
	}

}
