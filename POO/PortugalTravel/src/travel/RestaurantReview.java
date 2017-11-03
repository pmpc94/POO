package travel;
/**
 * Permite criar <i>reviews</i> de restaurantes.
 * @author Francisco Godinho / Pedro Carolina
 */
public interface RestaurantReview extends Review {

	/**
	 * Devolve uma <code>String</code> com a classificacao atribuida 'a comida do restaurante.
	 * @return a classificacao da comida do restaurante.
	 */
	String getCuisineRating();
	/**
	 * Devolve um <code>String</code> com a classificacao atribuida ao ambiente do restaurante.
	 * @return a classificacao do ambiente do restaurante.
	 */
	String getAmbianceRating();
	
}
