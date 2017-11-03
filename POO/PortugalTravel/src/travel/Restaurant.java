package travel;
/**
 * Permite a criacao de restaurantes.
 * @author Francisco Godinho / Pedro Carolina
 */
public interface Restaurant extends Activity {
	
	/**
	 * Realiza uma <i>review</i> ao restaurante.
	 * Atribui tambem pontuacoes respectivas 'a classificacao do restaurante.
	 * @param rev - a <i>review</i> a adicionar;
	 * @param advisor - o autor da <i>review</i>.
	 */
	void reviewRestaurant(RestaurantReview rev, String advisor);
	/**
	 * Devolve uma <code>String</code> com a classificacao media da qualidade da comida do restaurante com base nas <i>reviews</i> feitas.
	 * @return a classificacao media da comida do restaurante.
	 */
	String getAvgCuisineRating();
	/**
	 * Devolve uma <code>String</code> com a classificacao media do ambiente do restaurante com base nas <i>reviews</i> feitas.
	 * @return a classificacao media do ambiente do restaurante.
	 */
	String getAvgAmbianceRating();
	/**
	 * Devolve uma <code>String</code> com o tipo de cozinha do restaurante.
	 * @return o tipo de cozinha do restaurante.
	 */
	String getCuisine();
	/**
	 * Devolve uma <code>String</code> com o escalao de preco do restaurante.
	 * @return o escalao de preco do restaurante.
	 */
	String getPrice();

}
