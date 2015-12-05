/*
 * Will Rice
 * CS 124
 * all code written by Will Rice.
 * Copying code without permission is expressly prohibited.
 */
public class user {


	String password;
	String username;
	boolean status;
	boolean tombstone;
	
	public user(){
		
	}
	
	public user(String username, String password, boolean status, boolean tombstone){
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
}
