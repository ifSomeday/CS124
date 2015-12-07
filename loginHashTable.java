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
		if(loadValue() >= 0.5){
			filled = 0;
			User ar[] = hashTable.clone();
			size = prime(2);
			hashTable = new User[size];
			rehash(ar);
			ar = null;
			register(user);
		} else {
			int key = hash(user.getUsername());
			insert(user, key, 0);
			filled++;
		}
		
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

	
	private void rehash(User[] ar){
		for(User u: ar){
			if(u != null){
				register(u);
			}
		}
	}
}
