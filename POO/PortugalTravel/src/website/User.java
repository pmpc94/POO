package website;
/**
 * Permite a criacao de utilizadores.
 * @author Francisco Godinho / Pedro Carolina
 */
public interface User {
	
	/**
	 * Devolve uma <code>String</code> com o tipo de conta do utilizador.
	 * @return o tipo de conta do utilizador.
	 */
	String getType();
	/**
	 * Devolve uma <code>String</code> com o <i>username</i> do utilizador.
	 * @return o <i>username</i> do utilizador.
	 */
	String getUsername();
	/**
	 * Devolve uma <code>String</code> com o nome do utilizador.
	 * @return o nome do utilizador.
	 */
	String getName();
	/**
	 * Devolve uma <code>String</code> com a morada do utilizador.
	 * @return a morada do utilizador.
	 */
	String getLocation();
	/**
	 * Devolve uma <code>String</code> com o e-mail do utilizador.
	 * @return o e-mail do utilizador.
	 */
	String getEmail();
	/**
	 * Verifica se a <i>password</i> inserida pelo utilizador corresponde 'a da conta.
	 * @param password - <i>password</i> a inserir;
	 * @return <code>true</code> se a <i>password</i> corresponder 'a conta, <code>false</code> caso contrario.
	 */
	boolean checkPassword(String password);

}
