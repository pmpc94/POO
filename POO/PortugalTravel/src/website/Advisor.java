package website;
/**
 * Permite a criacao de utilizadores com a possibilidade de realizarem <i>reviews</i>.
 * @author Francisco Godinho / Pedro Carolina
 */
public interface Advisor extends User {

	/**
	 * Gera uma <i>password</i> para utilizadores do tipo <code>Advisor</code>.
	 * @return uma <code>String</code> com a <i>password</i>.
	 */
	String generateAdvisorPassword();
	
}
