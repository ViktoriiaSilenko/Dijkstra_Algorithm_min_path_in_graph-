package com.implemica.task2MinRouteInGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InputHandler {

	private BufferedReader br;
	
	public InputHandler(BufferedReader br) {
		this.br = br;
	}

	private void handleNeighbours (Graph graph, int vertexIndex, int vertexNeighboursNumber) throws IOException {
		for (int vertexNeighboursIndex = 0; vertexNeighboursIndex < vertexNeighboursNumber; vertexNeighboursIndex++) {
			int vertexNumber = graph.getVertexNumber();
			System.out.println(String.format(MessageConstants.MSG_NR_COST_INPUT, vertexNumber));

			String[] nrCost = br.readLine().split("\\s+");
			if (nrCost.length == 2) {
				int index = Integer.parseInt(nrCost[0]);
				int cost = Integer.parseInt(nrCost[1]);

				if (index >= 1 && index <= vertexNumber && cost > 0) {
					graph.setCost(vertexIndex, index - 1, cost);

				} else {
					System.out.println(String.format(MessageConstants.MSG_INVALID_NR_COST_INPUT, vertexNumber));
					vertexNeighboursIndex--;
					continue;
				}
			} else {
				System.out.println(MessageConstants.MSG_INVALID_PAIR_NR_COST_INPUT);
				vertexNeighboursIndex--;
				continue;
			}

		}
	}
	
	private void handlePaths (Graph graph, Map<Integer, Integer> sourceDest, Map<String, Integer> vertices) throws NumberFormatException, IOException {
		boolean isPathNumberCorrect = false;
		do {
			System.out.println(MessageConstants.MSG_PATH_NUMBER_INPUT);
			int pathNumber = Integer.parseInt(br.readLine());
			if (pathNumber > 0 && pathNumber <= MaxNumberConstants.MAX_PATH_NUMBER) {
				isPathNumberCorrect = true;

				for (int pathIndex = 0; pathIndex < pathNumber; pathIndex++) {

					System.out.println(MessageConstants.MSG_SRC_DEST_CITY_INPUT);
					String[] sourceDestination = br.readLine().split("\\s+");
					boolean isSourceDestinationCorrect = false;
					do {
						if (sourceDestination.length == 2) {
							isSourceDestinationCorrect = true;
							if (!vertices.isEmpty()) {
								Integer sourceIndex = vertices.get(sourceDestination[0]);
								Integer destIndex = vertices.get(sourceDestination[1]);
								if ((sourceIndex != null) && (destIndex != null)) {
									sourceDest.put(sourceIndex, destIndex);

								} else {
									System.out.println(MessageConstants.MSG_INCORRECT_CITIES_INPUT);
									pathIndex--;
									break;
								}
							}

						} else {
							System.out.println(MessageConstants.MSG_INVALID_PAIR_SRC_DEST_INPUT);
							pathIndex--;
							break;
						}
					} while (!isSourceDestinationCorrect);
					
				}

			} else {
				System.out.println(MessageConstants.MSG_INVALID_PATH_NUMBER_INPUT);

			}
		} while (!isPathNumberCorrect);

		
		// graph.printContiguityMatrix(PrintSource.CONSOLE); //  for testing
		Object[] sourcesIndexes = sourceDest.keySet().toArray();
		for (Object sourcesIndex : sourcesIndexes) {
			System.out.println( String.format(MessageConstants.MSG_COST_OUTPUT, graph.getMinCostBetweenVertices((int) sourcesIndex,
					(int) sourceDest.get(sourcesIndex))) ); // result
		}
	}
	
	public void handleVertices (int attemptsNumber) {
		for (int attemptsIndex = 0; attemptsIndex < attemptsNumber; attemptsIndex++) {

			System.out.println(MessageConstants.MSG_VERTEX_NUMBER_INPUT);

			try {

				int vertexNumber = Integer.parseInt(br.readLine());
				if (vertexNumber > 0 && vertexNumber <= MaxNumberConstants.MAX_VERTEX_NUMBER) {

					Graph graph = new Graph(vertexNumber);
					Map<String, Integer> vertices = new HashMap<>();
					Map<Integer, Integer> sourceDest = new HashMap<>();

					for (int vertexIndex = 0; vertexIndex < vertexNumber; vertexIndex++) {

						System.out.println(MessageConstants.MSG_CITY_NAME_INPUT);

						String cityName = br.readLine();
						vertices.put(cityName, vertexIndex);

						System.out.println(String.format(MessageConstants.MSG_NEIGHBOURS_NUMBER_INPUT, vertexNumber - 1));
						
						int vertexNeighboursNumber = Integer.parseInt(br.readLine());
						if (vertexNeighboursNumber > 0 && vertexNeighboursNumber <= vertexNumber - 1) {
							
							handleNeighbours(graph, vertexIndex, vertexNeighboursNumber);

						} else {
							System.out.println(String.format(MessageConstants.MSG_INVALID_NEIGHBOURS_NUMBER_INPUT, vertexNumber - 1));
							vertexIndex--;
							continue;
						}
						

					}

					handlePaths(graph, sourceDest, vertices);

				} else {
					System.out.println(MessageConstants.MSG_INVALID_VERTEX_NUMBER_INPUT);
					continue;
				}

			} catch (NumberFormatException e) {

				System.out.println(MessageConstants.MSG_INVALID_NUMBER);
				continue;

			} catch (IOException e) {

				System.out.println(MessageConstants.MSG_IO_ERROR);
				continue;
			}

		}
	}

}
