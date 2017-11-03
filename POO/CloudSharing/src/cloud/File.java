package cloud;

/**
 * Um ficheiro a adicionar 'a conta de uma determinada pessoa, pode ser 
 * partilhado por diversas pessoas, tendo, no entanto, um unico dono.
 * @author Francisco Godinho / Pedro Carolina
 */
public interface File {
	
	/**
	 * Devolve o nome do ficheiro.
	 * @return - o nome do ficheiro.
	 */
	String getName();
	/**
	 * Devolve o proprietario do ficheiro.
	 * @return - o proprietario do ficheiro.
	 */
	String getOwner();
	/**
	 * Devolve o utilizador que realizou a ultima actualizacao.
	 * @return - o utilizador que realizou a ultima actualizacao.
	 */
	String getLastUpdate();
	/**
	 * Devolve o tamanho do ficheiro.
	 * @return - o tamanho do ficheiro em MB.
	 */
	int getSize();
	/**
	 * Realiza uma actualizacao ao ficheiro.
	 * @param account - nome da conta a realizar a actualizacao.
	 */
	void update(String account);
	
}
