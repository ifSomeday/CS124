
/*
 * Will Rice
 * CS124
 * A program to test the PrintSpooler class.
 * Looks for a file named "input.txt" to read from.
 */

import java.io.File;
import java.io.FileNotFoundException;

public class PrintSpoolerTest {

	public static void main(String args[]) throws FileNotFoundException {

		PrintSpooler spooler = new PrintSpooler();
		spooler.readFile(new File("input.txt"));

	}

}
