package travel;
/**
 * Subclasse da classe <code>ActivityClass</code>, classe dos restaurantes.
 * @author Francisco Godinho / Pedro Carolina
 */
public class RestaurantClass extends ActivityClass implements Restaurant {

	public static final String RESTAURANT = "Restaurant";
	private String price, cuisine;
	private int cuisine_score, ambiance_score;
	
	public RestaurantClass(String author, String name, String description, String location, String cuisine, String price) {
		super(author, name, description, location);
		this.cuisine = cuisine;
		this.price = price;
		this.cuisine_score = 0;
		this.ambiance_score = 0;
	}

	public void reviewRestaurant(RestaurantReview rev, String advisor) {
		reviews.put(advisor, rev);
		counter++;
		Ratings grade = Ratings.valueOf(rev.getRating());
		total_score += grade.getScore();
		grade = Ratings.valueOf(rev.getCuisineRating());
		cuisine_score += grade.getScore();
		grade = Ratings.valueOf(rev.getAmbianceRating());
		ambiance_score += grade.getScore();
	}
	
	public String getCuisine() {
		return cuisine;
	}

	public String getPrice() {
		return price;
	}
	
	public String getAvgCuisineRating() {
		float avg_score = 1;
		if (getTotalReviews() != 0)
			avg_score = (float) cuisine_score/getTotalReviews();
		return getRating(Math.round(avg_score));
	}

	public String getAvgAmbianceRating() {
		float avg_score = 1;
		if (getTotalReviews() != 0)
			avg_score = (float) ambiance_score/getTotalReviews();
		return getRating(Math.round(avg_score));
	}

}
