package travel;
import java.util.*;
/**
 * Classe abstracta das actividades, implementa o metodo <code>compareTo</code> para ordenacao de actividades.
 * @author Francisco Godinho / Pedro Carolina
 */
public abstract class ActivityClass implements Activity {
	
	public static final String ACTIVITY = "Activity";

	protected SortedMap<String, Review> reviews;
	private String author, name, description, location;
	protected int total_score, counter;
	
	
	protected ActivityClass(String author, String name, String description, String location) {
		this.reviews = new TreeMap<String, Review>();
		this.author = author;
		this.name = name;
		this.description = description;
		this.location = location;
		this.total_score = 0;
	}
	
	public String getAuthor() {
		return author;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getLocation() {
		return location;
	}
	public int getNumberOfRatings(String grade) {
		int number = 0;
		for (Review rev: reviews.values())
			if (rev.getRating().equals(grade))
				number++;
		return number;
	}
	public int getTotalReviews() {
		return counter;
	}
	public int getCurrentReviews() {
		return reviews.size();
	}
	public int getAvgScore() {
		int avg = 0;
		if (getTotalReviews() != 0)
			avg = total_score/getTotalReviews();
		return avg;
	}
	public Review getReview(String username) {
		return reviews.get(username);
	}
	public boolean hasReview(String username) {
		return reviews.containsKey(username);
	}	
	public Iterator<Review> listReviews() {
		List<Review> sort = new ArrayList<Review>();
		sort.addAll(reviews.values());
		Collections.sort(sort);
		return sort.iterator();
	}

	
	public int compareTo(Activity o) {
		int result = 0;
		if (getAvgScore() > o.getAvgScore())
			result = -1;
		else if (getAvgScore() < o.getAvgScore())
			result = 1;
		return result;
	}
	
	protected String getRating(int score) {
		Ratings grade = null;
		switch (score) {
			case 5: grade = Ratings.Excellent;
				break;
			case 4: grade = Ratings.Good;
				break;
			case 3: grade = Ratings.Average;
				break;
			case 2: grade = Ratings.Poor;
				break;
			case 1: grade = Ratings.Terrible;
				break;
		}
		return grade.toString();
	}

}
