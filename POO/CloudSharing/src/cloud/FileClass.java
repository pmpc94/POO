package cloud;
/**
 * Ficheiro que possui nome, tamanho e nome de proprietario.
 * @author Francisco Godinho / Pedro Carolina
 */
public class FileClass implements File {

	private String owner;
	private String name;
	private int size;
	private String update;	
	
	/**
	 * Cria um novo ficheiro, atribuindo-lhe um nome, um tamanho, um 
	 * proprietario e uma conta que lhe realiza a ultima actualizacao.
	 * @param owner - o nome do proprietario;
	 * @param name - o nome a dar ao ficheiro;
	 * @param size - o tamanho do ficheiro.
	 */
	public FileClass(String owner, String name, int size) {
		this.owner = owner;
		this.name = name;
		this.size = size;
		update = owner;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return size;
	}
	
	public String getLastUpdate() {
		return update;
	}
	
	public void update(String update) {
		this.update = update;
	}
	
}
