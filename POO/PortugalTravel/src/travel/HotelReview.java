package travel;
/**
 * Permite criar <i>reviews</i> de hoteis.
 * @author Francisco Godinho / Pedro Carolina
 */
public interface HotelReview extends Review {
	
	/**
	 * Devolve uma <code>String</code> com a classificacao atribuida 'a qualidade do servico do hotel.
	 * @return a classificacao do servico do restaurante.
	 */
	String getServiceRating();
	/**
	 * Devolve uma <code>String</code> com a classificacao atribuida ao local em que o hotel se encontra.
	 * @return a classificacao do local do restaurante.
	 */
	String getLocationRating();

}
