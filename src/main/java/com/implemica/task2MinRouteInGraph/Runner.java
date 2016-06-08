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
	



	public static void main(String[] args) {

		boolean isAttemptsNumberCorrect = false;

		do {

			System.out.println(MessageConstants.MSG_TESTS_NUMBER_INPUT);

			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				int attemptsNumber = Integer.parseInt(br.readLine());

				if (attemptsNumber > 0 && attemptsNumber <= MaxNumberConstants.MAX_TEST_NUMBER) {
					isAttemptsNumberCorrect = true;

					new InputHandler(br).handleVertices(attemptsNumber);

				} else {

					System.out.println(MessageConstants.MSG_INVALID_TEST_NUMBER_INPUT);

				}

			} catch (NumberFormatException e) {

				System.out.println(MessageConstants.MSG_INVALID_NUMBER);

			} catch (IOException e) {

				System.out.println(MessageConstants.MSG_IO_ERROR);
			}

		} while (!isAttemptsNumberCorrect);

	}
}
