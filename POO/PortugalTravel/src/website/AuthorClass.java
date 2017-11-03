package website;
/**
 * Subclasse da classe <code>UserClass</code>, classe dos utilizadores <code>Author</code>.
 * @author Francisco Godinho / Pedro Carolina
 */
public class AuthorClass extends UserClass implements Author {

	public static final String AUTHOR = "author";
	private static int keygen = 1;
	
	public AuthorClass(String username, String name, String location, String email) {
		super(AUTHOR, username, name, location, email);
	}
	
	public String generateAuthorPassword() {
		password = AUTHOR + keygen++;
		return password;
	}

}
