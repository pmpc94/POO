package cloud;
/**
 * Conta do tipo <code>Basic</code>.
 * @author Francisco Godinho / Pedro Carolina
 */
public class BasicClass extends AccountClass {

	public static final String TYPE = "Basic";
	public static final int SIZE = 2048;
	
	/**
	 * Cria uma nova conta do tipo <code>Basic</code>.
	 * @param name - o nome da conta.
	 */
	public BasicClass(String name) {
		super(name, TYPE);
	}

	public int getAvailableSize() {
		return SIZE - storage;
	}
	
	public void upload(File file) {
		if (counter == files.length)
			resize();
		files[counter++] = file;
		
		if (file.getOwner().equals(account))
			storage += file.getSize();
		else
			storage += file.getSize()/2;
	}
	
	public void share(Account other, String file) {}

}
