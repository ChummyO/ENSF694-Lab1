import java.util.Arrays;
import java.util.Scanner;

public class Searching {
	
	//Linear Search
	
	static int linearSearch(int[] array, int key)
	{
		for(int i = 0; i < array.length; i ++)
		{
			if(array[i] == key)
				return i; //key FOUND and index returned
		}
	return -1; //key NOT FOUND and -1 returned
	}
	
    //	Interpolation Search
	static int InterpolationSearchIterative(int[] array, int key)
	{
	int low = 0, mid, pos, high = array.length-1;
	pos = (key - array[low]) / (array[high] - array[low]);
	mid = low + (((high - low) * pos));

	while (low <= high)
	{
		mid = low + (((high - low) * pos));
		if(key < array[mid])
			high = mid - 1;
		else if(array[mid] < key)
			low = mid + 1;
		else
			return mid; //key FOUND and index returned
	}
	return -1; //key NOT FOUND and -1 returned
	}
	
	// Improved Linear Search
	
    public static int SentinelLinearSearch(int[] arr, int key) {
        int n = arr.length;
        int lastElement = arr[n - 1];  // Store the last element
        arr[n - 1] = key;  // Set sentinel value at the end

        int i = 0;
        while (arr[i] != key) {
            i++;
        }

        arr[n - 1] = lastElement;  // Restore the last element

        if (i < n - 1 || arr[n - 1] == key) {
            return i;  // Found the target, return the index
        } else {
            return -1;  // Target element not found
        }
    }
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of elements in the array: ");
        int input1 = scanner.nextInt();
        int[] array = new int[input1];
        System.out.println("Enter the elements in the array:");

        for (int i = 0; i < input1; i++) {
            array[i] = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("Enter the search key: ");
        int input3 = scanner.nextInt();
        scanner.close();
        
        // sort the array
        Arrays.sort(array);
        
        // measure performance
        long startTimeLinear = System.nanoTime();
        int result1 = linearSearch(array, input3);
        long endTimeLinear = System.nanoTime();
        long linearSearchTime = endTimeLinear - startTimeLinear;
        
        long startTimeInterpolation = System.nanoTime();
        int result2 = InterpolationSearchIterative(array, input3);
        long endTimeInterpolation = System.nanoTime();
        long InterpolationSearchTime = endTimeInterpolation - startTimeInterpolation;
        
        long startTimeSentinelLinear = System.nanoTime();
        SentinelLinearSearch(array, input3);
        long endTimeSentinelLinear = System.nanoTime();
        long SentinelLinearSearchTime = endTimeSentinelLinear - startTimeSentinelLinear;
        
        System.out.println("\nUsing Linear Search: ");
        if (result1 == -1) {
            System.out.println("Search key NOT FOUND");
        } else {
            System.out.println("Search key FOUND at index " + result1);
        }
        
        System.out.println("\nUsing Interpolation Search: ");
        if (result2 == -1) {
            System.out.println("Search key NOT FOUND");
        } else {
            System.out.println("Search key FOUND at index " + result2);
        }
       
        System.out.println("\n**********Comparing running times*************");
        System.out.println("Linear Search Time: " + linearSearchTime + " nanoseconds");

        System.out.println("Interpolation Search Time: " + InterpolationSearchTime + " nanoseconds");
        
        System.out.println("\nThe Interpolation Search is better than the Linear search because it "
        		+ "divides the array by a mid value for each iteration\n, thereby making it faster");
        
        double percentageImprovement = Math.abs(((double)(SentinelLinearSearchTime - linearSearchTime)/linearSearchTime)* 100);

        System.out.println("\nImproved Linear Search Time: " + SentinelLinearSearchTime + " nanoseconds " 
        				+ "which is " + percentageImprovement + "%" + " better than Linear Search");
        System.out.println("\nLOGIC: I replaced the last value of the array with my target value as a Sentinel\n"
        		+ "to act as a marker for the end of the array which eliminated the explicit check present in the Linear Search");
    }
}
