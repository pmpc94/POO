package cloud;

/**
 * Permite iterar sobre os ficheiros de uma conta.
 * @author Francisco Godinho / Pedro Carolina
 */
public interface FileIterator {
	
	/**
	 * Inicia o iterador na primeira posicao do vector.
	 */
	void initFile();
	/**
	 * Verifica se existe um objecto do tipo <code>File</code> na posicao seguinte do vector.
	 * @return - <code>true</code> ou <code>false</code> consoante exista o objecto na posicao seguinte ou nao.
	 */
	boolean hasNextFile();
	/**
	 * Devolve um objecto <code>File</code> e avanca de posicao no vector.
	 * @return - objecto <code>File</code>.
	 */
	File nextFile();

}
