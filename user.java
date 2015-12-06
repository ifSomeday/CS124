/*
 * Will Rice
 * CS 124
 * all code written by Will Rice.
 * Copying code without permission is expressly prohibited.
 */
public class User {


	private String password;
	private String username;
	private boolean status;
	private boolean tombstone;
	
	public User(){
		
	}
	
	public User(String username, String password, boolean status, boolean tombstone){
		this.username = username;
		this.password = password;
		this.status = status;
		this.tombstone = tombstone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isTombstone() {
		return tombstone;
	}

	public void setTombstone(boolean tombstone) {
		this.tombstone = tombstone;
	}

	@Override
	public String toString() {
		return "User [password=" + password + ", username=" + username + ", status=" + status + ", tombstone="
				+ tombstone + "]";
	}
	
}
