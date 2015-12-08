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
		}
		table.register("Will", "uj");
		table.register("Will1", "uj");
		table.list();
		System.out.println("");
		table.login("Will", "uj");
		table.logout("Will1");
		table.login("Will1", "uj");
		table.users();
		table.unregister("Will");
		table.logout("Will1");
		table.inspect("sdf");
		table.inspect("size");
		table.inspect("load");
		table.inspect("entries");
		//table.list();
	}
}
