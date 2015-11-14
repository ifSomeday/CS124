import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Sorting {

	static Long start;
	static Long end;
	static Scanner scanner;
	static File file;
	static int j = 0;
	static int[] r_10 = new int[10];
	static int[] r_100 = new int[100];
	static int[] r_1k = new int[1000];
	static int[] r_10k = new int[10000];
	static int[] r_100k = new int[100000];
	static int[] r_1m = new int[1000000];
	static int[][] arrays = {r_10, r_100, r_1k, r_10k, r_100k, r_1m}; 
	static ArrayList<Integer> sedgewick = new ArrayList<Integer>();
	@SuppressWarnings("unchecked")
	static ArrayList<Integer>[] LSD = new ArrayList[10];

	public static void main(String args[]) throws FileNotFoundException {

		r_10 = readFile("r_10.out", r_10);
		r_100 = readFile("r_100.out", r_100);
		r_1k = readFile("r_1k.out", r_1k);
		r_10k = readFile("r_10k.out", r_10k);
		r_100k = readFile("r_100k.out", r_100k);
		r_1m = readFile("r_1m.out", r_1m);
		scanner.close();
		int[][] arrays = {r_10, r_100, r_1k, r_10k, r_100k, r_1m};
		
		for(int ch = 10; ch <= 1000000; ch *= 10){
			System.out.println(ch + " random characters:");
			System.out.println("Insertion Sort: " + insertionSortTimer(arrays[(int) (Math.log10(ch)-1)]));
			System.out.println("Shell sort:     " + shellSortTimer(arrays[(int) (Math.log10(ch)-1)]));
			System.out.println("Quick sort:     " + quickSortTimer(arrays[(int) (Math.log10(ch)-1)]));
			System.out.println("LSD Radix Sort: " + LSDRadixTimer(arrays[(int) (Math.log10(ch)-1)]));
			System.out.println();
		}
	}

	public static Long insertionSortTimer(int[] array) {
		array = array.clone();
		start = System.nanoTime();
		insertionSort(array, 0, array.length);
		end = System.nanoTime();
		return (end - start);

	}

	public static void insertionSort(int[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex + 1; i < rightIndex; i++) {
			final int p = array[i];
			int j;
			for (j = i - 1; j >= 0 && p < array[j]; j--) {
				array[j + 1] = array[j];
			}
			array[j + 1] = p;
		}
	}

	public static Long shellSortTimer(int[] array) {
		array = array.clone();
		generateSequence(array.length);
		start = System.nanoTime();
		shellSort(array);
		end = System.nanoTime();
		return (end - start);
	}

	public static void shellSort(int[] array) {
		for (final int gap : sedgewick) {
			for (int i = gap; i < array.length; i++) {
				final int temp = array[i];
				for (j = i; j >= gap && array[j - gap] > temp; j = j - gap) {
					array[j] = array[j - gap];
				}
				array[j] = temp;
			}
		}
	}

	public static void generateSequence(int length) {
		length = length / 2;
		sedgewick.clear();
		int gap = 0, odd = 0, even = 2;
		boolean bool = true;
		while (gap <= length) {
			if (bool) {
				gap = (int) ((9 * Math.pow(4, odd)) - (9 * Math.pow(2, odd)) + 1);
				odd++;
				bool = false;
			} else {
				gap = (int) (Math.pow(4, even) - (3 * Math.pow(2, even)) + 1);
				even++;
				bool = true;
			}
			if (gap <= length) {
				sedgewick.add(gap);
			}
		}
		Collections.reverse(sedgewick);
	}

	public static Long quickSortTimer(int[] array) {
		array = array.clone();
		start = System.nanoTime();
		quickSort(array, 0, array.length);
		end = System.nanoTime();
		return (end - start);
	}

	public static void quickSort(int[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			if (leftIndex - rightIndex < 5) {
				insertionSort(array, leftIndex, rightIndex);
			} else {
				final int index = partition(array, leftIndex, rightIndex);
				if (leftIndex < index - 1) {
					quickSort(array, leftIndex, index);
				}
				if (rightIndex > index) {
					quickSort(array, index + 1, rightIndex);
				}
			}
		}
	}

	public static int partition(int[] array, int leftIndex, int rightIndex) {
		final int pivot = Math.max(Math.min(array[leftIndex], array[(leftIndex + rightIndex) / 2]),
				Math.min(Math.max(array[leftIndex], array[(leftIndex + rightIndex) / 2]), array[rightIndex]));
		leftIndex--;
		rightIndex++;
		while (true) {
			do {
				rightIndex--;
			} while (array[rightIndex] > pivot);

			do {
				leftIndex++;
			} while (array[leftIndex] < pivot);

			if (leftIndex < rightIndex) {
				final int p = array[leftIndex];
				array[leftIndex] = array[rightIndex];
				array[rightIndex] = p;
			} else {
				return rightIndex;
			}
		}
	}

	public static Long LSDRadixTimer(int[] array) {
		array = array.clone();
		for (int i = 0; i < 10; i++) {
			LSD[i] = new ArrayList<Integer>();
		}
		start = System.nanoTime();
		LSDRadix(array);
		end = System.nanoTime();
		return (end - start);

	}

	public static void LSDRadix(int[] array) {
		for (int d = 1; d < 8; d++) {
			for (final int i : array) {
				LSD[getDigit(i, d)].add(i);
			}
			for (int i = 1; i < 10; i++) {
				LSD[0].addAll(LSD[i]);
				LSD[i].clear();
			}
			int j = 0;
			for (final int i : LSD[0]) {
				array[j++] = i;
			}
			LSD[0].clear();
		}
	}

	public static int getDigit(int num, int digit) {
		return (int) ((num / Math.pow(10, digit - 1)) % 10);
	}

	public static int[] readFile(String name, int[] array) throws FileNotFoundException {
		file = new File("random_numbers/" + name);
		scanner = new Scanner(file);
		int i = 0;
		while (scanner.hasNextLine()) {
			array[i++] = Integer.parseInt(scanner.nextLine());
		}
		scanner.close();
		return (array);
	}
}
