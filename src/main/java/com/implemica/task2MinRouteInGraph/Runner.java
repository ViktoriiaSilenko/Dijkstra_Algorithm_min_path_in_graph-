package com.implemica.task2MinRouteInGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Viktoriia Silenko
 */

public class Runner {

	private static final int MAX_TEST_NUMBER = 10;
	private static final int MAX_VERTEX_NUMBER = 10000;
	private static final int MAX_PATH_NUMBER = 100;

	private static final String MSG_TESTS_NUMBER_INPUT = "Enter number of tests (attempts). It must be positive number <= "
			+ MAX_TEST_NUMBER;
	private static final String MSG_INVALID_TEST_NUMBER_INPUT = "Your input does not a positive number or greater than "
			+ MAX_TEST_NUMBER + ". It must be positive number <= " + MAX_TEST_NUMBER;

	private static final String MSG_VERTEX_NUMBER_INPUT = "Enter the number of cities <= " + MAX_VERTEX_NUMBER;
	private static final String MSG_INVALID_VERTEX_NUMBER_INPUT = "Your input does not a positive number or greater than " + MAX_VERTEX_NUMBER + ". It must be positive number <= " + MAX_VERTEX_NUMBER;

	private static final String MSG_IO_EXCEPTION = "Error with reading your input";
	private static final String MSG_NUMBER_FORMAT_EXCEPTION = "Your input does not a number.";
	
	private static final String MSG_CITY_NAME_INPUT = "Enter city name";
	private static final String MSG_NEIGHBOURS_NUMBER_INPUT = "Enter number of neighbours of city. It should be positive number <= %s ";
	
	private static final String MSG_NR_COST_INPUT = "Enter nr cost (with space) , where  nr - index of a city connected to %s  (the index of the first city is 1, index should be >= 1 and <= %s) and [cost - positive integer number of the transportation cost]";

	private static final String MSG_INVALID_NR_COST_INPUT = "Index should be >= 1 and <= %s and cost should be > 0";
	private static final String MSG_INVALID_PAIR_NR_COST_INPUT = "It should be pair: nr cost";
	
	private static final String MSG_INVALID_NEIGHBOURS_NUMBER_INPUT = "Number of neighbours of city should be positive number <= %s";
	private static final String MSG_PATH_NUMBER_INPUT = "Enter the number of paths to find. It is positive number <= " + MAX_PATH_NUMBER;
	
	private static final String MSG_SRC_DEST_CITY_INPUT = "Enter NAME1 NAME2 (with space) , where NAME1 - source city name, NAME2 - destination city name";
	private static final String MSG_INCORRECT_CITIES_INPUT = "There are no such cities";
	private static final String MSG_INVALID_PAIR_SRC_DEST_INPUT = "Please enter two city with space";
	
	private static final String MSG_INVALID_PATH_NUMBER_INPUT = "Number of path should be positive number <= " + MAX_PATH_NUMBER;
	private static final String MSG_COST_OUTPUT = "Cost = %s";
	
	public static void main(String[] args) {

		boolean isAttemptsNumberCorrect = false;

		do {

			System.out.println(MSG_TESTS_NUMBER_INPUT);

			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				int attemptsNumber = Integer.parseInt(br.readLine());

				if (attemptsNumber > 0 && attemptsNumber <= MAX_TEST_NUMBER) {
					isAttemptsNumberCorrect = true;

					for (int attemptsIndex = 0; attemptsIndex < attemptsNumber; attemptsIndex++) {

						System.out.println(MSG_VERTEX_NUMBER_INPUT);

						try {

							int vertexNumber = Integer.parseInt(br.readLine());
							if (vertexNumber > 0 && vertexNumber <= MAX_VERTEX_NUMBER) {

								Graph graph = new Graph(vertexNumber);
								Map<String, Integer> vertices = new HashMap<>();
								Map<Integer, Integer> sourceDest = new HashMap<>();

								for (int vertexIndex = 0; vertexIndex < vertexNumber; vertexIndex++) {

									System.out.println(MSG_CITY_NAME_INPUT);

									String cityName = br.readLine();
									vertices.put(cityName, vertexIndex);

									System.out.println(String.format(MSG_NEIGHBOURS_NUMBER_INPUT, vertexNumber - 1));
									
									int vertexNeighboursNumber = Integer.parseInt(br.readLine());
									if (vertexNeighboursNumber > 0 && vertexNeighboursNumber <= vertexNumber - 1) {
										

										for (int vertexNeighboursIndex = 0; vertexNeighboursIndex < vertexNeighboursNumber; vertexNeighboursIndex++) {
											System.out.println(String.format(MSG_NR_COST_INPUT, cityName, vertexNumber));

											String[] nrCost = br.readLine().split("\\s+");
											if (nrCost.length == 2) {
												int index = Integer.parseInt(nrCost[0]);
												int cost = Integer.parseInt(nrCost[1]);

												if (index >= 1 && index <= vertexNumber && cost > 0) {
													graph.setCost(vertexIndex, index - 1, cost);

												} else {
													System.out.println(String.format(MSG_INVALID_NR_COST_INPUT, vertexNumber));
													vertexNeighboursIndex--;
													continue;
												}
											} else {
												System.out.println(MSG_INVALID_PAIR_NR_COST_INPUT);
												vertexNeighboursIndex--;
												continue;
											}

										}

									} else {
										System.out.println(String.format(MSG_INVALID_NEIGHBOURS_NUMBER_INPUT, vertexNumber - 1));
										vertexIndex--;
										continue;
									}
									

								}

								boolean isPathNumberCorrect = false;
								do {
									System.out.println(MSG_PATH_NUMBER_INPUT);
									int pathNumber = Integer.parseInt(br.readLine());
									if (pathNumber > 0 && pathNumber <= MAX_PATH_NUMBER) {
										isPathNumberCorrect = true;

										

										for (int pathIndex = 0; pathIndex < pathNumber; pathIndex++) {

											System.out.println( MSG_SRC_DEST_CITY_INPUT);
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
															System.out.println(MSG_INCORRECT_CITIES_INPUT);
															pathIndex--;
															break;
														}
													}

												} else {
													System.out.println(MSG_INVALID_PAIR_SRC_DEST_INPUT);
													pathIndex--;
													break;
												}
											} while (!isSourceDestinationCorrect);
											
										}

									} else {
										System.out.println(MSG_INVALID_PATH_NUMBER_INPUT);

									}
								} while (!isPathNumberCorrect);

								
								// graph.printContiguityMatrix(); //  for testing
								Object[] sourcesIndexes = sourceDest.keySet().toArray();
								for (Object sourcesIndex : sourcesIndexes) {
									System.out.println( String.format(MSG_COST_OUTPUT, graph.getMinCostBetweenVertices((int) sourcesIndex,
											(int) sourceDest.get(sourcesIndex))) ); // result
								}

							} else {
								System.out.println(MSG_INVALID_VERTEX_NUMBER_INPUT);
								continue;
							}

						} catch (NumberFormatException e) {

							System.out.println(MSG_NUMBER_FORMAT_EXCEPTION);
							continue;

						} catch (IOException e) {

							System.out.println(MSG_IO_EXCEPTION);
							continue;
						}

					}

				} else {

					System.out.println(MSG_INVALID_TEST_NUMBER_INPUT);

				}

			} catch (NumberFormatException e) {

				System.out.println(MSG_NUMBER_FORMAT_EXCEPTION);

			} catch (IOException e) {

				System.out.println(MSG_IO_EXCEPTION);
			}

		} while (!isAttemptsNumberCorrect);

	}
}
