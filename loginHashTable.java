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
	private int filled;
	private User hashTable[] = new User[size];
	
	loginHashTable() {
		
	}

	public void setSize() {
		
	}

	public void register(String user, String pass) {
		register(new User(user,pass,false,false));
	}

	public void register(User user) {
		int key = hash(user.getUsername());
		insert(user, key);
	}
	
	public void unregister(String user) {

	}

	public void list() {

	}

	public void login(String user, String pass) {

	}

	public void logout(String user) {

	}

	public void users() {

	}

	public void inspect(int property) {

	}

	public void dump() {

	}

	private int nextPrime() {
		boolean prime = false;
		int num = size + 1;
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
		int num = size - 1;
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
		return ((double)(filled / size));
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
}
