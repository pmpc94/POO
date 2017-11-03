package cloud;
/**
 * Conta do tipo <code>Premium</code>.
 * @author Francisco Godinho / Pedro Carolina
 */
public class PremiumClass extends AccountClass {
	
	public static final String TYPE = "Premium";
	public static final int SIZE = 5120;

	/**
	 * Cria uma nova conta do tipo <code>Premium</code>.
	 * @param name - o nome da conta.
	 */
	public PremiumClass(String name) {
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
	}

	public void share(Account other, String file) {
		other.upload(files[index(file)]);
	}
	
	
}
