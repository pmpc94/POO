package cloud;

/**
 * Sistema de contas <i>CloudSharing</i>.
 * @author Francisco Godinho / Pedro Carolina
 */
public interface Cloud {

	/**
	 * Adiciona uma nova conta ao sistema do tipo <code>Premium</code> ou do tipo <code>Basic</code>.
	 * @param type - o tipo de conta adicionar;
	 * @param account - o nome da conta.
	 */
	void addAccount(String type, String account);
	/**
	 * Adiciona um novo ficheiro a uma determinada conta.
	 * @param account - o nome da conta;
	 * @param file - o nome do ficheiro;
	 * @param size - o tamanho do ficheiro.
	 */
	void upload(String account, String file, int size);
	/**
	 * Partilha um ficheiro de uma conta com outra.
	 * @param owner - o nome da conta proprietaria do ficheiro;
	 * @param sharer - o nome da conta com que a partilha se realiza;
	 * @param file - o nome do ficheiro a partilhar.
	 */
	void shareFile(String owner, String sharer, String file);
	/**
	 * Realiza uma actualizacao a um determinado ficheiro.
	 * @param owner - o proprietario do ficheiro;
	 * @param updater - o nome da conta que realiza a partilha;
	 * @param file - o nome do ficheiro a actualizar.
	 */
	void updateFile(String owner, String updater, String file);
	/**
	 * Devolve o nome da conta que realizou a ultima actualizacao a um determinado ficheiro.
	 * @param account - o nome da conta proprietaria do ficheiro;
	 * @param file - o nome do ficheiro;
	 * @return - o nome da conta que realizou a ultima actualizacao.
	 */
	String getLastUpdate(String account, String file);
	/**
	 * Devolve o numero de ficheiros de uma determinada conta.
	 * @param account - o nome da conta;
	 * @return - o numero de ficheiros na conta.
	 */
	int getNumberOfFiles(String account);
	/**
	 * Devolve o numero de ficheiros partilhados numa determinada conta 
	 * (ficheiros cujo nome do seu proprietario seja diferente do nome da conta).
	 * @param account - o nome da conta;
	 * @return - o numero de ficheiros partilhados na conta.
	 */
	int getNumberOfSharedFiles(String account);
	/**
	 * Devolve o espaco disponivel para armazenamento em MB numa determinada conta.
	 * @param account - o nome da conta;
	 * @return - o espaco disponivel na conta.
	 */
	int getAvailableSpace(String account);
	/**
	 * Devolve o tamanho em MB de um ficheiro especifico de uma determinada conta.
	 * @param account - o nome da conta;
	 * @param file - o nome do ficheiro;
	 * @return - o tamanho do ficheiro.
	 */
	int getFileSize(String account, String file);
	/**
	 * Devolve o numero total de contas existentes no sistema.
	 * @return - o numero de contas existentes.
	 */
	int getNumberOfAccounts();
	/**
	 * Devolve se existe uma determinada conta no sistema ou nao.
	 * @param account - o nome da conta;
	 * @return - <code>true</code> ou <code>false</code> consoante exista a conta no sistema ou nao.
	 */
	boolean hasAccount(String account);
	/**
	 * Devolve se existe um ficheiro especifico numa determinada conta.
	 * @param account - o nome da conta;
	 * @param file - o nome do ficheiro;
	 * @return - <code>true</code> ou <code>false</code> consoante exista o ficheiro na conta ou nao.
	 */
	boolean hasFile(String account, String file);
	/**
	 * Verifica se uma determinada conta pode partilhar ficheiros com outras contas ou nao atraves do seu tipo.
	 * @param account - o nome da conta;
	 * @return - <code>true</code> ou <code>false</code> consoante a conta possa partilhar ficheiros ou nao.
	 */
	boolean canShare(String account);
	/**
	 * Verifica se uma determinada conta e' proprietaria de um ficheiro especifico.
	 * @param account - o nome da conta;
	 * @param file - o nome do ficheiro;
	 * @return - <code>true</code> ou <code>false</code> consoante a conta seja proprietaria do ficheiro ou nao.
	 */
	boolean isOwner(String account, String file);
	/**
	 * Verifica se um determinado ficheiro se encontra partilhado entre duas contas.
	 * @param account - o nome da conta proprietaria;
	 * @param other - o nome da outra conta;
	 * @param file - o nome do ficheiro;
	 * @return - <code>true</code> ou <code>false</code> consoante o ficheiro se encontre partilhado entre ambas as contas ou nao.
	 */
	boolean isShared(String account, String other, String file);
	/**
	 * Permite iterar sobre as contas presentes no sistema.
	 * @return - objecto <code>Account</code>.
	 */
	Iterator allAccounts();
	/**
	 * Permite iterar sobre as contas de um dado tipo presentes no sistema.
	 * @param type - o tipo de contas a iterar;
	 * @return - objecto <code>Account</code> de um determinado tipo.
	 */
	Iterator typeAccounts(String type);
	/**
	 * Permite iterar sobre os ficheiros de uma determinada conta.
	 * @param account - o nome da conta sobre a qual se pretende iterar os ficheiros;
	 * @return - objecto <code>File</code>.
	 */
	FileIterator allFiles(String account);
	
}
