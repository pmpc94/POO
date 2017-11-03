package travel;
/**
 * Subclasse da classe <code>ActivityClass</code>, classe dos hoteis.
 * @author Francisco Godinho / Pedro Carolina
 */
public class HotelClass extends ActivityClass implements Hotel {
	
	public static final String HOTEL = "Hotel";
	private int price, stars;
	private int service_score, location_score;

	public HotelClass(String author, String name, String description, String location, int stars, int price) {
		super(author, name, description, location);
		this.price = price;
		this.stars = stars;
		this.service_score = 0;
		this.location_score = 0;
	}
	
	public void reviewHotel(HotelReview rev, String advisor) {
		reviews.put(advisor, rev);
		counter++;
		Ratings grade = Ratings.valueOf(rev.getRating());
		total_score += grade.getScore();
		grade = Ratings.valueOf(rev.getServiceRating());
		service_score += grade.getScore();
		grade = Ratings.valueOf(rev.getLocationRating());
		location_score += grade.getScore();
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getStars() {
		return stars;
	}
	public String getAvgServiceRating() {
		float avg_score = 1;
		if (getTotalReviews() != 0)
			avg_score = (float) service_score/getTotalReviews();
		return getRating(Math.round(avg_score));
	}
	public String getAvgLocationRating() {
		float avg_score = 1;
		if (getTotalReviews() != 0)
			avg_score = (float) location_score/getTotalReviews();
		return getRating(Math.round(avg_score));
	}

}
