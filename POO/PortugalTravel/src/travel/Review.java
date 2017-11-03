package travel;
/**
 * Permite classificar actividades pelos seus atributos.
 * @author Francisco Godinho / Pedro Carolina
 */
public interface Review extends Comparable<Review> {
	
	/**
	 * Devolve uma <code>String</code> com o nome do autor que criou a <i>review</i>.
	 * @return o nome do autor da <i>review</i>.
	 */
	String getReviewer();
	/**
	 * Devolve uma <code>String</code> com a classificacao que o autor atribuiu 'a actividade.
	 * @return a classificacao correspondente 'a <i>review</i>.
	 */
	String getRating();
	/**
	 * Devolve uma <code>String</code> com o comentario do autor 'a actividade.
	 * @return o comentario do autor.
	 */
	String getReview();
	/**
	 * Adiciona um <i>like</i> 'a <i>review</i>.
	 */
	void like();
	/**
	 * Devolve o numero de <i>likes</i> da <i>review</i>.
	 * @return o numero de <i>likes</i> da <i>review</i>.
	 */
	int getLikes();
	
}
