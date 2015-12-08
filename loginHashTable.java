
/*
 * Will Rice
 * CS 124
 * all code written by Will Rice.
 * Copying code without permission is expressly prohibited.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class loginHashTable {

	private int size = 11;
	private int filled = 0;
	private User hashTable[] = new User[size];
	private Pattern p = Pattern.compile("\\=([^,\\]\\s]*)");
	private Pattern p2 = Pattern.compile("\\S+");
	private String p3 = "[a-zA-Z\\d._]+";
	private Matcher m;
	private Scanner sf, s = new Scanner(System.in);
	private File f;
	private PrintWriter w;

	loginHashTable() {

	}

	public void query(String input) {
		m = p2.matcher(input);
		m.find();
		switch (m.group().toLowerCase()) {
		case ("register"):
			if (m.find()) {
				String user = m.group();
				if (m.find()) {
					register(user, m.group());
				} else {
					register(user);
				}
			} else {
				register();
			}
			break;
		case ("unregister"):
			if (m.find()) {
				unregister(m.group());
			} else {
				unregister();
			}
			break;
		case ("list"):
			list();
			break;
		case ("login"):
			if (m.find()) {
				String user = m.group();
				if (m.find()) {
					login(user, m.group());
				} else {
					login(user);
				}
			} else {
				login();
			}
			break;
		case ("logout"):
			if (m.find()) {
				logout(m.group());
			} else {
				logout();
			}
			break;
		case ("users"):
			users();
			break;
		case ("inspect"):
			if (m.find()) {
				inspect(m.group());
			} else {
				inspect();
			}
			break;
		case ("dump"):
			dump();
			break;
		case ("load"):
			loadDump();
			break;
		case ("exit"):
			exit();
			break;
		case ("help"):
			help();
			break;
		default:
			System.out.println("Invalid command. enter 'help' for a command list.");
			break;

		}
		System.out.println("\n--------------------------\n");
	}

	private void register() {
		System.out.println("Please enter a username or nothing to cancel: ");
		String user = s.nextLine();
		if (user.equals(null)) {
			System.out.println("Canceling...");
		}
		if (!user.matches(p3)) {
			System.out.println("Username " + user
					+ " contains invalid characters.\nOnly letters, digits, underscores, and periods allowed.");
			register();
		} else {
			register(user);
		}
	}

	private void register(String user) {
		System.out.println("Okay, " + user + ", please enter a password or nothing to select a new username: ");
		String pass = s.nextLine();
		if (!pass.equals("")) {
			System.out.println("Canceling...");
			register();
		} else if (pass.length() < 6 || pass.length() > 10) {
			System.out.println("Passwords must be between 6 and 10 characters.");
			register(user);
		} else {
			register(user, pass);
		}
	}

	private void register(String user, String pass) {
		if (!user.matches(p3)) {
			System.out.println("Username " + user
					+ " contains invalid characters.\nOnly letters, digits, underscores, and periods allowed.");
			register();
		} else if (pass.length() < 6 || pass.length() > 10) {
			System.out.println("Passwords must be between 6 and 10 characters.");
			register(user);
		} else {
			register(new User(user, pass, false, false));
		}
	}

	private void register(User user) {
		if (loadValue() >= 0.5) {
			size = prime(2);
			rehash();
		}
		int key = hash(user.getUsername());
		insert(user, key, 0);
		filled++;
		System.out.println("User " + user.getUsername() + " registered!");
	}

	private void unregister() {
		System.out.println("Enter user to unregister: ");
		String input = s.nextLine();
		unregister(input);
	}

	private void unregister(String user) {
		int key = search(user, hash(user), 0);
		if (key == -1) {
			System.out.println("User not found.");
		} else {
			hashTable[key] = null;
			filled--;
			System.out.println("User " + user + " removed");
		}
		if (loadValue() <= 0.15) {
			size = prime(0.5);
			rehash();
			unregister(user);
		}
	}

	private void list() {
		for (User u : hashTable) {
			System.out.println(u);
		}
	}

	private void login() {
		System.out.println("Please enter user to log in, or nothing to cancel: ");
		String user = s.nextLine();
		if (!user.equals("")) {
			login(user);
		} else {
			System.out.println("Canceling...");
		}
	}

	private void login(String user) {
		System.out.println("Okay " + user + " please enter your password, or nothing to select a different user: ");
		String pass = s.nextLine();
		if (!pass.equals("")) {
			login(user, pass);
		} else {
			login();
		}
	}

	private void login(String user, String pass) {
		int index = search(user, hash(user), 0);
		if (index != -1) {
			if (hashTable[index].getPassword().equals(pass)) {
				if (hashTable[index].isStatus()) {
					System.out.println("User " + user + " is already logged in!");
				} else {
					hashTable[index].setStatus(true);
					System.out.println(user + " logged in!");
				}
			} else {
				System.out.println("Invalid password");
				login(user);
			}
		} else {
			System.out.println("User not found.");
			login();
		}
	}

	private void logout() {
		System.out.println("Enter the user to log out or nothing to cancel: ");
		String user = s.nextLine();
		if (!user.equals("")) {
			logout(user);
		} else {
			System.out.println("Canceling...");
		}
	}

	private void logout(String user) {
		int key = search(user, hash(user), 0);
		if (key == -1) {
			System.out.println("User " + user + " not found.");
			logout();
		} else if (hashTable[key].isStatus()) {
			hashTable[key].setStatus(false);
			System.out.println("User " + user + " logged out.");
		} else {
			System.out.println("User " + user + " is already logged out!");
		}
	}

	private void users() {
		for (User u : hashTable) {
			if (u != null && u.isStatus()) {
				System.out.println(u);
			}
		}
	}

	private void inspect() {
		System.out.println("Enter a property to inspect or nothing to cancel: ");
		String input = s.nextLine();
		if (!input.equals("")) {
			inspect(input);
		} else {
			System.out.println("Canceling...");
		}
	}

	private void inspect(String property) {
		property = property.toLowerCase();
		switch (property) {
		case ("size"):
			System.out.println("Table currently has " + size + " buckets.");
			break;
		case ("load"):
			System.out.println("The table's load value is currently: " + loadValue() + ".");
			break;
		case ("entries"):
			System.out.println("There are currently " + filled + " active entries in the table.");
			break;
		default:
			System.out.println("Invalid property to inspect.\nValid properties are 'size', 'load' and 'entries'.");
			inspect();
			break;
		}
	}

	private void dump() {
		System.out.println("Dumping Hash Table...");
		f = new File("dump.txt");
		try {
			w = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found, dump failed.");
			return;
		}
		w.println("buckets=" + size);
		w.println("filled=" + filled);
		System.out.println("buckets=" + size + "\nfilled=" + filled);
		for (User u : hashTable) {
			w.println(u);
			System.out.println(u);
		}
		w.close();
		System.out.println("Table dump success!");
	}

	private void loadDump() {
		System.out.println("Loading dump...");
		f = new File("dump.txt");
		try {
			sf = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Reading dump failed... File not found.");
			return;
		}
		int i = 0;
		String d;
		m = p.matcher(s.nextLine());
		m.find();
		size = Integer.parseInt(m.group(1));
		m = p.matcher(sf.nextLine());
		m.find();
		filled = Integer.parseInt(m.group(1));
		hashTable = new User[size];
		while (sf.hasNextLine()) {
			d = sf.nextLine();
			if (d.equals(null)) {
				m = p.matcher(s.nextLine());
				m.find();
				hashTable[i] = (new User(m.group(1), m.group(1), Boolean.parseBoolean(m.group(1)),
						Boolean.parseBoolean(m.group(1))));
			}
			i++;
		}
		System.out.println("Dump successfully loaded!");
	}

	private void help() {
		System.out.println(
				"List of Commands:\n\tregister - registers a new user\n\t\toptional arguments: [username], [username] [password]\n\tunregister - unregisters a user\n\t\toptional arguements: [username]\n\tlist - displays a list of registered users \n\tlogin - logs in a registered user\n\t\toptional arguments: [username], [username] [password]\n\tlogout - logs out a logged in user\n\t\toptional arguements: [username]\n\tinspect - inspects a certain element of the hashtable\n\t\toptional arguements: [property] (can be 'size', 'entries', or 'load')\n\tdump - dumps the table to 'dump.txt' for inspection\n\tload - loads the dump located at 'dump.txt'\n\texit - dumps the table and then exits.");
	}

	private void exit() {
		System.out.println("Dumping table and exiting...");
		dump();
		System.exit(0);
	}

	private int prime(double mult) {
		boolean prime = false;
		int num = (int) (size * mult);
		while (!prime) {
			test: if (num % 2 == 0) {
				num++;
			} else {
				prime = true;
				for (int i = 3; i <= (int) Math.sqrt(num); i++) {
					if (num % i == 0) {
						prime = false;
						num++;
						break test;
					}
				}
			}
		}
		return (num);
	}

	private double loadValue() {
		return ((double) filled / (double) size);
	}

	private int hash(String user) {
		int key = 0;
		for (char c : user.toCharArray()) {
			key += (int) c;
		}
		return (key % this.size);
	}

	private void insert(User user, int k, int i) {
		int key = (int) ((k + Math.pow(i, 2)) % this.size);
		if (hashTable[key] == null) {
			hashTable[key] = user;
		} else {
			if (hashTable[key].getUsername().equals(user.getUsername())) {
				System.out.println("Username already taken. Please enter a new username and password.");
				register();
			} else {
				insert(user, k, ++i);
			}
		}
	}

	private int search(String user, int k, int i) {
		if (i > size) {
			return (-1);
		}
		if (hashTable[(int) ((k + Math.pow(i, 2)) % this.size)] != null
				&& hashTable[(int) ((k + Math.pow(i, 2)) % this.size)].getUsername().equals(user)) {
			return ((int) ((k + Math.pow(i, 2)) % this.size));
		} else {
			return (search(user, k, ++i));
		}
	}

	private void rehash() {
		User ar[] = hashTable.clone();
		hashTable = new User[size];
		for (User u : ar) {
			if (u != null) {
				insert(u, hash(u.getUsername()), 0);
			}
		}
		ar = null;
	}
}
