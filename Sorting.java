import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sorting {
	
	static Long start;
	static Long end;
	
	public static void main(String args[]) throws FileNotFoundException{
		int[] r1k = new int[1000];
		
		Scanner scanner;
		
		
		File file = new File("random_numbers/r_1k.out");
		scanner = new Scanner(file);
		
		
		int i = 0;
		while(scanner.hasNextLine()){
			r1k[i++] = Integer.parseInt(scanner.nextLine());
		}

		insertionSort(r1k);
		for(int y : r1k){
			System.out.print(y + ", ");
		}	
	}
	
	public static Long insertionSort(int[] array){
		array=array.clone();
		start = System.nanoTime();
		for(int i = 1; i < array.length; i++){
			int p = array[i];
			int j;
			for(j = i - 1; j >=0 && p < array[j]; j--){
				array[j+1] = array[j];
			}
			array[j+1] = p;
		}
		end = System.nanoTime();
		return end;
		
	}
	
	public Long shellSort(){
		return null;
		
	}
	
	public Long quickSort(){
		return null;
		
	}
	
	public Long LSDRadix(){
		return null;
		
	}

}
