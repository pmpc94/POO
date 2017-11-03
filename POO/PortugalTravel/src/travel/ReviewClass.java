package travel;
/**
 * Classe abstracta de <i>reviews</i>, implementa o metodo <code>compareTo</code> para ordenacao de <i>reviews</i>.
 * @author Francisco Godinho / Pedro Carolina
 */
public abstract class ReviewClass implements Review {
	
	protected String reviewer, rating, review;
	private int likes;
	
	protected ReviewClass(String reviewer, String rating, String review) {
		this.reviewer = reviewer;
		this.rating = rating;
		this.review = review;
		this.likes = 0;
	}
	
	public String getReviewer() {
		return reviewer;
	}
	public String getRating() {
		return rating;
	}
	public String getReview() {
		return review;
	}
	public void like() {
		likes++;
	}
	public int getLikes() {
		return likes;
	}
	
	public int compareTo(Review o) {
		int result = 0;
		if (getLikes() > o.getLikes())
			result = -1;
		else if (getLikes() < o.getLikes())
			result = 1;
		return result;
	}

}
