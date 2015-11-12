import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sorting {

	static Long start;
	static Long end;
	static Scanner scanner;
	static File file;
	static int[] r_10 = new int[10];
	static int[] r_100 = new int[100];
	static int[] r_1k = new int[1000];
	static int[] r_10k = new int[10000];
	static int[] r_100k = new int[100000];
	static int[] r_1m = new int[1000000];

	public static void main(String args[]) throws FileNotFoundException {

		r_10 = readFile("r_10.out",r_10);
		r_100 = readFile("r_100.out",r_100);
		r_1k = readFile("r_1k.out",r_1k);
		r_10k = readFile("r_10k.out",r_10k);
		r_100k = readFile("r_100k.out",r_100k);
		r_1m = readFile("r_1m.out",r_1m);
		
		System.out.println(quickSortTimer(r_100));
		System.out.println(insertionSort(r_100));
		/*System.out.println(quickSortTimer(r_100));
		System.out.println(quickSortTimer(r_1k));
		System.out.println(quickSortTimer(r_10k));
		System.out.println(quickSortTimer(r_100k));
		System.out.println(quickSortTimer(r_1m));*/
		for (int y : r_1k) {
			System.out.print(y + ", ");
		}
		scanner.close();
	}

	public static Long insertionSort(int[] array) {
		array = array.clone();
		start = System.nanoTime();
		for (int i = 1; i < array.length; i++) {
			int p = array[i];
			int j;
			for (j = i - 1; j >= 0 && p < array[j]; j--) {
				array[j + 1] = array[j];
			}
			array[j + 1] = p;
		}
		end = System.nanoTime();
		return (end - start);

	}

	public Long shellSort(int[] array) {
		array = array.clone();
		start = System.nanoTime();
		end = System.nanoTime();
		return (end - start);
	}

	public static Long quickSortTimer(int[] array) {
		array = array.clone();
		start = System.nanoTime();
		quickSort(array,0,array.length-1);
		end = System.nanoTime();
		for (int y : array) {
			System.out.print(y + ", ");
		}
		System.out.println("");
		return (end - start);
	}
	
	public static void quickSort(int[] array, int start, int end){
		int index = partition(array, start, end);
		if(start < index - 5){
			quickSort(array, start, index - 1);
		}
		
		if(end > index + 5){
			quickSort(array, index, end);
		}
		insertionSort(array);
	}
	
	public static int partition(int[] array, int leftIndex, int rightIndex){
		int left = array[leftIndex], mid = array[(leftIndex+rightIndex)/2], right = array[rightIndex], pivot;
		pivot = Math.max(Math.min(left,mid),Math.min(Math.max(left,mid),right));
		while(leftIndex <= rightIndex){
			while(array[leftIndex] < pivot){
				leftIndex++;
			}
			while(array[rightIndex] > pivot){
				rightIndex--;
			}
			
			if(left <= right){
				int p = array[rightIndex];
				array[rightIndex--] = array[leftIndex];
				array[leftIndex++] = p;
				
			}
		}
		return leftIndex;
	}

	public Long LSDRadix(int[] array) {
		array = array.clone();
		start = System.nanoTime();
		end = System.nanoTime();
		return (end - start);

	}
	
	public static int[] readFile(String name, int[] array) throws FileNotFoundException{
		file = new File("random_numbers/"+name);
		scanner = new Scanner(file);
		int i = 0;
		while(scanner.hasNextLine()){
			array[i++] = Integer.parseInt(scanner.nextLine());
		}
		scanner.close();
		return(array);
	}

}
