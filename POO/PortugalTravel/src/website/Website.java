package website;
import java.util.*;
import travel.*;
/**
 * <i>Website</i> de <i>PortugalTravel</i>
 * @author Francisco Godinho / Pedro Carolina
 */
public interface Website {
	
	/**
	 * Regista um utilizador no <i>site</i>, devolvendo uma <code>String</code> 
	 * com a <i>password</i> para o mesmo utilizar para entrar na conta.
	 * @param type - o tipo de conta a criar;
	 * @param username - o <i>username</i> pretendido;
	 * @param name - o nome do utilizador;
	 * @param location - a morada do utilizador;
	 * @param email - o e-mail do utilizador;
	 * @return a <i>password</i> da conta criada.
	 */
	String register(String type, String username, String name, String location, String email);
	/**
	 * Devolve o utilizador com sessao iniciada.
	 * @return o utilizador com sessao iniciada.
	 */
	User getCurrentUser();
	/**
	 * Entra na conta do respectivo <i>username</i> dado pelo utilizador.
	 * O metodo nao faz nada se a <i>password</i> dada nao corresponder 'a conta em questao.
	 * @param username - o <i>username</i> da conta;
	 * @param password - a <i>password</i> da conta.
	 */
	void login(String username, String password);
	/**
	 * Termina a sessao de qualquer conta activa no sistema.
	 */
	void logout();
	/**
	 * Verifica se existe alguem com sessao iniciada no sistema.
	 * @return <code>true</code> se alguma conta tiver com a sessao iniciada, <code>false</code> caso contrario.
	 */
	boolean someoneLoggedIn();
	/**
	 * Verifica se uma determinada conta esta registada no sistema.
	 * @param username - o <i>username</i> da conta;
	 * @return <code>true</code> se a conta em questao estiver registada no sistema, <code>false</code> caso contrario.
	 */
	boolean isRegistered(String username);
	/**
	 * Verifica se uma determina <i>password</i> e' valida para uma determinada conta.
	 * @param username - o <i>username</i> da conta;
	 * @param password - a <i>password</i> da conta;
	 * @return <code>true</code> caso a <i>password</i> dada corresponda 'a conta, <code>false</code> caso contrario.
	 */
	boolean validPassword(String username, String password);
	/**
	 * Verifica se um utilizador pode adicionar actividades no sistema.
	 * @return <code>true</code> caso possa, <code>false</code> caso contrario.
	 */
	boolean canAddActivities();
	/**
	 * Adiciona um hotel ao sistema. Caso a cidade inserida nao exista, o sistema cria automaticamente a cidade.
	 * @param city - a cidade 'a qual se pretende adicionar o hotel;
	 * @param name - o nome do hotel;
	 * @param description - uma breve descricao do hotel;
	 * @param location - a localizacao do hotel;
	 * @param stars - o numero de estrelas do hotel;
	 * @param price - o preco por noite do hotel.
	 */
	void addHotel(String city, String name, String description, String location, int stars, int price);
	/**
	 * Adiciona um restaurante ao sistema. Caso a cidade inserida nao exista, o sistema cria automaticamente a cidade.
	 * @param city - a cidade 'a qual se pretende adicionar o restaurante;
	 * @param name - o nome do restaurante
	 * @param description - uma breve descricao do restaurante;
	 * @param location - a localizacao do restaurante;
	 * @param cuisine - o tipo de cozinha do restaurante;
	 * @param price - o escalao de preco do restaurante.
	 */
	void addRestaurant(String city, String name, String description, String location, String cuisine, String price);
	/**
	 * Verifica se uma cidade existe no sistema.
	 * @param city - o nome da cidade;
	 * @return <code>true</code> caso exista, <code>false</code> caso contrario.
	 */
	boolean hasCity(String city);
	/**
	 * Verifica se existe uma actividade no sistema.
	 * Se a cidade nao existir, devolve <code>false</code>.
	 * @param city - o nome da cidade em que a actividade se encontra;
	 * @param name - o nome da actividade;
	 * @return <code>true</code> se existir a actividade, <code>false</code> caso contrario.
	 */
	boolean hasActivity(String city, String name);
	/**
	 * Devolve uma <code>String</code> com o autor de uma actividade inserida pelo utilizador.
	 * @param city - o nome da cidade em que a actividade se encontra;
	 * @param name - o nome da actividade;
	 * @return o nome do autor da actividade.
	 */
	String getAuthor(String city, String name);
	/**
	 * Devolve uma <code>String</code> com a localizacao de uma actividade inserida pelo utilizador.
	 * @param city - o nome da cidade em que a actividade se encontra;
	 * @param name - o nome da actividade;
	 * @return a localizacao da actividade.
	 */
	String getLocation(String city, String name);
	/**
	 * Devolve uma <code>String</code> com a descricao de uma actividade inserida pelo utilizador.
	 * @param city - o nome da cidade em que a actividade se encontra;
	 * @param name - o nome da actividade;
	 * @return a descricao da actividade.
	 */
	String getDescription(String city, String name);
	/**
	 * Devolve uma actividade especifica do sistema.
	 * @param city - o nome da cidade em que a actividade se encontra;
	 * @param name - o nome da actividade;
	 * @return um objecto <code>Activity</code>.
	 */
	Activity getActivity(String city, String name);
	/**
	 * Faz uma <i>review</i> a um hotel do sistema escolhido pelo utilizador.
	 * @param city - o nome da cidade em que o hotel se encontra;
	 * @param name - o nome do hotel;
	 * @param rating - a classificacao geral do hotel;
	 * @param review - o comentario ao hotel;
	 * @param service - a classificacao do servico do hotel;
	 * @param location - a classificacao da localizacao do hotel.
	 */
	void reviewHotel(String city, String name, String rating, String review, String service, String location);
	/**
	 * Faz uma <i>review</i> a um restaurante do sistema escolhido pelo utilizador.
	 * @param city - o nome da cidade em que o restaurante se encontra;
	 * @param name - o nome do restaurante;
	 * @param rating - a classificacao geral do restaurante;
	 * @param review - o comentario ao restaurante;
	 * @param cuisine - a classificacao da comida do restaurante;
	 * @param ambiance - a classificacao do ambiente do restaurante.
	 */
	void reviewRestaurant(String city, String name, String rating, String review, String cuisine, String ambiance);
	/**
	 * Permite gostar de uma <i>review</i> de um determinado utilizador.
	 * @param city - a cidade da actividade;
	 * @param name - a actividade 'a qual foi feita a <i>review</i>;
	 * @param username - o nome de utilizador do autor da <i>review</i>.
	 */
	void likeReview(String city, String name, String username);
	/**
	 * Devolve o numero de pessoas que gostaram de uma <i>review</i> de um determinado utilizador.
	 * @param city - o nome da cidade em que a actividade se encontra;
	 * @param name - a actividade 'a qual foi feita a <i>review</i>;
	 * @param username - o nome de utilizador do autor da <i>review</i>.
	 * @return o numero de <i>likes</i> da <i>review</i> em causa.
	 */
	int getReviewLikes(String city, String name, String username);
	/**
	 * Devolve o numero de classificacoes de um dado tipo a uma actividade.
	 * @param city - o nome da cidade em que a actividade se encontra;
	 * @param name - o nome da actividade;
	 * @param grade - o tipo de classificacao;
	 * @return o numero de ocurrencias em que o tipo de classificacao especificado foi dado 'a actividade.
	 */
	int getNumberOfRatings(String city, String name, String grade);
	/**
	 * Devolve uma <code>String</code> com a media das classificacoes dadas ao servico do hotel. 
	 * @param city - o nome da cidade em que o hotel se encontra;
	 * @param name - o nome do hotel;
	 * @return a media das classificacoes do servico do hotel.
	 */
	String getAverageService(String city, String name);
	/**
	 * Devolve uma <code>String</code> com a media das classificacoes dadas 'a localizacao do hotel. 
	 * @param city - o nome da cidade em que o hotel se encontra;
	 * @param name - o nome do hotel;
	 * @return a media das classificacoes da localizacao do hotel.
	 */
	String getAverageLocation(String city, String name);
	/**
	 * Devolve uma <code>String</code> com a media das classificacoes dadas 'a cozinha do restaurante. 
	 * @param city - o nome da cidade em que o restaurante se encontra;
	 * @param name - o nome do restaurante;
	 * @return a media das classificacoes da cozinha do restaurante.
	 */
	String getAverageCuisine(String city, String name);
	/**
	 * Devolve uma <code>String</code> com a media das classificacoes dadas ao ambiente do restaurante. 
	 * @param city - o nome da cidade em que o restaurante se encontra;
	 * @param name - o nome do restaurante;
	 * @return a media das classificacoes do ambiente do restaurante.
	 */
	String getAverageAmbiance(String city, String name);
	/**
	 * Verifica se um utilizador realizou uma <i>review</i> a uma dada actividade.
	 * @param city - o nome da cidade em que a actividade se encontra;
	 * @param name - o nome da actividade;
	 * @param username - o <i>username</i> do utilizador que realiza a <i>review</i>;
	 * @return <code>true</code> se o utilizador tiver realizado uma <i>review</i> 'a actividade, <code>false</code> caso contrario.
	 */
	boolean hasReview(String city, String name, String username);
	/**
	 * Devolve um iterador sobre as <i>reviews</i> de uma determinada actividade.
	 * @param city - o nome da cidade em que a actividade se encontra;
	 * @param name - o nome da actividade;
	 * @return o iterador de <i>reviews</i> de uma determinada actividade.
	 */
	Iterator<Review> listReviews(String city, String name);
	/**
	 * Ordena por classificacao e devolve um iterador sobre todos os hoteis existentes numa cidade escolhida pelo utilizador.
	 * @param city - o nome da cidade;
	 * @return o iterador de hoteis de uma determinada cidade.
	 */
	Iterator<Activity> listAllHotels(String city);
	/**
	 * Ordena por classificacao e devolve um iterador sobre todos os restaurantes existentes numa cidade escolhida pelo utilizador.
	 * @param city - o nome da cidade;
	 * @return o iterador de restaurantes de uma determinada cidade.
	 */
	Iterator<Activity> listAllRestaurants(String city);
	/**
	 * Ordena por classificacao e devolve um iterador sobre os hoteis com um preco inferior ou igual ao dado pelo utilizador 
	 * numa determinada cidade.
	 * @param city - o nome da cidade;
	 * @param price - o preco a especificar;
	 * @return o iterador de hoteis com preco inferior ou igual ao dado pelo utilizador numa determinada cidade.
	 */
	Iterator<Activity> listHotelsByPrice(String city, int price);
	/**
	 * Ordena por classificacao e devolve um iterador sobre os hoteis com um numero de estrelas iguais ao dado pelo utilizador 
	 * numa determinada cidade.
	 * @param city - o nome da cidade;
	 * @param stars - o numero de estrelas;
	 * @return o iterador de hoteis com um numero de estrelas iguais ao dado pelo utilizador numa determinada cidade.
	 */
	Iterator<Activity> listHotelsByStars(String city, int stars);
	/**
	 * Ordena por classificacao e devolve um iterador sobre os restaurantes com um tipo de cozinha igual ao dado pelo utilizador
	 * numa determinada cidade.
	 * @param city - o nome da cidade;
	 * @param cuisine - o tipo de cozinha;
	 * @return o iterador de restaurantes com um tipo de cozinha igual ao dado pelo utilizador numa determinada cidade.
	 */
	Iterator<Activity> listRestaurantsByCuisine(String city, String cuisine);
	/**
	 * Ordena por classificacao e devolve um iterador sobre os restaurantes com um escalao de preco igual ao dado pelo utilizador
	 * numa determinada cidade.
	 * @param city - o nome da cidade;
	 * @param price - o escalao de preco;
	 * @return o iterador de restaurantes com um escalao de preco igual ao dado pelo utilizador numa determinada cidade.
	 */
	Iterator<Activity> listRestaurantsByPrice(String city, String price);
	
}
