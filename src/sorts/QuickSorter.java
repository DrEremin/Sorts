package sorts;

import java.util.Random;

public class QuickSorter {
	
	private static long assigmentsCounter = 0;

	public static void main(String[] args) {
		
		int[][] arrays = new int[5][];
        Random random = new Random();
        long sum;
        long result;
        System.out.printf("Quick sort:%13s%15s%18s", "best", "middle", "worst\n\n");
        
        for (int n = 10, k = 0; n < 100001; n *= 10, k++) {
        	System.out.print("n = " + n + ": ");
        	sum = 0;
        	arrays[k] = new int[n];
        	for (int i = 0; i < arrays[k].length; i++) {
        		arrays[k][i] = i;
        	}
        	sort(arrays[k], 0, arrays[k].length - 1);
        	result = resetCounter();
        	System.out.printf("%15d ", result);
        	sum += result;
        	
        	for (int i = 0; i < arrays[k].length; i++) {
        		arrays[k][i] = random.nextInt(20) - 5;
        	}
        	sort(arrays[k], 0, arrays[k].length - 1);
        	result = resetCounter();
        	System.out.printf("%15d ", result);
        	sum += result;
        	
        	for (int i = arrays[k].length - 1, j = 0; j < arrays[k].length; i--, j++) {
        		arrays[k][j] = i;
        	}
        	sort(arrays[k], 0, arrays[k].length - 1);
        	result = resetCounter();
        	System.out.printf("%15d ", result);
        	sum += result;
        	System.out.printf("\nAverage: %d\n\n", sum / 3);
        }
	}
	
	public static void inversion(int[] arr, int lIdx, int rIdx) {
		int temp = arr[lIdx];
		arr[lIdx] = arr[rIdx];
		arr[rIdx] = temp;
		assigmentsCounter += 3;
	}
	
	public static void sort(int[] arr, int sIdx, int eIdx) {
		
		int rWall = eIdx, lWall = sIdx, cur = sIdx;
		int pivot = arr[(sIdx + eIdx) / 2];
		
		assigmentsCounter += 4;
		while (cur <= rWall) {
			while (cur <= rWall && arr[cur] > pivot) {
				inversion(arr, cur, rWall--);
				assigmentsCounter++;
			}
			while (cur <= rWall && arr[cur] < pivot) {
				inversion(arr, cur++, lWall++);
				assigmentsCounter += 2;
			}
			while (cur <= rWall && arr[cur] == pivot) {
				cur++;
				assigmentsCounter++;
			}
		}
		if (lWall > sIdx + 1) {
			sort(arr, sIdx, lWall - 1);
		}
		if (rWall < eIdx - 1) {
			sort(arr, rWall + 1, eIdx);
		}
    }
	
	public static long resetCounter() {
		long temp = assigmentsCounter;
		assigmentsCounter = 0;
		return temp;
	}
}
