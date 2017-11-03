package website;
/**
 * Classe abstracta de utilizadores.
 * @author Francisco Godinho / Pedro Carolina
 */
public abstract class UserClass implements User {

	private String type, username, name, location, email;
	protected String password;
	
	protected UserClass(String type, String username, String name, String location, String email) {
		this.type = type;
		this.username = username;
		this.name = name;
		this.location = location;
		this.email = email;
	}
	
	public String getType() {
		return type;
	}
	
	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public String getEmail() {
		return email;
	}
	
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

}
