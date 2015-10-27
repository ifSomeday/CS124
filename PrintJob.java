/*
 * Will Rice
 * CS124
 * each print job requested gets its own object, defined here.
 */
public class PrintJob {

	private String login, time;
	private int priority, size, handle, id;

	// Constructors
	public PrintJob() {

	}

	public PrintJob(int priority) {
		this.priority = priority;
	}

	public PrintJob(String login, String time, int priority, int size, int handle, int id) {
		super();
		this.login = login;
		this.time = time;
		this.priority = priority;
		this.size = size;
		this.handle = handle;
		this.id = id;
	}

	public PrintJob(String login, String time, int priority, int size, int handle) {
		super();
		this.login = login;
		this.time = time;
		this.priority = priority;
		this.size = size;
		this.handle = handle;
	}

	// getters and setters
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getHandle() {
		return handle;
	}

	public void setHandle(int handle) {
		this.handle = handle;
	}

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	// to string
	@Override
	public String toString() {
		return (id + "\t" + login + "\t" + time + "\t" + priority + "\t" + size + " \t" + handle);
	}

}
