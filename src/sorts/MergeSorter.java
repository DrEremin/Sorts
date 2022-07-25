package sorts;

import java.util.Random;

public class MergeSorter {
	
	private static long assigmentsCounter = 0;

	public static void main(String[] args) {
		
		int[][] arrays = new int[5][];
        Random random = new Random();
        long sum;
        long result;
        System.out.printf("Merge sort:%13s%15s%18s", "best", "middle", "worst\n\n");
        
        for (int n = 10, k = 0; n < 100001; n *= 10, k++) {
        	System.out.print("n = " + n + ": ");
        	sum = 0;
        	arrays[k] = new int[n];
        	for (int i = 0; i < arrays[k].length; i++) {
        		arrays[k][i] = i;
        	}
        	sort(arrays[k]);
        	result = resetCounter();
        	System.out.printf("%15d ", result);
        	sum += result;
        	
        	for (int i = 0; i < arrays[k].length; i++) {
        		arrays[k][i] = random.nextInt(20) - 5;
        	}
        	sort(arrays[k]);
        	result = resetCounter();
        	System.out.printf("%15d ", result);
        	sum += result;
        	
        	for (int i = arrays[k].length - 1, j = 0; j < arrays[k].length; i--, j++) {
        		arrays[k][j] = i;
        	}
        	sort(arrays[k]);
        	result = resetCounter();
        	System.out.printf("%15d ", result);
        	sum += result;
        	System.out.printf("\nAverage: %d\n\n", sum / 3);
        }
	}
	
	private static int[] mergeSort(int[] arr, int sIdx, int eIdx) {
		
		int[] res = { sIdx, eIdx };
		int leftEIdx = sIdx + (eIdx - sIdx) / 2;
	
		assigmentsCounter += 2;
		if (eIdx - sIdx == 0) { return res; }
		merge(arr, 
			  mergeSort(arr, sIdx, leftEIdx), 
			  mergeSort(arr, leftEIdx + 1, eIdx));
		return res;
	}
	
	private static void merge(int[] arr, int[] left, int[] right) {
		
		int[] tmp = new int[left[1] - left[0] + 1];
		
		assigmentsCounter ++;
		for (int i = 0; i < tmp.length; i++) { 
			tmp[i] = arr[left[0] + i];
			assigmentsCounter += 2;
		}
		assigmentsCounter += 2;
		for (int i = left[0], l = 0, r = right[0]; i <= right[1]; i++) {
			arr[i] = r > right[1] || (l < tmp.length && tmp[l] <= arr[r])
					? tmp[l++]
					: arr[r++];
			assigmentsCounter += 3;
		}
	}
	
	public static void sort(int[] arr) { mergeSort(arr, 0, arr.length - 1); }
	
	public static void sort1(int[] array) {
        
        if (array.length == 1) {
            return;
        }
        
        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];
        
        assigmentsCounter += 5;
        
        for (int i = 0; i < left.length; i++) {
        	
        	assigmentsCounter += 2;
            left[i] = array[i];
        }
        for (int i = 0; i < right.length; i++) {
        	
        	assigmentsCounter += 2;
            right[i] = array[i + mid];
        }
        
        sort(left);
        sort(right);
        
        int mainIndex = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        
        assigmentsCounter += 3;
        
        while (leftIndex < left.length 
                && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
            	assigmentsCounter += 3;
            	array[mainIndex++] = left[leftIndex++];
            } else {
            	assigmentsCounter += 3;
            	array[mainIndex++] = right[rightIndex++];
            }
        }
        
        if (leftIndex >= left.length) {
            while (rightIndex < right.length) {
            	assigmentsCounter += 3;
            	array[mainIndex++] = right[rightIndex++];
            }
        } else {
            while (leftIndex < left.length) {
            	assigmentsCounter += 3;
            	array[mainIndex++] = left[leftIndex++];
            }
        }
    }
	
	public static long resetCounter() {
		long temp = assigmentsCounter;
		assigmentsCounter = 0;
		return temp;
	}
}
