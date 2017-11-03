package website;
/**
 * Permite a criacao de utilizadores com a possibilidade de realizar <i>reviews</i> e adicionar actividades.
 * @author Francisco Godinho / Pedro Carolina
 */
public interface Author extends User {
	
	/**
	 * Gera uma <i>password</i> para utilizadores do tipo <code>Author</code>.
	 * @return uma <code>String</code> com a <i>password</i>.
	 */
	String generateAuthorPassword();
	
}
