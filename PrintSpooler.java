
/*
 * Will Rice
 * CS124
 * Print spooler class.
 * input is fed through a file (can be specified in the constructor, or later).
 * each input file specified overwrites pre-existing output files, so feeding multiple files results in only an output for the last.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrintSpooler {

	private int size = 0;
	private int PRINTCAP = 50000000;
	private int currUsed = 0;
	private int id = 0;
	private String line;
	private Matcher matcher;
	private Pattern pattern = Pattern.compile("(\\w+(\\d*(:*))*)");
	private List<String> lineList = new ArrayList<String>();

	private PrintWriter writer;
	private Scanner scanner;
	private PrintJob[] heap = new PrintJob[1025];

	// constructors
	public PrintSpooler() throws FileNotFoundException {
		heap[0] = new PrintJob(null, null, Integer.MAX_VALUE, 0, 0, 0);
		writer = new PrintWriter("output.txt");

	}

	public PrintSpooler(File file) throws FileNotFoundException {
		this();
		readFile(file);

	}

	// private functions
	private int parent(int position) {
		return (position / 2);
	}

	private int leftChild(int position) {
		return (2 * position);
	}

	private int rightChild(int position) {
		return ((2 * position) + 1);
	}

	private void swap(int positionOne, int positionTwo) {
		PrintJob temp = heap[positionOne];
		heap[positionOne] = heap[positionTwo];
		heap[positionTwo] = temp;
	}

	private void maxHeapify(int position) {
		int largest = position;
		if (leftChild(position) <= size && heap[leftChild(position)].getPriority() > heap[largest].getPriority()) {
			largest = leftChild(position);
		}
		if (rightChild(position) <= size && heap[rightChild(position)].getPriority() > heap[largest].getPriority()) {
			largest = rightChild(position);
		}

		if (largest != position) {
			swap(largest, position);
			maxHeapify(largest);
		}
	}

	private void percolateUp(int position) {
		if (position != 1) {
			if (heap[position].getPriority() > heap[parent(position)].getPriority()) {
				swap(position, parent(position));
				percolateUp(parent(position));
			}
		}
	}

	private void remove(int position) {
		swap(1, position);
		this.print_next();
	}

	private void readFile() {
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			matcher = pattern.matcher(line);
			while (matcher.find()) {
				lineList.add(matcher.group());
			}
			writer.println(line);
			switch (lineList.get(0)) {
			case ("add"):
				writer.println(
						this.add(new PrintJob(lineList.get(1), lineList.get(2), Integer.parseInt(lineList.get(3)),
								Integer.parseInt(lineList.get(4)), Integer.parseInt(lineList.get(5)))));
				break;
			case ("print_next"):
				writer.println(print_next());
				break;
			case ("find_next"):
				writer.println(find_next());
				break;
			case ("status"):
				status();
				break;
			case ("cancel"):
				try {
					int test = Integer.parseInt(lineList.get(1));
					writer.println(cancel(test));
				} catch (Exception e) {
					writer.println(cancel(lineList.get(1)));
				}
				break;
			default:
				writer.write("command not recognized");
				break;
			}
			lineList.clear();
		}
	}

	// public functions
	public int add(PrintJob printJob) {
		if (printJob.getSize() + this.currUsed > this.PRINTCAP) {
			return (-1);
		} else {
			currUsed += printJob.getSize();
			printJob.setid(++id);
			if (id == 1025)
				id = 1;
			heap[++size] = printJob;
			percolateUp(size);
			return (printJob.getid());
		}
	}

	public int find_next() {
		if (heap[1] == null)
			return (-2);
		return (heap[1].getid());
	}

	public int print_next() {
		if (size >= 1) {
			PrintJob tmp = heap[1];
			currUsed = currUsed - tmp.getSize();
			swap(1, size);
			heap[size--] = null;
			maxHeapify(1);
			return (tmp.getid());
		}
		return (-2);
	}

	public void status() {
		if (this.size == 0) {
			writer.println(-2);
			return;
		}
		for (int i = 1; i <= this.size; i++) {
			writer.println(heap[i]);
		}

	}

	public int cancel(String login) {
		int req = 0;
		for (int i = 1; i <= size; i++) {
			if (heap[i].getLogin().trim().equals(login.trim())) {
				this.remove(i);
				req++;
			}
		}
		if (req == 0) {
			return (-3);
		} else {
			return (req);
		}

	}

	public int cancel(int id) {
		for (int i = 1; i <= size; i++) {
			if (heap[i].getid() == id) {
				remove(i);
				return (0);
			}
		}
		return (-3);
	}

	public void readFile(File file) throws FileNotFoundException {
		scanner = new Scanner(file);
		readFile();
		writer.close();
	}

}
