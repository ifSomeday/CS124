import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testshit {
	
	public static void main(String args[]){
		Pattern pattern = Pattern.compile("(\\w+(\\d*(:*))*)");
		Matcher matcher;
		matcher = pattern.matcher("add byung  11:30:14:02:28:08  5  5000000  0");
		while(matcher.find()){
			System.out.println(matcher.group());
		}
	}

}
