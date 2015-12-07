/*
 * Will Rice
 * CS 124
 * all code written by Will Rice.
 * Copying code without permission is expressly prohibited.
 */
public final class loginManager {
	
	static boolean state;
	
	public static void main(String[] args){
		loginHashTable table = new loginHashTable();
		for(int i = 0; i < 8; i++){
			table.register("Byung"+i, ""+i);
			System.out.println("");
		}
		table.register("Will", "dicks");
		table.list();
		table.login("Will", "dicks");
	}
}
