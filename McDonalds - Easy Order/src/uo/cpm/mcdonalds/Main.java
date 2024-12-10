package uo.cpm.mcdonalds;

import java.awt.EventQueue;

import javax.swing.UIManager;

import uo.cpm.mcdonalds.service.McDonalds;
import uo.cpm.mcdonalds.ui.MainWindow;

public class Main {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	final McDonalds mcDonalds = new McDonalds();
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    UIManager.setLookAndFeel(
			    "javax.swing.plaf.nimbus.NimbusLookAndFeel");
		    MainWindow frame = new MainWindow(mcDonalds);
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});

    }
}
////	int[][] matrix = { { 1, 2, 2, 2, 3 }, { 4, 5, 5, 5, 6 },
////		{ 7, 8, 8, 9, 9 }, { 9, 9, 9, 9, 11 }, { 12, 13, 13, 13, 14 } };
////
////	System.out.println("Original Matrix:");
////	printMatrix(matrix);
////
////	int[][] res = removeConsecutivePairsMikel(matrix);
////
////	System.out.println("\nModified Matrix:");
////	printMatrix(res);
//
//    int[][] matrix = { { 1, 1, 8, 4, 5 }, { 1, 3, 4, 5, 1 }, { 1, 4, 5, 1, 2 },
//	    { 4, 5, 1, 2, 3 }, { 5, 1, 2, 3, 4 } };
//
//    printMatrix(matrix);
//
//	removeConsecutiveNumbers(matrix);
//
//	System.out.println("\n NEW");
//	printMatrix(matrix);
//    }
//
//    public static void removeConsecutiveNumbersNuevo(int[][] matrix) {
//	int rows = matrix.length;
//	int cols = matrix[0].length;
//
//	// Check for consecutive numbers forming a cross pattern
//	for (int row = 0; row < rows; row++) {
//	    for (int col = 0; col < cols; col++) {
//		int current = matrix[row][col];
//
//		if (current != 0) {
//		    boolean horizontalCollide = false;
//		    boolean verticalCollide = false;
//
//		    // Check horizontal collision to the right
//		    if (col + 2 < cols) {
//			int next1 = matrix[row][col + 1];
//			int next2 = matrix[row][col + 2];
//			horizontalCollide = (current == next1)
//				&& (current == next2);
//		    }
//
//		    // Check horizontal collision to the left
//		    if (col - 2 >= 0) {
//			int prev1 = matrix[row][col - 1];
//			int prev2 = matrix[row][col - 2];
//			horizontalCollide = horizontalCollide
//				|| ((current == prev1) && (current == prev2));
//		    }
//
//		    // Check vertical collision downwards
//		    if (row + 2 < rows) {
//			int next1 = matrix[row + 1][col];
//			int next2 = matrix[row + 2][col];
//			verticalCollide = (current == next1)
//				&& (current == next2);
//		    }
//
//		    // Check vertical collision upwards
//		    if (row - 2 >= 0) {
//			int prev1 = matrix[row - 1][col];
//			int prev2 = matrix[row - 2][col];
//			verticalCollide = verticalCollide
//				|| ((current == prev1) && (current == prev2));
//		    }
//
//		    if (horizontalCollide && verticalCollide) {
//			matrix[row][col] = 0;
//		    }
//		}
//	    }
//	}
//    }
//
//    public static void removeConsecutiveNumbers(int[][] matrix) {
//	int rows = matrix.length;
//	int cols = matrix[0].length;
//
//	Set<Integer> toRemove = new HashSet<Integer>();
//
//	// Check for horizontal collisions
//	for (int row = 0; row < rows; row++) {
//	    for (int col = 0; col < cols - 2; col++) {
//		int current = matrix[row][col];
//		int next1 = matrix[row][col + 1];
//		int next2 = matrix[row][col + 2];
//		if (current == next1 && current == next2) {
//		    toRemove.add(current);
//		}
//	    }
//	}
//
//	// Check for vertical collisions
//	for (int col = 0; col < cols; col++) {
//	    for (int row = 0; row < rows - 2; row++) {
//		int current = matrix[row][col];
//		int next1 = matrix[row + 1][col];
//		int next2 = matrix[row + 2][col];
//		if (current == next1 && current == next2) {
//		    toRemove.add(current);
//		}
//	    }
//	}
//
//	// Remove collided consecutive numbers
//	for (int row = 0; row < rows; row++) {
//	    for (int col = 0; col < cols; col++) {
//		if (toRemove.contains(matrix[row][col])) {
//		    matrix[row][col] = 0;
//		}
//	    }
//	}
//    }
//
//    //////////////////// 77
//    public static int[][] removeConsecutiveCross(int[][] matrix) {
//	int[][] result = new int[5][5];
//
//	// Iterate over the rows
//	for (int i = 0; i < 5; i++) {
//	    // Iterate over the columns
//	    for (int j = 0; j < 5; j++) {
//		// Check if the current position is part of a cross
//		if (isConsecutiveCross(matrix, i, j)) {
//		    // Mark the cross positions as removed (set to 0)
//		    result[i][j] = 0;
//		    result[i - 1][j] = 0;
//		    result[i + 1][j] = 0;
//		    result[i][j - 1] = 0;
//		    result[i][j + 1] = 0;
//		} else {
//		    // Copy the original value to the result matrix
//		    result[i][j] = matrix[i][j];
//		}
//	    }
//	}
//
//	return result;
//    }
//
//    private static boolean isConsecutiveCross(int[][] matrix, int row,
//	    int col) {
//	// Check if the current position is within the bounds of the matrix
//	if (row >= 1 && row <= 3 && col >= 1 && col <= 3) {
//	    int center = matrix[row][col];
//
//	    // Check if the current position is part of a cross
//	    return matrix[row - 1][col] == center
//		    && matrix[row + 1][col] == center
//		    && matrix[row][col - 1] == center
//		    && matrix[row][col + 1] == center;
//	}
//
//	return false;
//    }
//
//    public static int[][] rotateMatrix(int[][] matrix) {
//	int size = matrix.length;
//	int[][] rotatedMatrix = new int[size][size];
//
//	for (int i = 0; i < size; i++) {
//	    for (int j = 0; j < size; j++) {
//		rotatedMatrix[j][size - 1 - i] = matrix[i][j];
//	    }
//	}
//
//	return rotatedMatrix;
//    }
//
//    public static int[][] removeConsecutivePairsMikel(int[][] matrix) {
//	for (int i = 0; i < 5; i++) {
//	    for (int j = 0; j < 5; j++) {
//		// 5 consecutive pairs
//		if (j + 4 < 5 && matrix[i][j] == matrix[i][j + 1]
//			&& matrix[i][j] == matrix[i][j + 2]
//			&& matrix[i][j] == matrix[i][j + 3]
//			&& matrix[i][j] == matrix[i][j + 4]) {
//
//		    matrix[i][j] = 0;
//		    matrix[i][j + 1] = 0;
//		    matrix[i][j + 2] = 0;
//		    matrix[i][j + 3] = 0;
//		    matrix[i][j + 4] = 0;
//
//		    j += 4;
//		}
//
//		// 4 consecutive pairs
//		else if (j + 3 < 5 && matrix[i][j] == matrix[i][j + 1]
//			&& matrix[i][j] == matrix[i][j + 2]
//			&& matrix[i][j] == matrix[i][j + 3]) {
//
//		    matrix[i][j] = 0;
//		    matrix[i][j + 1] = 0;
//		    matrix[i][j + 2] = 0;
//		    matrix[i][j + 3] = 0;
//
//		    j += 3;
//		}
//
//		// 3 consecutive pairs
//		else if (j + 2 < 5 && matrix[i][j] == matrix[i][j + 1]
//			&& matrix[i][j] == matrix[i][j + 2]) {
//		    matrix[i][j] = 0;
//		    matrix[i][j + 1] = 0;
//		    matrix[i][j + 2] = 0;
//
//		    j += 2; // Skip to the next number possible pair
//		}
//	    }
//	}
//
//	return matrix;
//    }
//
//    public static void printMatrix(int[][] matrix) {
//	int rows = matrix.length;
//	int cols = matrix[0].length;
//
//	for (int i = 0; i < rows; i++) {
//	    for (int j = 0; j < cols; j++) {
//		System.out.print(matrix[i][j] + " ");
//	    }
//
//	    System.out.println();
//	}
//    }
