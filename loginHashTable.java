import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Will Rice
 * CS 124
 * all code written by Will Rice.
 * Copying code without permission is expressly prohibited.
 * quadratic probing, double and rehash if 50% full, halve and rehash if 15% full
 */
public class loginHashTable {

	private int size = 11;
	private int filled = 0;
	private User hashTable[] = new User[size];
	private Pattern p = Pattern.compile("\\=([^,\\]\\s]*)");
	private Matcher m;
	private Scanner s;
	private File f;
	private PrintWriter w;
	
	loginHashTable() {
	}

	public void register(String user, String pass) {
		register(new User(user,pass,false,false));
	}

	public void register(User user) {
		if(loadValue() >= 0.5){
			size = prime(2);
			rehash();
			register(user);
		} 
		int key = hash(user.getUsername());
		insert(user, key, 0);
		filled++;
		System.out.println("User " + user.getUsername() + " registered!");
	}
	
	public void unregister(String user) {
		int key = search(user, hash(user), 0);
		if(key == -1){
			System.out.println("User not found.");
		} else {
			hashTable[key] = null;
			filled--;
			System.out.println("User " + user + " removed");
		}
		if(loadValue() <= 0.15){
			size = prime(0.5);
			rehash();
			unregister(user);
		}
	}

	public void list() {
		for(User u: hashTable){
			System.out.println(u);
		}
	}

	public void login(String user, String pass) {
		int index = search(user, hash(user), 0);
		if(index != -1){
			if(hashTable[index].getPassword().equals(pass)){
				hashTable[index].setStatus(true);
				System.out.println(user + " logged in!");
			} else {
				System.out.println("Invalid password");
			}
		} else {
			System.out.println("User not found.");
		}
	}

	public void logout(String user) {
		int key = search(user, hash(user), 0);
		if(key == -1){
			System.out.println("User " + user + " not found.");
		} else if(hashTable[key].isStatus()){
			hashTable[key].setStatus(false);
			System.out.println("User " + user + " logged out.");
		} else {
			System.out.println("User " + user + " is already logged out!");
		}
	}

	public void users() {
		for(User u: hashTable){
			if(u != null && u.isStatus()){
				System.out.println(u);
			}
		}
	}

	public void inspect(String property) {
		property = property.toLowerCase();
		switch(property){
		case("size"):
			System.out.println("Table currently has " + size + " buckets.");
			break;
		case("load"):
			System.out.println("The table's load value is currently: " + loadValue() + ".");
			break;
		case("entries"):
			System.out.println("There are currently " + filled + " active entries in the table.");
			break;
		default:
			System.out.println("Invalid property to inspect.\nValid properties are 'size', 'load' and 'entries'.");
			break;
		}
	}

	public void dump() {
		System.out.println("Dumping Hash Table...");
		f = new File("dump.txt");
		try {
			w = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("File not found, dump failed.");
			return;
		}
		w.println("buckets=" + size);
		w.println("filled=" + filled);
		System.out.println("buckets=" + size + "\nfilled=" + filled);
		for(User u: hashTable){
			w.println(u);
			System.out.println(u);
		}
		w.close();
	}
	
	public void loadDump(){
		f = new File("dump.txt");
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("Reading dump failed... File not found.");
			return;
		}
		int i = 0;
		String d;
		m = p.matcher(s.nextLine());
		m.find();
		size = Integer.parseInt(m.group(1));
		m = p.matcher(s.nextLine());
		m.find();
		filled  = Integer.parseInt(m.group(1));
		hashTable = new User[size];
		while(s.hasNextLine()){
			d = s.nextLine();
			if(d.equals(null)){
				m = p.matcher(s.nextLine());
				m.find();
				hashTable[i] = (new User(m.group(1),m.group(1),Boolean.parseBoolean(m.group(1)),Boolean.parseBoolean(m.group(1))));
			}
			i++;
		}
	}

	private int prime(double mult) {
		boolean prime = false;
		int num = (int) (size*mult);
		while (!prime) {
			test: if (num % 2 == 0) {
				num++;
			} else {
				prime = true;
				for (int i = 3; i <= (int)Math.sqrt(num); i++) {
					if (num % i == 0) {
						prime = false;
						num++;
						break test;
					}
				}
			}
		}
		return(num);
	}
	
	private double loadValue() {
		return ((double)filled/(double)size);
	}
	
	private int hash(String user){
		int key = 0;
		for(char c: user.toCharArray()){
			key += (int)c;
		}
		return(key%this.size);
	}
	
	private void insert(User user, int k, int i){
		int key = (int)((k + Math.pow(i, 2))%this.size);
		if(hashTable[key] == null){
			//System.out.println("inserting at: " + key);
			hashTable[key] = user;
		} else {
			insert(user, k, ++i);
		}
	}
	
	private int search(String user, int k, int i){
		if(i > size){
			return(-1);
		}
		//System.out.println(" size: " + size + " key: " + (int)((k + Math.pow(i, 2))%this.size) + " run through: " + i);
		if(hashTable[(int)((k + Math.pow(i, 2))%this.size)] != null && hashTable[(int)((k + Math.pow(i, 2))%this.size)].getUsername().equals(user)){
			return((int)((k + Math.pow(i, 2))%this.size));
		} else {
			return(search(user, k, ++i));
		}
	}

	
	private void rehash(){
		User ar[] = hashTable.clone();
		hashTable = new User[size];
		for(User u: ar){
			if(u != null){
				insert(u, hash(u.getUsername()), 0);
			}
		}
		ar = null;
	}
}
