package travel;
import java.util.Iterator;
/**
 * Permite a criacao de actividades.
 * @author Francisco Godinho / Pedro Carolina
 */
public interface Activity extends Comparable<Activity> {

	/**
	 * Devolve uma <code>String</code> com o nome do autor da actividade.
	 * @return o autor da actividade.
	 */
	String getAuthor();
	/**
	 * Devolve uma <code>String</code> com o nome da actividade.
	 * @return o nome da actividade.
	 */
	String getName();
	/**
	 * Devolve uma <code>String</code> com uma breve descricao da actividade.
	 * @return a descricao da actividade.
	 */
	String getDescription();
	/**
	 * Devolve uma <code>String</code> com o local da actividade.
	 * @return o local da actividade.
	 */
	String getLocation();
	/**
	 * Devolve uma media da pontuacao da actividade com base nas suas <i>reviews</i>.
	 * @return a pontuacao media da actividade.
	 */
	int getAvgScore();
	/**
	 * Devolve o numero de vezes que uma determinada classificacao foi atribuida 'a actividade.
	 * @param grade - a classificacao a procurar;
	 * @return a frequencia com que a classificacao foi atribuida.
	 */
	int getNumberOfRatings(String grade);
	/**
	 * Devolve o numero total de <i>reviews</i> realizadas desde que se inicia a simulacao, 
	 * tendo em conta as <i>reviews</i> realizadas pelo mesmos utilizadores que tenham sido substituidas.
	 * @return o numero total de <i>reviews</i> realizadas desde o inicio do programa.
	 */
	int getTotalReviews();
	/**
	 * Devolve o numero de <i>reviews</i> realizadas. 
	 * <i>Reviews</i> que tenham sido substituidas nao sao contabilizadas.
	 * @return o numero de <i>reviews</i> realizadas.
	 */
	int getCurrentReviews();
	/**
	 * Devolve um objecto do tipo <code>Review</code> atraves do nome do autor da mesma, 
	 * de modo a obter uma <i>review</i> especifica dentro de todas as que estao na actividade.
	 * @param username - o nome do autor da <i>review</i>;
	 * @return objecto do tipo <code>Review</code>.
	 */
	Review getReview(String username);
	/**
	 * Devolve se existe ou nao uma <i>review</i> de um determinado autor.
	 * @param username - o nome do autor da <i>review</i>;
	 * @return <code>true</code> caso exista a <i>review</i>, <code>false</code> caso contrario.
	 */
	boolean hasReview(String username);
	/**
	 * Ordena por classificacao e devolve um iterador de todas as <i>reviews</i> feitas 'a actividade.
	 * @return iterador de objectos do tipo <code>Review</code>.
	 */
	Iterator<Review> listReviews();
	
}
