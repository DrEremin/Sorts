package sorts;

import java.util.Random;

public class SmartSorter {

	private static long assigmentsCounter = 0;
	
	public static void main(String[] args) {
		
		int[][] arrays = new int[5][];
        Random random = new Random();
        long sum;
        long result;
        System.out.printf("Insertion sort:%10s%15s%18s", "best", "middle", "worst\n\n");
        
        for (int n = 10, k = 0; n < 100001; n *= 10, k++) {
        	System.out.print("n = " + n + ": ");
        	sum = 0;
        	arrays[k] = new int[n];
        	for (int i = 0; i < arrays[k].length; i++) {
        		arrays[k][i] = i;
        	}
        	smartSort(arrays[k]);
        	result = resetCounter();
        	System.out.printf("%15d ", result);
        	sum += result;
        	
        	for (int i = 0; i < arrays[k].length; i++) {
        		arrays[k][i] = random.nextInt(20) - 5;
        	}
        	smartSort(arrays[k]);
        	result = resetCounter();
        	System.out.printf("%15d ", result);
        	sum += result;
        	
        	for (int i = arrays[k].length - 1, j = 0; j < arrays[k].length; i--, j++) {
        		arrays[k][j] = i;
        	}
        	smartSort(arrays[k]);
        	result = resetCounter();
        	System.out.printf("%15d ", result);
        	sum += result;
        	System.out.printf("\nAverage: %d\n\n", sum / 3);
        }
	}
	
	private static void exchange(int[] arr, int left, int right) {
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
		assigmentsCounter += 3;
	}
	
	private static void quickSort(int[] arr, int sIdx, int eIdx) {
		if (eIdx - sIdx < 16) { 
			insertionSort(arr, sIdx, eIdx);
			return;
		}
		
		int rWall = eIdx, lWall = sIdx, cur = sIdx;
		int pivot = arr[(sIdx + eIdx) / 2];
		
		assigmentsCounter += 4;
		while (cur <= rWall) {
			while (cur <= rWall && arr[cur] > pivot) {
				exchange(arr, cur, rWall--);
				assigmentsCounter++;
			}
			while (cur <= rWall && arr[cur] < pivot) {
				exchange(arr, cur++, lWall++);
				assigmentsCounter += 2;
			}
			while (cur <= rWall && arr[cur] == pivot) {
				cur++;
				assigmentsCounter++;
			}
		}
		if (lWall > sIdx + 1) {
			quickSort(arr, sIdx, lWall - 1);
		}
		if (rWall < eIdx - 1) {
			quickSort(arr, rWall + 1, eIdx);
		}
    }
	
	private static void insertionSort(int[] arr, int sIdx, int eIdx) {
		assigmentsCounter++;
        for (int i = sIdx; i < eIdx; i++) {
        	assigmentsCounter++;
            for (int j = i + 1; j > sIdx; j--) {
                if (arr[j] < arr[j - 1]) { exchange(arr, j, j - 1); }
                else { break; }
            }
        }
    }
	
	public static void smartSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}
	
	private static long resetCounter() {
		long temp = assigmentsCounter;
		assigmentsCounter = 0;
		return temp;
	}
}
