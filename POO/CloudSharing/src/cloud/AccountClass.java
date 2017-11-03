package cloud;
/**
 * Conta do sistema, pode ser do tipo <code>Premium</code> ou <code>Basic</code>.
 * @author Francisco Godinho / Pedro Carolina
 */
public abstract class AccountClass implements Account{
	
	private static final int DEFAULT = 20;
	
	protected String account;
	protected String type;
	protected File[] files;
	protected int counter;
	protected int storage;
	
	/**
	 * Permite a criacao de uma nova conta.
	 * @param account - o nome da conta;
	 * @param type - o tipo de conta.
	 */
	protected AccountClass(String account, String type) {
		this.account = account;
		this.type = type;
		files = new FileClass[DEFAULT];
		counter = 0;
		storage = 0;
	}
	
	public abstract void upload(File file);
	public abstract void share(Account other, String file);
	public abstract int getAvailableSize();
	
	
	public String getAccountName() {
		return account;
	}
	
	public String getAccountType() {
		return type;
	}

	public String getLastUpdate(String file) {
		return files[index(file)].getLastUpdate();
	}
	
	public String getOwner(String file) {
		return files[index(file)].getOwner();
	}

	public int getNumberOfFiles() {
		return counter - getNumberOfSharedFiles();
	}

	public int getNumberOfSharedFiles() {
		int shared_files = 0;
		for (int i = 0; i < counter; i++)
			if (!files[i].getOwner().equals(account))
				shared_files++;
		return shared_files;
	}

	public int getFileSize(String file) {
		return files[index(file)].getSize();
	}

	public boolean hasFile(String file) {
		return index(file) >= 0;
	}

	public void update(String file, String update) {
		files[index(file)].update(update);
	}

	public FileIterator allFiles() {
		return new FileIteratorClass(files, counter);
	}
	
	
	/* Metodos auxiliares */
	protected int index(String file) {
		int pos = -1;
		for (int i = 0; i < counter; i++)
			if (files[i].getName().equals(file))
				return i;
		return pos;
	}
	
	protected void resize() {
		File tmp[] = new FileClass[2*files.length];
		for (int i = 0; i < counter; i++)
			tmp[i] = files[i];
		files = tmp;
	}

}
