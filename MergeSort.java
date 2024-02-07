package project2;

/**
 * @title MergeSort
 * @author johnbotonakis (with ChatGPT used for code commenting)
 * @help With help from (https://www.baeldung.com/java-merge-sort)
 * @due 12/11/23
 * @desc The MergeSort class contains methods for performing merge sort on an
 *       array.
 */
public class MergeSort {

    /**
     * Sorts an array using the merge sort algorithm.
     *
     * @param arrayToSort The array to be sorted.
     * @param arrayLength The length of the array.
     */
    public static void Sort(String[] arrayToSort, int arrayLength) {
        if (arrayLength < 2) {
            return;
        }

        int mid = arrayLength / 2;
        String[] l = new String[mid];
        String[] r = new String[arrayLength - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = arrayToSort[i];
        }
        for (int i = mid; i < arrayLength; i++) {
            r[i - mid] = arrayToSort[i];
        }

        Sort(l, mid);
        Sort(r, arrayLength - mid);

        merge(arrayToSort, l, r, mid, arrayLength - mid);
    }

    /**
     * Merges two subarrays into one array.
     *
     * @param mergedArray   The array to merge into.
     * @param leftSubArray  The left subarray to be merged.
     * @param rightSubArray The right subarray to be merged.
     * @param leftSize      The size of the left subarray.
     * @param rightSize     The size of the right subarray.
     */
    public static void merge(String[] mergedArray, String[] leftSubArray, String[] rightSubArray, int leftSize,
            int rightSize) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftSize && j < rightSize) {
            if (leftSubArray[i].compareTo(rightSubArray[j]) <= 0) {
                mergedArray[k++] = leftSubArray[i++];
            } else {
                mergedArray[k++] = rightSubArray[j++];
            }
        }

        while (i < leftSize) {
            mergedArray[k++] = leftSubArray[i++];
        }

        while (j < rightSize) {
            mergedArray[k++] = rightSubArray[j++];
        }
    }

}
