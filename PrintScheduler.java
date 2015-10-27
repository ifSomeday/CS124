import java.io.File;

public class PrintScheduler {
	
	private File file;
	private MaxHeap heap = new MaxHeap(1024);
	private int currKey = 0, size = 0, MAXSIZE = 50000000;
	
	public PrintScheduler(){
		
	}
	
	public PrintScheduler(File file){
		this.setFile(file);
	}
	
	public void setFile(File file){
		this.file = file;
	}
	
	public int printNext(){
		PrintJob tmp = heap.remove();
		if(tmp == null){
			return -2;
		} else {
			return tmp.getid();
		}
	}
	
	public void findNext(){
		heap.top();
	}
	
	public int add(PrintJob printJob){
		if(currKey == 1024){
			currKey = 0;
		}
		if (size + printJob.getSize() > MAXSIZE){
			return(-1);
		} else {
			size += printJob.getSize();
			printJob.setid(++currKey);
			heap.add(printJob);
			return currKey;
		}
	}
	
	public PrintJob remove(){
		
		return null;
	}
	

	
	

}
