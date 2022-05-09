import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        Random rand = new Random();
        int numElements = 10;
        int arr[] = new int[numElements];
        int i;
        int j;

        for (i = 0; i < numElements; i++) { //populating array to sort
            arr[i] = rand.nextInt(10);
        }
        my_quicksort(arr); //call to sort array being populated

        for(j = 0; j < numElements; j++) { // printing array to sort
            System.out.println(arr[j] + " ");
        }
        System.out.println("quicksorted, descending order");
    }

    public static void my_quicksort(int[] array) {
        QuickSort sort = new QuickSort();
        sort.quicksort_helper(array, 0, array.length - 1); //call to helper function to recursively sort
    }

    private void quicksort_helper(int[] arr, int low, int high) {
        if (low < high) {
            int index = partition(arr, low, high); // recursively partitioning the arrays

            quicksort_helper(arr, low, index - 1); //recursively adding to left side subarray
            quicksort_helper(arr, low + 1, high); // recursively adding to right side subarray
        }
    }

    private int partition(int[] arr, int low, int high) {
        if (arr.length < 2) { // check to see if array has more than two elements
            System.out.println("less than two elements, cannot sort");
            System.exit(0);
        }
        int pivot = arr[low]; // setting pivot = to first value
        int i = low; //i now represents the left pointer
        for (int j = low + 1; j <= high; j++) { // j now represents the right pointer
            if (arr[j] > pivot) { // swap if item after first is greater - creates descending order
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++; //incrementing left pointer
            }
        }
        return i; //return the index
    }

}
/* recurrence relation: a, b, c
T(n) = 2T(n/2) + n
a = 2; b = 2; c = 1;

log2(2) = 1
since logb(a) = c, recursive calls nor f(n) dominate
t(n) = theta(n log n)
*/