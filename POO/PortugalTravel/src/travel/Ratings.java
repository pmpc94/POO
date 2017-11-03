package travel;
/**
 * Enumerado das classificacoes atribuidas pelas <i>reviews</i>.
 * @author Francisco Godinho / Pedro Carolina
 */
public enum Ratings {
	
	Excellent(5), Good(4), Average(3), Poor(2), Terrible(1);
	private int value;
	
	Ratings(int value) {
		this.value = value;
	}
	
	public int getScore() {
		return value;
	}

}
