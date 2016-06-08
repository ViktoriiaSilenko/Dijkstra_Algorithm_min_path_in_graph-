// Constant utility class
package com.implemica.task2MinRouteInGraph;

public class MessageConstants {
	
	private MessageConstants() { }  // Prevents instantiation
	
	public static final String MSG_TESTS_NUMBER_INPUT = "Enter number of tests (attempts). It must be positive number <= "
			+ MaxNumberConstants.MAX_TEST_NUMBER;
	public static final String MSG_INVALID_TEST_NUMBER_INPUT = "Your input does not a positive number or greater than "
			+ MaxNumberConstants.MAX_TEST_NUMBER + ". It must be positive number <= " + MaxNumberConstants.MAX_TEST_NUMBER;

	public static final String MSG_VERTEX_NUMBER_INPUT = "Enter the number of cities <= " + MaxNumberConstants.MAX_VERTEX_NUMBER;
	public static final String MSG_INVALID_VERTEX_NUMBER_INPUT = "Your input does not a positive number or greater than " + MaxNumberConstants.MAX_VERTEX_NUMBER + 
			". It must be positive number <= " + MaxNumberConstants.MAX_VERTEX_NUMBER;

	public static final String MSG_IO_ERROR = "Error with reading your input";
	public static final String MSG_INVALID_NUMBER = "Your input does not a number.";
	
	public static final String MSG_CITY_NAME_INPUT = "Enter city name";
	public static final String MSG_NEIGHBOURS_NUMBER_INPUT = "Enter number of neighbours of city. It should be positive number <= %s ";
	
	public static final String MSG_NR_COST_INPUT = "Enter nr cost (with space) , where  nr - index of a city connected to this city " + 
			" (the index of the first city is 1, index should be >= 1 and <= %s) and [cost - positive integer number of the transportation cost]";

	public static final String MSG_INVALID_NR_COST_INPUT = "Index should be >= 1 and <= %s and cost should be > 0";
	public static final String MSG_INVALID_PAIR_NR_COST_INPUT = "It should be pair: nr cost";
	
	public static final String MSG_INVALID_NEIGHBOURS_NUMBER_INPUT = "Number of neighbours of city should be positive number <= %s";
	public static final String MSG_PATH_NUMBER_INPUT = "Enter the number of paths to find. It is positive number <= " + MaxNumberConstants.MAX_PATH_NUMBER;
	
	public static final String MSG_SRC_DEST_CITY_INPUT = "Enter NAME1 NAME2 (with space) , where NAME1 - source city name, NAME2 - destination city name";
	public static final String MSG_INCORRECT_CITIES_INPUT = "There are no such cities";
	public static final String MSG_INVALID_PAIR_SRC_DEST_INPUT = "Please enter two city with space";
	
	public static final String MSG_INVALID_PATH_NUMBER_INPUT = "Number of path should be positive number <= " + MaxNumberConstants.MAX_PATH_NUMBER;
	public static final String MSG_COST_OUTPUT = "Cost = %s";

}
