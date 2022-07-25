package sorts;

import java.util.Random;

public class BubbleSorter {
	
	private static long assigmentsCounter = 0;

	public static void main(String[] args) {
		
		int[][] arrays = new int[5][];
        Random random = new Random();
        long sum;
        long result;
        System.out.printf("Bubble sort:%13s%15s%18s", "best", "middle", "worst\n\n");
        
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
	
	public static void sort(int[] arr) {
        int temp;
        boolean inversions;
        for (int i = 1; i < arr.length; i++) {
        	assigmentsCounter += 2;
            inversions = false;
            for (int j = 0; j < arr.length - i; j++) {
            	assigmentsCounter++;
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    inversions = true;
                    assigmentsCounter += 4;
                }
            }
            if (!inversions) {
                break;
            }
        }
    }
	
	public static long resetCounter() {
		long temp = assigmentsCounter;
		assigmentsCounter = 0;
		return temp;
	}

}
