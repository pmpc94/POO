import java.util.*;
import website.*;
import travel.*;
/**
 * <code>Main</code>
 * @author Francisco Godinho / Pedro Carolina
 */
public class Main {

	/* Mensagens de sistema */
	public static final String WELCOME = "> Welcome %s.\n\n";
	public static final String REGISTERED = "> User was registered: %s.\n\n";
	public static final String ALREADY_REG = "> Username already registered.\n";
	public static final String USER_IS_LOGGED = "> User is already logged in.\n";
	public static final String REG_USER_IS_LOGGED = "> There is a user logged in.\n";
	public static final String OTHER_IS_LOGGED = "> Another user is logged in.\n";
	public static final String NONE_LOGGED = "> No user is logged in.\n";
	public static final String WRONG_PW = "> Wrong password.\n";
	public static final String NO_USER = "> User does not exist.\n";
	public static final String GOODBYE = "> Goodbye %s.\n\n";
	public static final String EXIT = "> Exiting...\n";
	public static final String AUTHOR_ONLY = "> Only Author users may add %ss.\n\n";
	public static final String ALREADY_ADDED = "> Activity already exists.\n";
	public static final String ADDED_ACTIVITY = "> %s was registered.\n\n";
	public static final String REVIEWS_FOR = "> Reviews for %s %s (by %s):\n";
	public static final String OVERALL = "> Overall review:";
	public static final String N_EXCELLENTS = "> Excellent: %d\n";
	public static final String N_GOODS = "> Good: %d\n";
	public static final String N_AVERAGES = "> Average: %d\n";
	public static final String N_POORS = "> Poor: %d\n";
	public static final String N_TERRIBLES = "> Terrible: %d\n";
	public static final String REVIEWER = "> Reviewer %s (%d likes): %s\n";
	public static final String SERVICE = "> Service review: %s\n";
	public static final String LOCATION = "> Location review: %s\n";
	public static final String CUISINE = "> Food review: %s\n";
	public static final String ATMOSPHERE = "> Atmosphere review: %s\n";
	public static final String LOGIN_PLS = "> Login to add a review.\n";
	public static final String NO_ACTIVITY = "> %s does not exist.\n\n";
	public static final String ADDED_REVIEW = "> Review was registered.\n";
	public static final String NO_REVIEW = "> User has not reviewed activity %s.\n\n";
	public static final String LIKES = "> Number of likes for review %s by %s: %d.\n\n";
	public static final String IT_NO_ACTIVITY = "> No registered %ss in %s.\n\n";
	public static final String IT_NO_ACTIVITY_FILTER = "> No registered %ss in %s with%s %s.\n\n";
	public static final String IT_ALL = "> All %ss in %s:\n";
	public static final String IT_RANK = "> Ranked %d (%d reviews): %s | %s | %s\n";
	
	/* Comandos disponiveis */
	public enum Commands {
		LOGIN, LOGOUT, ADD, REVIEW, HOTELS, RESTAURANTS, HOTEL, RESTAURANT, REVIEWS, LIKE, REGISTER, ALL, STARS, PRICE, CUISINE, COST, EXIT
	}
	
	public static void main(String[] args) {
		Website web = new WebsiteClass();
		Scanner in = new Scanner(System.in);
		menu(web, in);
	}
	
