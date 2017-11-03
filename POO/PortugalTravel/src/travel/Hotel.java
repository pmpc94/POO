package travel;
/**
 * Permite a criacao de hoteis.
 * @author Francisco Godinho / Pedro Carolina
 */
public interface Hotel extends Activity {

	/**
	 * Realiza uma <i>review</i> ao hotel.
	 * Atribui tambem pontuacoes respectivas 'a classificacao do hotel.
	 * @param rev - a <i>review</i> a adicionar;
	 * @param advisor - o autor da <i>review</i>.
	 */
	void reviewHotel(HotelReview rev, String advisor);
	/**
	 * Devolve uma <code>String</code> com a classificacao media da qualidade do servico do hotel com base nas <i>reviews</i> feitas.
	 * @return a classificacao media da servico do hotel.
	 */
	String getAvgServiceRating();
	/**
	 * Devolve uma <code>String</code> com a classificacao media da localizacao do hotel com base nas <i>reviews</i> feitas.
	 * @return a classificacao media da localizacao do hotel.
	 */
	String getAvgLocationRating();
	/**
	 * Devolve o preco do hotel por noite.
	 * @return o preco do hotel por noite.
	 */
	int getPrice();
	/**
	 * Devolve o numero de estrelas do hotel.
	 * @return o numero de estrelas do hotel.
	 */
	int getStars();

}
