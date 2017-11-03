import cloud.*;
import java.util.Scanner;
/**
 * Main.
 * @author Francisco Godinho / Pedro Carolina
 */
public class Main {

	/* Mensagens de sistema */
	public static final String ACCOUNT_ADDED = "> Account was added.\n";
	public static final String ACCOUNT_EXISTS = "> Account already exists.\n";
	public static final String ACCOUNT_UNKNOWN = "> Account does not exist.\n";
	public static final String ACCOUNT_SMALLER = "> Account with least free space: ";
	public static final String ACCOUNT_OVERFLOW = "> File size exceeds account capacity.\n";
	public static final String ACCOUNT_BASIC = "> Account does not allow file sharing.\n";
	public static final String NO_ACCOUNTS = "> No accounts.\n";
	public static final String FILE_ADDED = "> File uploaded into account.\n";
	public static final String FILE_UPDATED = "> File was updated.\n";
	public static final String FILE_SHARED = "> File was shared.\n";
	public static final String FILE_EXISTS = "> File already exists in the account.\n";
	public static final String FILE_UNKNOWN = "> File does not exist.\n";
	public static final String SHARE_EXISTS = "> File already shared.\n";
	public static final String SHARE_UNKNOWN = "> File not shared.\n";
	public static final String PRINT_UPDATE = "> Last update: ";
	public static final String PREMIUM_ACCOUNTS = "> Premium accounts:";
	public static final String BASIC_ACCOUNTS = "> Basic accounts:";
	public static final String ALL_ACCOUNTS ="> All accounts:";
	public static final String END = "> Exiting...\n";
	
	
	
	public static final String ALL = "ALL";
	public static final String FILES = "FILES";
	
	
	public static void main(String[] args) {
		menu();
	}
	
	public enum Commands {
		ADD, UPLOAD, SHARE, UPDATE, MINSPACE, LIST, LASTUPDATE, EXIT
	}
	
	/* Interpretador de comandos. */
	private static void menu() {
		Scanner in = new Scanner(System.in);
		Cloud cloud = new CloudClass();
		Commands comm = Commands.valueOf(in.next());
		
		while (comm != Commands.EXIT) {
			switch (comm) {
				case ADD:
					createAccount(in, cloud);
					break;
				case UPLOAD:
					uploadFile(in, cloud);
					break;
				case SHARE:
					shareFile(in, cloud);
					break;
				case UPDATE:
					updateFile(in, cloud);
					break;
				case MINSPACE:
					minSpace(cloud);
					break;
				case LIST:
					String method = in.next();
					if(method.equals(ALL))
						listAll(cloud);
					else if(method.equalsIgnoreCase(PremiumClass.TYPE) || method.equalsIgnoreCase(BasicClass.TYPE))
						listByType(method.toLowerCase(), cloud);
					else if(method.equals(FILES))
						listFiles(in, cloud);
					break;
				case LASTUPDATE:
					lastUpdate(in, cloud);
					break;
				case EXIT:
					break;
			}
			comm = Commands.valueOf(in.next());
		}
		System.out.println(END);
		in.close();
	}
	
	
	/**
	 * Permite criar uma conta nova no sistema. Necessita do nome da conta e do seu tipo.
	 * O metodo falha caso a conta ja exista no sistema.
	 */
	private static void createAccount(Scanner in, Cloud cloud){
		String account = in.next();
		String type = in.next();
		
		if (cloud.hasAccount(account))
			System.out.println(ACCOUNT_EXISTS);
		else {
			cloud.addAccount(type, account);
			System.out.println(ACCOUNT_ADDED);
		}
	}
	
	/**
	 * Permite adicionar um ficheiro a uma determinada conta. Necessita do nome da conta 'a qual se
	 * pretende adicionar o ficheiro, do nome e do tamanho do ficheiro a adicionar.
	 * O metodo falha se a conta nao existir no sistema, o ficheiro a adicionar ja existir na conta
	 * ou o tamanho do ficheiro exceder o tamanho disponivel em conta.
	 */
	private static void uploadFile(Scanner in, Cloud cloud){
		String account = in.next();
		String file = in.next();
		int size = in.nextInt();
		
		if (!cloud.hasAccount(account))
			System.out.println(ACCOUNT_UNKNOWN);
		else if (cloud.hasFile(account, file))
			System.out.println(FILE_EXISTS);
		else if (size > cloud.getAvailableSpace(account))
			System.out.println(ACCOUNT_OVERFLOW);
		else {
			cloud.upload(account, file, size);
			System.out.println(FILE_ADDED);
		}
	}
	
	/**
	 * Permite partilhar um ficheiro especifico entre duas contas. Necessita do nome da conta proprietaria 
	 * do ficheiro, do nome da conta com a qual se pretende realizar a partilha e do nome do ficheiro.
	 * O metodo falha caso alguma das contas nao exista, a conta nao conter o ficheiro, a conta a realizar 
	 * a partilha nao for do tipo <code>Premium</code>, a partilha ja exista ou o tamanho do ficheiro a 
	 * partilhar exceda o tamanho disponivel da conta a receber o ficheiro.
	 */
	private static void shareFile(Scanner in, Cloud cloud){
		String account = in.next();
		String other = in.next();
		String file = in.next();
		
		if (!cloud.hasAccount(account) || !cloud.hasAccount(other))
			System.out.println(ACCOUNT_UNKNOWN);
		else if (!cloud.hasFile(account, file))
			System.out.println(FILE_UNKNOWN);
		else if (!cloud.canShare(account))
			System.out.println(ACCOUNT_BASIC);
		else if (cloud.isShared(account, other, file))
			System.out.println(SHARE_EXISTS);
		else if (!cloud.canShare(other) && (cloud.getFileSize(account, file)/2 > cloud.getAvailableSpace(other)))
			System.out.println(ACCOUNT_OVERFLOW);
		else {
			cloud.shareFile(account, other, file);
			System.out.println(FILE_SHARED);
		}
	}
	
