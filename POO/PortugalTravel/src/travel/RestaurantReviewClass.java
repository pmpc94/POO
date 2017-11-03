package travel;
/**
 * Subclasse da classe <code>ReviewClass</code>, <i>reviews</i> dos restaurantes.
 * @author Francisco Godinho / Pedro Carolina
 */
public class RestaurantReviewClass extends ReviewClass implements RestaurantReview {

	private String cuisine, ambiance;
	
	public RestaurantReviewClass(String reviewer, String rating, String review, String cuisine, String ambiance) {
		super(reviewer, rating, review);
		this.cuisine = cuisine;
		this.ambiance = ambiance;
	}
	
	public String getCuisineRating() {
		return cuisine;
	}
	public String getAmbianceRating() {
		return ambiance;
	}
}
