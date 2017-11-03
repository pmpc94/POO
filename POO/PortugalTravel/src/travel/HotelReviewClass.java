package travel;
/**
 * Subclasse da classe <code>ReviewClass</code>, <i>reviews</i> dos hoteis.
 * @author Francisco Godinho / Pedro Carolina
 */
public class HotelReviewClass extends ReviewClass implements HotelReview {

	private String service, location;
	
	public HotelReviewClass(String reviewer, String rating, String review, String service, String location) {
		super(reviewer, rating, review);
		this.service = service;
		this.location = location;
	}
	
	public String getServiceRating() {
		return service;
	}
	public String getLocationRating() {
		return location;
	}
	
	
}