	/**
	 * Interpretador de comandos. Caso ocorra a excepccao especificada abaixo, o comando inserido e' ignorado.
	 * @throws IllegalArgumentException - caso receba um comando diferente dos contemplados no enumerado.
	 */
	private static void menu(Website web, Scanner in) throws IllegalArgumentException {
		try {
			Commands cmd = Commands.valueOf(in.next().toUpperCase());
			while (cmd != Commands.EXIT) {
				switch (cmd) {
					case LOGIN: login(web, in);
						break;
					case LOGOUT: logout(web);
						break;
					case ADD:
						cmd = Commands.valueOf(in.next().toUpperCase());
						switch (cmd) {
							case HOTEL: addHotel(web, in);
								break;
							case RESTAURANT: addRestaurant(web, in);
								break;
							default: 
								break;
						} break;
					case REVIEW: 
						cmd = Commands.valueOf(in.next().toUpperCase());
						switch (cmd) {
							case HOTEL: reviewHotel(web, in);
								break;
							case RESTAURANT: reviewRestaurant(web, in);
								break;
							default: 
								break;
						} break;
					case HOTELS: 
						cmd = Commands.valueOf(in.next().toUpperCase());
						switch (cmd) {
							case ALL: listAllHotels(web, in);
								break;
							case STARS: listHotelsByStars(web, in);
								break;
							case PRICE: listHotelsByPrice(web, in);
								break;
							default:
								break;
						} break;
					case RESTAURANTS:
						cmd = Commands.valueOf(in.next().toUpperCase());
						switch (cmd) {
							case ALL: listAllRestaurants(web, in);
								break;
							case CUISINE: listRestaurantsByCuisine(web, in);
								break;
							case COST: listRestaurantsByPrice(web, in);
								break;
							default:
								break;
						} break;
					case REVIEWS: 
						cmd = Commands.valueOf(in.next().toUpperCase());
						switch (cmd) {
							case HOTEL: getHotelReviews(web, in);
								break;
							case RESTAURANT: getRestaurantReviews(web, in);
								break;
							default: 
								break;
						} break;
					case LIKE:
						cmd = Commands.valueOf(in.next().toUpperCase());
						if (cmd == Commands.REVIEW)
							likeReview(web, in);
						break;
					case REGISTER: register(web, in);
						break;
					default:
						break;
				} 
				cmd = Commands.valueOf(in.next().toUpperCase());
			} 
			System.out.println(EXIT);
		} catch (IllegalArgumentException e) { menu(web, in); }
		in.close();
	}
	/**
	 * Permite registar utilizadores no sistema. Necessita de um tipo de conta, de um <i>username</i>, de um nome,
	 * de uma morada e de um e-mail. O metodo falha caso existam contas com sessao iniciada ou o <i>username</i> ja
	 * se encontre registado no sistema.
	 */
	private static void register(Website web, Scanner in) {
		String type = in.next();
		String username = in.nextLine().trim();
		String name = in.nextLine();
		String location = in.nextLine();
		String email = in.nextLine();
		if (web.someoneLoggedIn())
			System.out.println(REG_USER_IS_LOGGED);
		else if (web.isRegistered(username))
			System.out.println(ALREADY_REG);
		else {
			String pw = web.register(type, username, name, location, email);
			System.out.printf(REGISTERED, pw);
		}
	}
	/**
	 * Permite iniciar sessao numa conta do sistema. Necessita, para isso, de um <i>username</i> e de uma <i>password</i>.
	 * O metodo falha caso a conta nao se encontre registada no sistema, o utilizador ja se encontre com sessao iniciada,
	 * outro utilizador se encontre com sessao iniciada ou a <i>password</i> nao corresponda 'a respectiva conta.
	 */
	private static void login(Website web, Scanner in) {
		String username = in.next();
		String password = in.nextLine().trim();
		if (!web.isRegistered(username))
			System.out.println(NO_USER);
		else if (web.someoneLoggedIn()) {
			if (web.getCurrentUser().getUsername().equals(username))
				System.out.println(USER_IS_LOGGED);
			else
				System.out.println(OTHER_IS_LOGGED);
		}
		else if (!web.validPassword(username, password))
			System.out.println(WRONG_PW);
		else {
			web.login(username, password);
			System.out.printf(WELCOME, username);
		}
			
	}
	/**
	 * Permite terminar a sessao de qualquer conta com sessao iniciada no sistema.
	 * O metodo falha caso nao exista ninguem com conta iniciada no sistema.
	 */
	private static void logout(Website web) {
		if (!web.someoneLoggedIn())
			System.out.println(NONE_LOGGED);
		else {
			System.out.printf(GOODBYE, web.getCurrentUser().getUsername());
			web.logout();
		}
	}
	/**
	 * Permite adicionar um hotel ao sistema. Necessita do nome de uma cidade, do nome do hotel, da descricao, da localizacao,
	 * das estrelas e do preco por noite. O metodo falha caso a conta com sessao iniciada nao seja do tipo <code>Author</code>
	 * ou a actividade ja exista no sistema.
	 */
	private static void addHotel(Website web, Scanner in) {
		String city = in.next();
		String name = in.nextLine().trim();
		String description = in.nextLine();
		String location = in.nextLine();
		int stars = in.nextInt();
		int price = in.nextInt();
		if (!web.canAddActivities())
			System.out.printf(AUTHOR_ONLY, HotelClass.HOTEL.toLowerCase());
		else if (web.hasActivity(city, name))
			System.out.println(ALREADY_ADDED);
		else {
			web.addHotel(city, name, description, location, stars, price);
			System.out.printf(ADDED_ACTIVITY, HotelClass.HOTEL);
		}
	}
	/**
	 * Permite adicionar um restaurante ao sistema. Necessita do nome de uma cidade, do nome do restaurante, da descricao, 
	 * da localizacao, do tipo de cozinha e do escalao de preco. O metodo falha caso a conta com sessao iniciada nao seja 
	 * do tipo <code>Author</code> ou a actividade ja exista no sistema.
	 */
	private static void addRestaurant(Website web, Scanner in) {
		String city = in.next();
		String name = in.nextLine().trim();
		String description = in.nextLine();
		String location = in.nextLine();
		String cuisine = in.nextLine();
		String price = in.nextLine();
		if (!web.canAddActivities())
			System.out.printf(AUTHOR_ONLY, RestaurantClass.RESTAURANT.toLowerCase());
		else if (web.hasActivity(city, name))
			System.out.println(ALREADY_ADDED);
		else {
			web.addRestaurant(city, name, description, location, cuisine, price);
			System.out.printf(ADDED_ACTIVITY, RestaurantClass.RESTAURANT);
		}
	}
	/** 
	 * Permite adicionar uma <i>review</i> a um hotel. Necessita do nome da cidade do hotel, do nome do hotel, da classificacao
	 * geral dada ao hotel, de um comentario, de uma classificacao dada ao servico e de uma classificacao dada a localizacao.
	 * O metodo falha caso nao exista uma conta com sessao iniciada ou caso a actividade nao exista.
	 */
	private static void reviewHotel(Website web, Scanner in) {
		String city = in.next();
		String name = in.nextLine().trim();
		String rating = in.nextLine();
		String review = in.nextLine();
		String service = in.nextLine().trim();
		String location = in.nextLine().trim();
		if (!web.someoneLoggedIn())
			System.out.println(LOGIN_PLS);
		else if (!web.hasActivity(city, name))
			System.out.printf(NO_ACTIVITY, HotelClass.HOTEL);
		else {
			web.reviewHotel(city, name, rating, review, service, location);
			System.out.println(ADDED_REVIEW);
		}
			
	}
	/** 
	 * Permite adicionar uma <i>review</i> a um restaurante. Necessita do nome da cidade do restaurante, do nome do resutarante,
	 * da classificacao geral dada ao restaurante, de um comentario, de uma classificacao dada 'a comida e de uma classificacao 
	 * dada ao ambiente. O metodo falha caso nao exista uma conta com sessao iniciada ou caso a actividade nao exista.
	 */
	private static void reviewRestaurant(Website web, Scanner in) {
		String city = in.next();
		String name = in.nextLine().trim();
		String rating = in.nextLine();
		String review = in.nextLine();
		String cuisine = in.nextLine().trim();
		String ambiance = in.nextLine().trim();
		if (!web.someoneLoggedIn())
			System.out.println(LOGIN_PLS);
		else if (!web.hasActivity(city, name))
			System.out.printf(NO_ACTIVITY, RestaurantClass.RESTAURANT);
		else {
			web.reviewRestaurant(city, name, rating, review, cuisine, ambiance);
			System.out.println(ADDED_REVIEW);
		}
	}
	/**
	 * Permite um utilizador gostar de uma <i>review</i> de um determinado utilizador a uma actividade. Necessita do nome da cidade
	 * da actividade, do nome da actividade, e do nome do utilizador que realizou a <i>review</i>. O metodo falha caso a actividade
	 * nao exista no sistema, o utilizador em causa nao se encontre registado ou a <i>review</i> nao exista.
	 */
	private static void likeReview(Website web, Scanner in) {
		String city = in.next();
		String name = in.next();
		String username = in.nextLine().trim();
		if (!web.hasActivity(city, name))
			System.out.printf(NO_ACTIVITY, ActivityClass.ACTIVITY);
		else if (!web.isRegistered(username))
			System.out.println(NO_USER);
		else if (!web.hasReview(city, name, username))
			System.out.printf(NO_REVIEW, name);
		else {
			web.likeReview(city, name, username);
			System.out.printf(LIKES, name, username, web.getReviewLikes(city, name, username));
		}
	}
	/**
	 * Permite obter <i>reviews</i> realizadas a um determinado hotel. Necessita do nome da cidade do hotel e do nome do hotel.
	 * O metodo falha caso a actividade nao exista.
	 */
	private static void getHotelReviews(Website web, Scanner in) {
		String city = in.next();
		String name = in.nextLine().trim();
		if (!web.hasActivity(city, name) || !(web.getActivity(city, name) instanceof Hotel))
			System.out.printf(NO_ACTIVITY, HotelClass.HOTEL);
		else {
			Iterator<Review> it = web.listReviews(city, name);
			Review rev = null;
			System.out.printf(REVIEWS_FOR, HotelClass.HOTEL.toLowerCase(), name, web.getAuthor(city, name));
			System.out.println("> " + web.getLocation(city, name) + "\n> " + web.getDescription(city, name) + '\n' + OVERALL);
			System.out.printf(N_EXCELLENTS, web.getNumberOfRatings(city, name, Ratings.Excellent.toString()));
			System.out.printf(N_GOODS, web.getNumberOfRatings(city, name, Ratings.Good.toString()));
			System.out.printf(N_AVERAGES, web.getNumberOfRatings(city, name, Ratings.Average.toString()));
			System.out.printf(N_POORS, web.getNumberOfRatings(city, name, Ratings.Poor.toString()));
			System.out.printf(N_TERRIBLES, web.getNumberOfRatings(city, name, Ratings.Terrible.toString()));
			System.out.printf(SERVICE, web.getAverageService(city, name));
			System.out.printf(LOCATION, web.getAverageLocation(city, name));
			while (it.hasNext()) {
				rev = it.next();
				System.out.printf(REVIEWER, rev.getReviewer(), rev.getLikes(), rev.getReview());
			}
			System.out.println();
		}
	}
	/**
	 * Permite obter <i>reviews</i> realizadas a um determinado restaurante. Necessita do nome da cidade do restaurante 
	 * e do nome do restaurante. O metodo falha caso a actividade nao exista.
	 */
	private static void getRestaurantReviews(Website web, Scanner in) {
		String city = in.next();
		String name = in.nextLine().trim();
		if (!web.hasActivity(city, name) || !(web.getActivity(city, name) instanceof Restaurant))
			System.out.printf(NO_ACTIVITY, RestaurantClass.RESTAURANT);
		else {
			Iterator<Review> it = web.listReviews(city, name);
			Review rev = null;
			System.out.printf(REVIEWS_FOR, RestaurantClass.RESTAURANT.toLowerCase(), name, web.getAuthor(city, name));
			System.out.println("> " + web.getLocation(city, name) + "\n> " + web.getDescription(city, name) + '\n' + OVERALL);
			System.out.printf(N_EXCELLENTS, web.getNumberOfRatings(city, name, Ratings.Excellent.toString()));
			System.out.printf(N_GOODS, web.getNumberOfRatings(city, name, Ratings.Good.toString()));
			System.out.printf(N_AVERAGES, web.getNumberOfRatings(city, name, Ratings.Average.toString()));
			System.out.printf(N_POORS, web.getNumberOfRatings(city, name, Ratings.Poor.toString()));
			System.out.printf(N_TERRIBLES, web.getNumberOfRatings(city, name, Ratings.Terrible.toString()));
			System.out.printf(CUISINE, web.getAverageCuisine(city, name));
			System.out.printf(ATMOSPHERE, web.getAverageAmbiance(city, name));
			while (it.hasNext()) {
				rev = it.next();
				System.out.printf(REVIEWER, rev.getReviewer(), rev.getLikes(), rev.getReview());
			}
			System.out.println();
		}
	}
	/**
	 * Permite listar todos os hoteis de uma determinada cidade. Necessita do nome da cidade.
	 * O metodo falha caso a cidade nao exista no sistema.
	 */
	private static void listAllHotels(Website web, Scanner in) {
		String city = in.nextLine().trim();
		Iterator<Activity> it = web.listAllHotels(city);
		Hotel res = null;
		int rank = 1;
		if (!web.hasCity(city) || !it.hasNext())
			System.out.printf(IT_NO_ACTIVITY, HotelClass.HOTEL.toLowerCase(), city);
		else {
			System.out.printf(IT_ALL, HotelClass.HOTEL.toLowerCase(), city);
			while (it.hasNext()) {
				res = (Hotel) it.next();
				System.out.printf(IT_RANK, rank, res.getCurrentReviews(), res.getName(), res.getStars(), res.getPrice());
				rank++;
			}
			System.out.println();
		}
	}
	/**
	 * Permite listar todos os restaurantes de uma determinada cidade. Necessita do nome da cidade.
	 * O metodo falha caso a cidade nao exista no sistema.
	 */
	private static void listAllRestaurants(Website web, Scanner in) {
		String city = in.nextLine().trim();
		Iterator<Activity> it = web.listAllRestaurants(city);
		Restaurant res = null;
		int rank = 1;
		if (!web.hasCity(city) || !it.hasNext())
			System.out.printf(IT_NO_ACTIVITY, RestaurantClass.RESTAURANT.toLowerCase(), city);
		else {
			System.out.printf(IT_ALL, RestaurantClass.RESTAURANT.toLowerCase(), city);
			while (it.hasNext()) {
				res = (Restaurant) it.next();
				System.out.printf(IT_RANK, rank++, res.getCurrentReviews(), res.getName(), res.getCuisine(), res.getPrice());
			}
			System.out.println();
		}
	}
	/**
	 * Permite listar todos os hoteis de uma determinada cidade que tenham um preco por noite inferior ao dado. 
	 * Necessita do nome da cidade. O metodo falha caso a cidade nao exista no sistema ou nao existam hoteis que 
	 * cumpram esse requisito.
	 */
	private static void listHotelsByPrice(Website web, Scanner in) {
		String city = in.next();
		int price = in.nextInt();
		Hotel res = null;
		int rank = 1;
		if (!web.hasCity(city))
			System.out.printf(IT_NO_ACTIVITY_FILTER, HotelClass.HOTEL.toLowerCase(), city, " price by night bellow", price);
		else {
			Iterator<Activity> it = web.listHotelsByPrice(city, price);
			if (!it.hasNext())
				System.out.printf(IT_NO_ACTIVITY_FILTER, HotelClass.HOTEL.toLowerCase(), city, " price by night bellow", price);
			else {
				System.out.printf(IT_ALL, HotelClass.HOTEL.toLowerCase(), city + " bellow " + price);
				while (it.hasNext()) {
					res = (Hotel) it.next();
					System.out.printf(IT_RANK, rank++, res.getCurrentReviews(), res.getName(), res.getStars(), res.getPrice());
				}
				System.out.println();
			}
		}
	}
	/**
	 * Permite listar todos os hoteis de uma determinada cidade que tenham o mesmo numero de estrelas que as dadas pelo utilizador. 
	 * Necessita do nome da cidade. O metodo falha caso a cidade nao exista no sistema ou nao existam hoteis que cumpram esse requisito.
	 */
	private static void listHotelsByStars(Website web, Scanner in) {
		String city = in.next();
		int stars = in.nextInt();
		Hotel res = null;
		int rank = 1;
		if (!web.hasCity(city))
			System.out.printf(IT_NO_ACTIVITY_FILTER, HotelClass.HOTEL.toLowerCase(), city, " " + stars, "stars");
		else {
			Iterator<Activity> it = web.listHotelsByStars(city, stars);
			if (!it.hasNext())
				System.out.printf(IT_NO_ACTIVITY_FILTER, HotelClass.HOTEL.toLowerCase(), city, " " + stars, "stars");
			else {
				System.out.printf(IT_ALL, HotelClass.HOTEL.toLowerCase(), city + " with " + stars + " stars");
				while (it.hasNext()) {
					res = (Hotel) it.next();
					System.out.printf(IT_RANK, rank++, res.getCurrentReviews(), res.getName(), res.getStars(), res.getPrice());
				}
				System.out.println();
			}
		}
	}
	/**
	 * Permite listar todos os restaurantes de uma determinada cidade que tenham o mesmo tipo de cozinha que o dado pelo utilizador. 
	 * Necessita do nome da cidade. O metodo falha caso a cidade nao exista no sistema ou nao existam restaurantes que cumpram 
	 * esse requisito.
	 */
	private static void listRestaurantsByCuisine(Website web, Scanner in) {
		String city = in.next();
		String cuisine = in.nextLine().trim();
		Restaurant res = null;
		int rank = 1;
		if (!web.hasCity(city))
			System.out.printf(IT_NO_ACTIVITY_FILTER, RestaurantClass.RESTAURANT.toLowerCase(), city, " cuisine", cuisine);
		else {
			Iterator<Activity> it = web.listRestaurantsByCuisine(city, cuisine);
			if (!it.hasNext())
				System.out.printf(IT_NO_ACTIVITY_FILTER, RestaurantClass.RESTAURANT.toLowerCase(), city, " cuisine", cuisine);
			else {
				System.out.printf(IT_ALL, RestaurantClass.RESTAURANT.toLowerCase(), city + " with cuisine " + cuisine);
				while (it.hasNext()) {
					res = (Restaurant) it.next();
					System.out.printf(IT_RANK, rank++, res.getCurrentReviews(), res.getName(), res.getCuisine(), res.getPrice());
				}
				System.out.println();
			}
		}
	}
	/**
	 * Permite listar todos os restaurantes de uma determinada cidade que tenham o mesmo escalao de preco que o dado pelo 
	 * utilizador. Necessita do nome da cidade. O metodo falha caso a cidade nao exista no sistema ou nao existam restaurantes 
	 * que cumpram esse requisito.
	 */
	private static void listRestaurantsByPrice(Website web, Scanner in) {
		String city = in.next();
		String price = in.nextLine().trim();
		Restaurant res = null;
		int rank = 1;
		if (!web.hasCity(city))
			System.out.printf(IT_NO_ACTIVITY_FILTER, RestaurantClass.RESTAURANT.toLowerCase(), city, "in price range", price);
		else {
			Iterator<Activity> it = web.listRestaurantsByPrice(city, price);
			if (!it.hasNext())
				System.out.printf(IT_NO_ACTIVITY_FILTER, RestaurantClass.RESTAURANT.toLowerCase(), city, "in price range", price);
			else {
				System.out.printf(IT_ALL, RestaurantClass.RESTAURANT.toLowerCase(), city + " within price range " + price);
				while (it.hasNext()) {
					res = (Restaurant) it.next();
					System.out.printf(IT_RANK, rank++, res.getCurrentReviews(), res.getName(), res.getCuisine(), res.getPrice());
				}
				System.out.println();
			}
		}
	}
	
}