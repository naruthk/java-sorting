//Naruth Kongurai (1429760)
//CSE 373 AB
//TA: Raquel Van Hofwegen
//03/10/17


package sorting;

import java.util.Comparator;

/**
 * Class full of static sorting methods. Used to sort Integers.
 * 
 * TODO: Implement mergeSort() and selectionSort().
 * 
 * insertionSort is implemented for you as an example.
 * 
 * @author pattersp
 *
 */

public class IntegerSorter {
    /**
     * Sorts the given array of integers in ascending order according to the
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
     *            the integers to sort
     * @param comparator
     *            The comparator the will be used to compare two integers.
     */
    public static void mergeSort(
    		Integer[] array, Comparator<Integer> comparator) {
        Integer[] tempArray = new Integer[array.length];
        // Helper method that will split the array into halves and then mergesort
        mergeSortHelper(array, tempArray, comparator, 0, array.length - 1);
    }
    
    /*
     * Internal method that recursively sorts the array into chunks that each
     * time splits in half and sorts.
     * @param array the integers to sort
     * @param tempArray the temporary array used to store the final integers
     * @param comparator the comparator that will be used to compare two integers
     * @param left the position of the index 
     * @param right the position of the index
     */
    private static void mergeSortHelper(Integer[] array, Integer[] tempArray, 
    		Comparator<Integer> comparator, int left, int right) {
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
     * @param array the integers to sort
     * @param tempArray the temporary array used to store the final integers
     * @param comparator the comparator that will be used to compare two integers
     * @param leftPosition the position of the left index 
     * @param rightPosition the position of the right index
     * @param rightEnd the position of the right element index in the array
     */
    private static void mergeSortFinalize(Integer[] array, Integer[] tempArray, 
    		Comparator<Integer> comparator, int leftPosition, int rightPosition, int rightEnd) {
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
    	
    	for (int i = 0; i < elementsCount; i++) {
    		array[rightEnd] = tempArray[rightEnd];
    		rightEnd--;
    	}
    }

    /**
     * Sort the array of integers in ascending order according to the comparator
     * using selection sort.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the array of integer that will be sorted.
     * @param comparator
     *            The comparator the will be used to compare two integers.
     */
    public static void selectionSort(Integer[] array,
            Comparator<Integer> comparator) {
        for (int outerIndex = 0; outerIndex < array.length - 1; outerIndex++) {
        	int nextSmallestIndex = outerIndex;
        	for (int innerIndex = outerIndex + 1; innerIndex < array.length; innerIndex++) {
        		if (comparator.compare(array[innerIndex], array[nextSmallestIndex]) < 0) {
        			nextSmallestIndex = innerIndex;
        		}
        	}
        	int nextSmallestInt = array[nextSmallestIndex];
        	array[nextSmallestIndex] = array[outerIndex];
        	array[outerIndex] = nextSmallestInt;
        }
    }
    

    /**
     * Sort the array of integers in ascending order according to the comparator
     * using insertion sort.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the array of integers that will be sorted.
     * @param comparator
     *            The comparator the will be used to compare two integer.
     */
    public static void insertionSort(Integer[] array,
            Comparator<Integer> comparator) {
        for (int outerIndex = 1; outerIndex < array.length; outerIndex++) {
            Integer currentInt = array[outerIndex];
            int innerIndex = outerIndex - 1;
            while (innerIndex >= 0
                    && comparator.compare(currentInt, array[innerIndex]) < 0) {
                array[innerIndex + 1] = array[innerIndex];
                innerIndex--;
            }
            array[innerIndex + 1] = currentInt;
        }
    }
}
