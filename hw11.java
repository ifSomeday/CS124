
public class hw11 {
	
	public static void main(String args[]){
		System.out.println(hash1("Saddam Hussein",1048573));
		System.out.println(hash2("Saddam Hussein",1048573));
	}
	
	static int hash1(String s, int m){
		int h = 0;
		for(char c : s.toCharArray()){
			h = 127*h + (int)c;
		}
		h = h % m;
		if(h < 0)
			h += m;
		return h;
	}
	
	static int hash2(String s, int m){
		int h = 0;
		for(char c : s.toCharArray()){
			h = (127 * h + (int)c)%m;
		}
		return h;
	}
}
