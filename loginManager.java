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
			table.query("register Byung"+i + " "+i);
		}
		
		table.query("login");
		table.query("login Byung2");
		table.query("login Byung3 3");
		table.query("list");
		
		/*table.register("Will", "uj");
		table.register("Will1", "uj");
		table.query("list");
		table.query("register uuuu");
		table.query("register uuuu2 uuuu2");
		table.login("Will", "uj");
		table.logout("Will1");
		table.login("Will1", "uj");
		table.query("users");
		table.unregister("Will");
		table.logout("Will1");
		table.inspect("size");
		table.inspect("load");
		table.inspect("entries");
		table.query("dump");

		 
		table.query("load");
		table.query("list");*/
	}
}
