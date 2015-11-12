import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testshit {
	
	public static void main(String args[]){
			
		int i;
				
	}
	
	public static void change(int p[]){
		
		for(int i : p){
			p = p.clone();
			p[i]++;
			System.out.println(p[i]);
		}
	}

}
