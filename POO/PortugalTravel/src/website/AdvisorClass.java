package website;
/**
 * Subclasse da classe <code>UserClass</code>, classe dos utilizadores <code>Advisor</code>.
 * @author Francisco Godinho / Pedro Carolina
 */
public class AdvisorClass extends UserClass implements Advisor {

	public static final String ADVISOR = "advisor";
	private static int keygen = 1;
	
	public AdvisorClass(String username, String name, String location, String email) {
		super(ADVISOR, username, name, location, email);
	}
	
	public String generateAdvisorPassword() {
		password = ADVISOR + keygen++;
		return password;
	}

}
