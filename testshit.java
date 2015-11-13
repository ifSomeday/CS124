import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testshit {
	
	static ArrayList<Integer> LSD[] = (ArrayList<Integer>[]) new ArrayList[10];
	
	
	public static void main(String args[]){
			
		System.out.println(getDigit(2,2));
				
	}
	
	public static int getDigit(int num, int digit){
		return (int) ((num/Math.pow(10, digit-1))%10);
	}
	

}
