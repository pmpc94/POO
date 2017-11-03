package listing;
import java.util.*;
import travel.*;
/**
 * Itera sobre hoteis que tenham as mesmas estrelas que o valor introduzido pelo utilizador.
 * @author Francisco Godinho / Pedro Carolina
 */
public class HotelStarsIterator implements Iterator<Activity> {

	private Iterator<Activity> it;
	private Activity current;
	private int stars;
	
	public HotelStarsIterator(Iterator<Activity> it, int stars) {
		this.it = it;
		this.stars = stars;
		this.searchNext();
	}
	
	private void searchNext() {
		while(it.hasNext()) {
			current = it.next();
			if ((current instanceof Hotel) && (((Hotel) current).getStars() == stars))
				return;	
		}
		current = null;
	}
	
	public boolean hasNext() {
		return current != null;
	}
	
	public Activity next() {
		Activity res = current;
		searchNext();
		return res;
	}
	
	public void remove() {}

}