	/**
	 * Permite realizar uma actualizacao a um determinado ficheiro. Precisa do nome da conta proprietaria
	 * do ficheiro, do nome da conta que pretende realizar a actualizacao e do nome do ficheiro.
	 * O metodo falha caso uma das contas nao exista, a conta inserida como proprietaria nao seja proprietaria
	 * do ficheiro ou o ficheiro nao se encontre partilhado entre as duas contas.
	 */
	private static void updateFile(Scanner in, Cloud cloud){
		String account = in.next();
		String update = in.next();
		String file = in.next();
		
		if (!cloud.hasAccount(account) || !cloud.hasAccount(update))
			System.out.println(ACCOUNT_UNKNOWN);
		else if (!cloud.isOwner(account, file))
			System.out.println(FILE_UNKNOWN);
		else if (!cloud.isShared(account, update, file))
			System.out.println(SHARE_UNKNOWN);
		else {
			cloud.updateFile(account, update, file);
			System.out.println(FILE_UPDATED);
		}
	}
	
	/**
	 * Devolve a conta que tem menos espaco disponivel para armazenamento de ficheiros.
	 * O metodo falha caso nao existam contas registadas no sistema.
	 */
	private static void minSpace(Cloud cloud){
		Iterator it = cloud.allAccounts();
		Account aux = null;
		String min = null;
		int size = PremiumClass.SIZE + 1;
		
		if (cloud.getNumberOfAccounts() == 0)
			System.out.println(NO_ACCOUNTS);
		else {
			while (it.hasNext()) {
				aux = it.next();
				if (aux.getAvailableSize() < size) {
					size = aux.getAvailableSize();
					min = aux.getAccountName();
				}
			}
			System.out.println(ACCOUNT_SMALLER + min + '\n');
		}
		
	}
	
	/**
	 * Permite listar todos os ficheiros de uma determinada conta. Necessita do seu nome.
	 * O metodo falha caso a conta nao exista no sistema.
	 */
	private static void listFiles(Scanner in, Cloud cloud){
		String account = in.next();
		
		if (!cloud.hasAccount(account))
			System.out.println(ACCOUNT_UNKNOWN);
		else {
			File file = null;
			FileIterator it = cloud.allFiles(account);
			System.out.println("> Account has " + cloud.getNumberOfFiles(account) + " files:");
			while (it.hasNextFile()) {
				file = it.nextFile();
				if (file.getOwner().equals(account))
					System.out.println("> " + file.getName() + " (" + file.getSize() + " MB)");
			}
			System.out.println("> Account has " + cloud.getNumberOfSharedFiles(account) + " shared files:");
			if (cloud.getNumberOfSharedFiles(account) != 0) {
				it = cloud.allFiles(account);
				while (it.hasNextFile()) {
					file = it.nextFile();
					if(!file.getOwner().equals(account))
						System.out.println("> " + file.getName() + " (" + file.getSize() + " MB)");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * Permite listar todas as contas existentes no sistema.
	 */
	private static void listAll(Cloud cloud) {
		Iterator it = cloud.allAccounts();
		Account aux = null;
		
		System.out.println(ALL_ACCOUNTS);
		while(it.hasNext()){
			aux = it.next();
			System.out.println("> " + aux.getAccountName() + " (" + aux.getAccountType() + ")");
		}
		System.out.println();
	}
	
	/**
	 * Permite listar todas as contas de um dado tipo, <code>Premium</code> ou <code>Basic</code>, existentes no sistema.
	 */
	private static void listByType(String type, Cloud cloud) {
		Iterator it = cloud.typeAccounts(type);
		Account aux = null;
		
		if(type.equalsIgnoreCase(PremiumClass.TYPE))
			System.out.println(PREMIUM_ACCOUNTS);
		else if(type.equalsIgnoreCase(BasicClass.TYPE))
			System.out.println(BASIC_ACCOUNTS);

		while(it.hasNext()) {
			aux = it.next();
			System.out.println("> " + aux.getAccountName());
		}
		System.out.println();
					
	}
	
	/**
	 * Permite consultar a conta que realizou a ultima actualizacao a um determinado ficheiro. 
	 * Necessita do nome da conta proprietaria do ficheiro e do nome do ficheiro.
	 * O metodo falha caso a conta nao se encontre registada no sistema, ou caso o ficheiro
	 * nao se encontre presente na conta.
	 */
	private static void lastUpdate(Scanner in, Cloud cloud) {
		String account = in.next();
		String file = in.next();
		
		if (!cloud.hasAccount(account))
			System.out.println(ACCOUNT_UNKNOWN);
		else if (!cloud.hasFile(account, file))
			System.out.println(FILE_UNKNOWN);
		else
			System.out.println(PRINT_UPDATE + cloud.getLastUpdate(account, file) + '\n');
	}
	
	
}
