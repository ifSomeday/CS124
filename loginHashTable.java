/*
 * Will Rice
 * CS 124
 * all code written by Will Rice.
 * Copying code without permission is expressly prohibited.
 * quadratic probing, double and rehash if 50% full, halve and rehash if 15% full
 */
public class loginHashTable {

	private int size = 11;
	private int quadCounter = 0;
	private int filled = 0;
	private User hashTable[] = new User[size];
	
	loginHashTable() {
		
	}

	public void register(String user, String pass) {
		register(new User(user,pass,false,false));
	}

	public void register(User user) {
		//System.out.println(loadValue());
		if(loadValue() >= 0.5){
			filled = 0;
			//System.out.println("rehashin");
			User ar[] = hashTable.clone();
			size = prime(2);
			hashTable = new User[size];
			rehash(ar);
			ar = null;
			register(user);
		} else {
			int key = hash(user.getUsername());
			insert(user, key);
			filled++;
		}
		System.out.println("User " + user.getUsername() + " registered!");
	}
	
	public void unregister(String user) {
		
	}

	public void list() {
		for(User u: hashTable){
			System.out.println(u);
		}
	}

	public void login(String user, String pass) {
		int index = search(user, hash(user), 0);
		if(index != -1){
			System.out.println(hashTable[index].getPassword());
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

	}

	public void users() {

	}

	public void inspect(int property) {

	}

	public void dump() {

	}

	private int prime(int mult) {
		boolean prime = false;
		int num = size*mult;
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
		//System.out.println(num);
		return(num);
	}

	private  int prevPrime() {
		boolean prime = false;
		int num = size;
		while (!prime) {
			test: if (num % 2 == 0) {
				num--;
			} else {
				prime = true;
				for (int i = 3; i <= (int)Math.sqrt(num); i++) {
					if (num % i == 0) {
						prime = false;
						num--;
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
	
	private int hashQuad(int key, int i){
		return((int)((key + Math.pow(i, 2))%this.size));
	}
	
	private void insert(User user, int key){
		if(hashTable[key] == null){
			quadCounter = 0;
			hashTable[key] = user;
		} else {
			quadCounter++;
			key = hashQuad(key, quadCounter);
			insert(user, key);
		}
	}
	
	private int search(String user, int k, int i){
		if(i > size){
			return(-1);
		}
		System.out.println(hashTable[(k + (int)(Math.pow(i, 2)))%size]);
		if(hashTable[(k + (int)(Math.pow(i, 2)))%size] != null && hashTable[(k + (int)(Math.pow(i, 2)))%size].getUsername().equals(user)){
			return((k + (int)(Math.pow(k, i)))%size);
		} else {
			return(search(user, k, ++i));
		}
	}

	
	private void rehash(User[] ar){
		for(User u: ar){
			if(u != null){
				register(u);
			}
		}
	}
}
