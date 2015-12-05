/*
 * Will Rice
 * CS 124
 * all code written by Will Rice.
 * Copying code without permission is expressly prohibited.
 * quadratic probing, double and rehash if 50% full, halve and rehash if 15% full
 */
public class loginHashTable {

	private int size = 11;
	private int filled;

	loginHashTable() {

	}

	public void setSize() {

	}

	private double loadValue() {
		return (filled / size);
	}

	public void register(String user, String pass) {

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
}
