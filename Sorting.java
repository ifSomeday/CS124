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
		System.out.println(quickSortTimer(r_1m));
		for (int y : r_1k) {
			System.out.print(y + ", ");
		}*/
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
		for (int y : array) {
			System.out.print(y + ", ");
		}
		System.out.println("");
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
	
	public static void quickSort(int[] array, int leftIndex, int rightIndex){
		int index = partition(array,leftIndex,rightIndex);
		if(leftIndex < index -1){
			quickSort(array, leftIndex,index);
		}
		if(rightIndex > index){
			quickSort(array,index+1,rightIndex);
		}
	}
	
	public static int partition(int[] array, int leftIndex, int rightIndex){
		int pivot = Math.max(Math.min(array[leftIndex],array[(leftIndex+rightIndex)/2]),Math.min(Math.max(array[leftIndex],array[(leftIndex+rightIndex)/2]),array[rightIndex]));
		leftIndex--;
		rightIndex++;
		while(true){
			do{
				rightIndex--;
			}while(array[rightIndex] > pivot);
			
			do{
				leftIndex++;
			}while(array[leftIndex] < pivot);
			
			if(leftIndex < rightIndex){
				int p = array[leftIndex];
				array[leftIndex] = array[rightIndex];
				array[rightIndex] = p;
			} else {
				return rightIndex;
			}
		}
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
