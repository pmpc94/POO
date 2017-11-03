package cloud;
/**
 * Iterador de ficheiros.
 * @author Francisco Godinho / Pedro Carolina
 */
public class FileIteratorClass implements FileIterator {

	private File[] files;
	private int counter;
	private int currentFile;
	
	/**
	 * Cria um novo iterador de ficheiros.
	 * @param files - vector de ficheiros;
	 * @param counter - contador do vector de ficheiros.
	 */
	public FileIteratorClass(File[] files, int counter){
		this.counter = counter;
		this.files = files;
		initFile();
	}
	
	public void initFile() {
		currentFile = 0;
	}

	
	public boolean hasNextFile() {
		return (currentFile < counter);
	}

	
	public File nextFile() {
		File aux = files[currentFile++];
		return aux;
	}

}
