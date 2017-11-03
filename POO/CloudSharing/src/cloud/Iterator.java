package cloud;

/**
 * Permite iterar no vector de contas "CloudClass".
 * @author Francisco Godinho / Pedro Carolina
 */
public interface Iterator {
	
	/**
	 * Inicia o iterador na primeira posicao do vector.
	 */
	void init();
	/**
	 * Verifica se existe um objecto do tipo <code>Account</code> na posicao seguinte do vector.
	 * @return - <code>true</code> ou <code>false</code> consoante exista o objecto na posicao seguinte ou nao.
	 */
	boolean hasNext();
	/**
	 * Devolve um objecto <code>Account</code> e avanca de posicao no vector.
	 * @return - objecto <code>Account</code>.
	 */
	Account next();
	
}
