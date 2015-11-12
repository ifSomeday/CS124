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
		
		insertionSort(r_1k);
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

	public Long quickSort(int[] array) {
		array = array.clone();
		start = System.nanoTime();
		end = System.nanoTime();
		return (end - start);

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
