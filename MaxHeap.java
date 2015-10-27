//code modified from http://www.sanfoundry.com/java-program-implement-max-heap/

public class MaxHeap {
	private PrintJob[] Heap;
	private int size;
	private int maxsize;

	private static final int FRONT = 1;

	public MaxHeap(int maxsize) {
		this.maxsize = maxsize;
		this.size = 0;
		Heap = new PrintJob[this.maxsize + 1];
		Heap[0] = new PrintJob(Integer.MAX_VALUE);
	}

	private int parent(int pos) {
		return pos / 2;
	}

	private int leftChild(int pos) {
		return (2 * pos);
	}

	private int rightChild(int pos) {
		return (2 * pos) + 1;
	}

	private boolean isLeaf(int pos) {
		if (pos >= (size / 2) && pos <= size) {
			return true;
		}
		return false;
	}

	private void swap(int fpos, int spos) {
		PrintJob tmp;
		tmp = Heap[fpos];
		Heap[fpos] = Heap[spos];
		Heap[spos] = tmp;
	}

	private void maxHeapify(int pos) {
		if (!isLeaf(pos)) {
			if (Heap[leftChild(pos)] != null || Heap[rightChild(pos)] != null) {
				if (Heap[pos].getPriority() < Heap[leftChild(pos)].getPriority()
						|| Heap[pos].getPriority() < Heap[rightChild(pos)].getPriority()) {
					if (Heap[leftChild(pos)].getPriority() > Heap[rightChild(pos)].getPriority()) {
						swap(pos, leftChild(pos));
						maxHeapify(leftChild(pos));
					} else {
						swap(pos, rightChild(pos));
						maxHeapify(rightChild(pos));
					}
				}
			}
		}
	}

	public void add(PrintJob printJob) {
		Heap[++size] = printJob;
		int current = size;

		while (Heap[current].getPriority() > Heap[parent(current)].getPriority()) {
			swap(current, parent(current));
			current = parent(current);
		}
	}

	public void print() {
		for (int i = 1; i <= size / 2; i++) {
			System.out.print(
					" PARENT : " + Heap[i] + " LEFT CHILD : " + Heap[2 * i] + " RIGHT CHILD :" + Heap[2 * i + 1]);
			System.out.println();
		}
	}

	public void maxHeap() {
		for (int pos = (size / 2); pos >= 1; pos--) {
			maxHeapify(pos);
		}
	}

	public PrintJob remove() {
		if (this.size == 0) {
			return null;
		}
		PrintJob popped = Heap[FRONT];
		Heap[FRONT] = Heap[size--];
		maxHeapify(FRONT);
		return popped;
	}
	
	public int top() {
		if(this.size == 0){
			return -2;
		} else {
			return Heap[FRONT].getid();
		}
	}
	
	public int cancel(int id){
		for(int i = 0; i <= size; i++){
			if(Heap[i].getid() == id){
				swap(i, FRONT);
				remove();
				System.out.println("done");
			}

		}
		return 0;
		
	}
	
	public int cancel(String login){
		return 0;
		
	}

	public static void main(String... arg) {
		MaxHeap printSchedule = new MaxHeap(1024);
		printSchedule.add(new PrintJob("byung", "11:30:14:02:28:08", 5, 5000000, 0, 1));
		printSchedule.add(new PrintJob("isaac", "12:30:14:02:28:08", 10, 15000000, 0, 2));
		printSchedule.add(new PrintJob("sonah", "14:30:14:02:28:08", 8, 10000000, 0, 3));
		printSchedule.add(new PrintJob("george", "20:30:14:02:28:08", 33, 30000000, 0, 4));
		printSchedule.print();
		System.out.println(printSchedule.remove());
		printSchedule.print();
		System.out.println(printSchedule.remove());
		printSchedule.print();
		System.out.println(printSchedule.remove());
		printSchedule.print();
		System.out.println(printSchedule.remove());
		printSchedule.print();
		

	}
}
