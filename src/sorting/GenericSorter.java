//Naruth Kongurai (1429760)
//CSE 373 AB
//TA: Raquel Van Hofwegen
//03/10/17

package sorting;

import java.util.Comparator;

public class GenericSorter {

	/**
	 * Sorts the given array of objects in ascending order according to the
	 * comparator using mergesort. You may create as many private helper
	 * functions as you wish to implement this method.
	 * 
	 * A note about ascending order:
	 * 
	 * When the method is finished, it should be true that:
	 * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
	 * array.length.
	 * 
	 * @param array
	 *            the objects to sort
	 * @param comparator
	 *            The comparator the will be used to compare two objects.
	 */
	public static <E> void mergeSort(E[] array, Comparator<E> comparator) {
		E[] tempArray = (E[]) new Object[array.length];
		// Helper method that will split the array into halves and then mergesort
		mergeSortHelper(array, tempArray, comparator, 0, array.length - 1);
	}

	/*
	 * Internal method that recursively sorts the array into chunks that each
	 * time splits in half and sorts.
	 * @param array the objects to sort
	 * @param tempArray the temporary array used to store the final array of objects
	 * @param comparator the comparator that will be used to compare two objects
	 * @param left the position of the index 
	 * @param right the position of the index
	 */
	private static <E> void mergeSortHelper(E[] array, E[] tempArray, 
			Comparator<E> comparator, int left, int right) {
		if (left < right) {
			int middle = (left + right) / 2;	
			// split and sort the left side
			mergeSortHelper(array, tempArray, comparator, left, middle);	
			// split and sort the right side
			mergeSortHelper(array, tempArray, comparator, middle + 1, right);	
			// combine and sort both sides together
			mergeSortFinalize(
					array, tempArray, comparator, left, middle + 1, right);
		}
	}

	/*
	 * Internal method that merges all the values in the array while sorting
	 * them in ascending order.
	 * @param array the objects to sort
	 * @param tempArray the temporary array used to store the final array of objects
	 * @param comparator the comparator that will be used to compare two objects
	 * @param leftPosition the position of the left index 
	 * @param rightPosition the position of the right index
	 * @param rightEnd the position of the right element index in the array
	 */
	private static <E> void mergeSortFinalize(E[] array, E[] tempArray, 
			Comparator<E> comparator, int leftPosition, int rightPosition, int rightEnd) {
		// Find position index
		int leftEnd = rightPosition - 1;
		int tempPosition = leftPosition;
		int elementsCount = rightEnd - leftPosition + 1;

		while (leftPosition <= leftEnd && rightPosition <= rightEnd) {
			if (comparator.compare(array[leftPosition], array[rightPosition]) <= 0) {
				tempArray[tempPosition++] = array[leftPosition++];
			} else {
				tempArray[tempPosition++] = array[rightPosition++];
			}
		}

		while (leftPosition <= leftEnd) {
			tempArray[tempPosition++] = array[leftPosition++];
		}

		while (rightPosition <= rightEnd) {
			tempArray[tempPosition++] = array[rightPosition++];
		}

		// Copy over all the elements in the temporaryArray to the original array
		for (int i = 0; i < elementsCount; i++) {
			array[rightEnd] = tempArray[rightEnd];
			rightEnd--;
		}
	}

	/**
	 * Sort the array of objects in ascending order using selection sort.
	 * 
	 * A note about ascending order:
	 * 
	 * When the method is finished, it should be true that:
	 * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
	 * array.length.
	 * 
	 * @param array
	 *            the array of packets that will be sorted.
	 * @param comparator
	 *            The comparator the will be used to compare two packets.
	 */
	public static <E> void selectionSort(E[] array,
			Comparator<E> comparator) {
		for (int outerIndex = 0; outerIndex < array.length - 1; outerIndex++) {
			int nextSmallestIndex = outerIndex;
			for (int innerIndex = outerIndex + 1; innerIndex < array.length; innerIndex++) {
				if (comparator.compare(array[innerIndex], array[nextSmallestIndex]) < 0) {
					nextSmallestIndex = innerIndex;
				}
			}
			E nextSmallestObject = array[nextSmallestIndex];
			array[nextSmallestIndex] = array[outerIndex];
			array[outerIndex] = nextSmallestObject;
		}
	}
}
