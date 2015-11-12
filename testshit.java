import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testshit {
	
	public static void main(String args[]){
			int array[] = {0,1,2};
			
			for(int i : array){
				System.out.println(i);
			}
			
			change(array);
			
			for(int i : array){
				System.out.println(i);
			}
				
	}
	
	public static void change(int p[]){
		
		for(int i : p){
			p = p.clone();
			p[i]++;
			System.out.println(p[i]);
		}
	}

}
