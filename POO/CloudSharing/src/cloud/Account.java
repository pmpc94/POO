package cloud;

/**
 * Conta de <i>CloudSharing</i>, pode ser do tipo <code>Premium</code> ou <code>Basic</code>.
 * @author Francisco Godinho / Pedro Carolina
 */
public interface Account {

	/**
	 * Devolve o nome da conta.
	 * @return - o nome da conta.
	 */
	String getAccountName();
	/**
	 * Devolve o tipo da conta, se e' <code>Premium</code> ou <code>Basic</code>.
	 * @return - o tipo da conta.
	 */
	String getAccountType();
	/**
	 * Devolve a conta que realizou a ultima actualizacao a um  determinado ficheiro.
	 * @param file - o nome do ficheiro;
	 * @return - a conta que realizou a ultima actualizacao.
	 */
	String getLastUpdate(String file);
	/**
	 * Devolve o proprietario de um determinado ficheiro.
	 * @param file - o nome do ficheiro;
	 * @return - o proprietario do ficheiro.
	 */
	String getOwner(String file);
	/**
	 * Devolve o numero de ficheiros que a conta tem.
	 * @return - o numero de ficheiros da conta.
	 */
	int getNumberOfFiles();
	/**
	 * Devolve o numero de ficheiros partilhados (ficheiros cujo nome do 
	 * seu proprietario seja diferente do nome da conta) na conta.
	 * @return - o numero de ficheiros partilhados na conta.
	 */
	int getNumberOfSharedFiles();
	/**
	 * Devolve o tamanho disponivel na conta em MB.
	 * @return - o tamanho disponivel na conta.
	 */
	int getAvailableSize();
	/**
	 * Devolve o tamanho do ficheiro em MB.
	 * @param file - o nome do ficheiro;
	 * @return - o tamanho do ficheiro.
	 */
	int getFileSize(String file);
	/**
	 * Devolve se existe um determinado ficheiro na conta.
	 * @param file - o nome do ficheiro;
	 * @return - <code>true</code> ou <code>false</code> consoante exista o ficheiro na conta ou nao.
	 */
	boolean hasFile(String file);
	/**
	 * Adiciona um ficheiro 'a conta.
	 * @param file - o ficheiro a adicionar.
	 */
	void upload(File file);
	/**
	 * Realiza uma actualizacao a um determinado ficheiro.
	 * @param file - o ficheiro ao qual se pretende realizar a actualizacao;
	 * @param update - a conta que realiza a actualizacao.
	 */
	void update(String file, String update);
	/**
	 * Realiza uma partilha de um determinado ficheiro com outra conta.
	 * Este metodo nao tem implementacao na conta do tipo <code>Basic</code>,
	 * visto que esse tipo de contas nao consegue partilhar ficheiros com outras contas.
	 * @param other - a conta com quem se pretende realizar a partilha;
	 * @param file - o ficheiro a partilhar.
	 */
	void share(Account other, String file);
	/**
	 * Permite iterar sobre os ficheiros presentes na conta.
	 * @return - objecto <code>File</code>.
	 */
	FileIterator allFiles();

}
