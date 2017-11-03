package website;
import java.util.*;
import travel.*;
import listing.*;
/**
 * Classe principal, estabelece o contacto com a <code>Main</code>.
 * @author Francisco Godinho / Pedro Carolina
 */
public class WebsiteClass implements Website {

	
	private Map<String, Map<String, Activity>> cities;
	private Map<String, User> users;
	private User currentUser;
	
	public WebsiteClass() {
		this.users = new HashMap<String, User>();
		this.cities = new HashMap<String, Map<String, Activity>>();
		this.currentUser = null;
	}
	
	public String register(String type, String username, String name, String location, String email) {
		User account = null;
		String password = null;
		if (type.equalsIgnoreCase(AuthorClass.AUTHOR)) {
			account = new AuthorClass(username, name, location, email);
			password = ((Author) account).generateAuthorPassword();
		}
		else if (type.equalsIgnoreCase(AdvisorClass.ADVISOR)) {
			account = new AdvisorClass(username, name, location, email);
			password = ((Advisor) account).generateAdvisorPassword();
		}
		users.put(username, account);
		return password;
	}
	public void login(String username, String password) {
		if (validPassword(username, password))
			currentUser = users.get(username);
	}
	public void logout() {
		currentUser = null;
	}
	public boolean someoneLoggedIn() {
		return currentUser != null;
	}
	public boolean isRegistered(String username) {
		return users.containsKey(username);
	}
	public boolean validPassword(String username, String password) {
		return users.get(username).checkPassword(password);
	}
	public User getCurrentUser() {
		return currentUser;
	}
	public boolean canAddActivities() {
		return currentUser != null && currentUser.getType().equalsIgnoreCase(AuthorClass.AUTHOR);
	}
	
	public void addHotel(String city, String name, String description, String location, int stars, int price) {
		Activity hotel = new HotelClass(currentUser.getUsername(), name, description, location, stars, price);
		if (!cities.containsKey(city)) {
			SortedMap<String, Activity> activities = new TreeMap<String, Activity>();
			cities.put(city, activities);
		}
		cities.get(city).put(name, hotel);
	}
	public void addRestaurant(String city, String name, String description, String location, String cuisine, String price) {
		Activity restaurant = new RestaurantClass(currentUser.getUsername(), name, description, location, cuisine, price);
		if (!cities.containsKey(city)) {
			SortedMap<String, Activity> activities = new TreeMap<String, Activity>();
			cities.put(city, activities);
		}
		cities.get(city).put(name, restaurant);
	}
	public boolean hasCity(String city) {
		return cities.containsKey(city);
	}
	public boolean hasActivity(String city, String name) {
		return hasCity(city) && cities.get(city).containsKey(name);
	}
	public String getAuthor(String city, String name) {
		return cities.get(city).get(name).getAuthor();
	}
	public String getLocation(String city, String name) {
		return cities.get(city).get(name).getLocation();
	}
	public String getDescription(String city, String name) {
		return cities.get(city).get(name).getDescription();
	}
	public Activity getActivity(String city, String name) {
		return cities.get(city).get(name);
	}
	
	public void reviewHotel(String city, String name, String rating, String review, String service, String location) {
		HotelReview rev = new HotelReviewClass(currentUser.getUsername(), rating, review, service, location);
		Hotel hotel = (Hotel) cities.get(city).get(name);
		hotel.reviewHotel(rev, currentUser.getUsername());
	}
	public void reviewRestaurant(String city, String name, String rating, String review, String cuisine, String price) {
		RestaurantReview rev = new RestaurantReviewClass(currentUser.getUsername(), rating, review, cuisine, price);
		Restaurant restaurant = (Restaurant) cities.get(city).get(name);
		restaurant.reviewRestaurant(rev, currentUser.getUsername());
	}
	public void likeReview(String city, String name, String username) {
		cities.get(city).get(name).getReview(username).like();
	}
	public int getReviewLikes(String city, String name, String username) {
		return cities.get(city).get(name).getReview(username).getLikes();
	}
	public int getNumberOfRatings(String city, String name, String grade) {
		return cities.get(city).get(name).getNumberOfRatings(grade);
	}
	public String getAverageService(String city, String name) {
		return ((Hotel) (cities.get(city).get(name))).getAvgServiceRating();
	}
	public String getAverageLocation(String city, String name) {
		return ((Hotel) (cities.get(city).get(name))).getAvgLocationRating();
	}
	public String getAverageCuisine(String city, String name) {
		return ((Restaurant) (cities.get(city).get(name))).getAvgCuisineRating();
	}
	public String getAverageAmbiance(String city, String name) {
		return ((Restaurant) (cities.get(city).get(name))).getAvgAmbianceRating();
	}
	public boolean hasReview(String city, String name, String username) {
		return cities.get(city).get(name).hasReview(username);
	}
	public Iterator<Review> listReviews(String city, String name) {
		return cities.get(city).get(name).listReviews();
	}
	public Iterator<Activity> listAllHotels(String city) {
		List<Activity> list = new ArrayList<Activity>();
		if (cities.containsKey(city))
			for (Activity activity: cities.get(city).values())
				if (activity instanceof Hotel)
					list.add(activity);
		Collections.sort(list);
		return list.iterator();
	}
	public Iterator<Activity> listAllRestaurants(String city) {
		List<Activity> list = new ArrayList<Activity>();
		if (cities.containsKey(city))
			for (Activity activity: cities.get(city).values())
				if (activity instanceof Restaurant)
					list.add(activity);
		Collections.sort(list);
		return list.iterator();
	}
	public Iterator<Activity> listHotelsByPrice(String city, int price) {
		List<Activity> list = new ArrayList<Activity>();
		list.addAll(cities.get(city).values());
		Collections.sort(list);
		return new HotelPriceIterator(list.iterator(), price);
	}
	public Iterator<Activity> listHotelsByStars(String city, int stars) {
		List<Activity> list = new ArrayList<Activity>();
		list.addAll(cities.get(city).values());
		Collections.sort(list);
		return new HotelStarsIterator(list.iterator(), stars);
	}
	public Iterator<Activity> listRestaurantsByCuisine(String city, String cuisine) {
		List<Activity> list = new ArrayList<Activity>();
		list.addAll(cities.get(city).values());
		Collections.sort(list);
		return new RestaurantCuisineIterator(list.iterator(), cuisine);
	}
	public Iterator<Activity> listRestaurantsByPrice(String city, String price) {
		List<Activity> list = new ArrayList<Activity>();
		list.addAll(cities.get(city).values());
		Collections.sort(list);
		return new RestaurantPriceIterator(list.iterator(), price);
	}

}
