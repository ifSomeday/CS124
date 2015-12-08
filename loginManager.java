
/*
 * Will Rice
 * CS 124
 * all code written by Will Rice.
 * Copying code without permission is expressly prohibited.
 */
import java.util.Scanner;

public final class loginManager {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		loginHashTable table = new loginHashTable();
		for (;;) {
			table.query(scanner.nextLine());
		}
	}
}
