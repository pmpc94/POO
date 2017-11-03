package cloud;
/**
 * Iterador de contas.
 * @author Francisco Godinho / Pedro Carolina
 */
public class IteratorClass implements Iterator{

	private Account[] accounts;
	private int counter;
	private int current;
	private String type;
	
	/**
	 * Cria um novo iterador que permite iterar sobre todas as contas do sistema.
	 * @param accounts - vector de contas;
	 * @param counter - contador do vector de contas.
	 */
	public IteratorClass(Account[] accounts, int counter) {
		this.counter = counter;
		this.accounts = accounts;
		this.type = null;
		init();
	}
	
	/**
	 * Cria um novo iterador que permite iterar sobre um tipo especifico de contas presentes no sistema.
	 * @param type - o tipo de contas sobre as quais iterar;
	 * @param accounts - vector de contas;
	 * @param counter - contador do vector de contas.
	 */
	public IteratorClass(String type, Account[] accounts, int counter){
		this.counter = counter;
		this.accounts = accounts;
		this.type = type;
		init();
	}
	
	
	public void init() {
		current = 0;
		if (type != null)
			while((current < counter) && !accounts[current].getAccountType().equalsIgnoreCase(type))
				current++;
	}
	
	public boolean hasNext() {
		return (current < counter);
	}
	
	public Account next() {
		Account aux = accounts[current++];
		if (type != null)
			while((current < counter) && !accounts[current].getAccountType().equalsIgnoreCase(type))
				current++;
		return aux;
	}

}
