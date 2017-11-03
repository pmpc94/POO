package cloud;
/**
 * Sistema de contas.
 * @author Francisco Godinho / Pedro Carolina
 */
public class CloudClass implements Cloud {
	
	private static final int DEFAULT = 15;
	
	private Account[] accounts;
	private int counter;
	
	/**
	 * Permite a criacao de um novo sistema de contas.
	 */
	public CloudClass() {
		accounts = new AccountClass[DEFAULT];
		counter = 0;
	}
	
	
	public void addAccount(String type, String account) {
		if (counter == accounts.length)
			resize();
		
		if (type.equalsIgnoreCase(PremiumClass.TYPE))
			accounts[counter++] = new PremiumClass(account);
		else if (type.equalsIgnoreCase(BasicClass.TYPE))
			accounts[counter++] = new BasicClass(account);
	}

	public void upload(String account, String file, int size) {
		File data = new FileClass(account, file, size);
		accounts[index(account)].upload(data);
	}

	public void shareFile(String account, String other, String file) {
		accounts[index(account)].share(accounts[index(other)], file);
	}

	public void updateFile(String owner, String update, String file) {
		accounts[index(owner)].update(file, update);
	}
	
	public String getLastUpdate(String owner, String file) {
		return accounts[index(owner)].getLastUpdate(file);
	}

	public int getNumberOfFiles(String account) {
		return accounts[index(account)].getNumberOfFiles();
	}

	public int getNumberOfSharedFiles(String account) {
		return accounts[index(account)].getNumberOfSharedFiles();
	}

	public int getAvailableSpace(String account) {
		return accounts[index(account)].getAvailableSize();
	}
	
	public int getFileSize(String account, String file) {
		return accounts[index(account)].getFileSize(file);
	}

	public boolean hasAccount(String account) {
		return index(account) >= 0;
	}

	public boolean hasFile(String account, String file) {
		return accounts[index(account)].hasFile(file);
	}

	public boolean canShare(String account) {
		return accounts[index(account)].getAccountType().equals(PremiumClass.TYPE);
	}

	public boolean isOwner(String account, String file) {
		boolean isOwner = false;
		if (hasFile(account, file))
			isOwner = accounts[index(account)].getOwner(file).equals(account);
		return isOwner;
	}

	public boolean isShared(String account, String other, String file) {
		boolean isShared = false;
		if (hasFile(other, file)) {
			String owner = accounts[index(other)].getOwner(file);
			if (owner.equals(account))
				isShared = true;
		}
		return isShared;
	}
	
	public int getNumberOfAccounts(){
		return counter;
	}

	public Iterator allAccounts() {
		return new IteratorClass(accounts, counter);
	}

	public Iterator typeAccounts(String type) {
		return new IteratorClass(type, accounts, counter);
	}
	
	public FileIterator allFiles(String account){
		return accounts[index(account)].allFiles();
	}
	
	
	/* Metodos auxiliares */
	
	private int index(String account) {
		int pos = -1;
		for (int i = 0; i < counter; i++)
			if (accounts[i].getAccountName().equals(account))
				pos = i;
		return pos;
	}
	
	private void resize() {
		Account tmp[] = new AccountClass[2*accounts.length];
		for (int i = 0; i < counter; i++)
			tmp[i] = accounts[i];
		accounts = tmp;
	}

}
