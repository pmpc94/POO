package listing;
import java.util.*;
import travel.*;
/**
 * Itera sobre restaurantes que tenham o mesmo tipo de cozinha que o tipo introduzido pelo utilizador.
 * @author Francisco Godinho / Pedro Carolina
 */
public class RestaurantCuisineIterator implements Iterator<Activity> {
	
	private Iterator<Activity> it;
	private Activity current;
	private String cuisine;
	
	public RestaurantCuisineIterator(Iterator<Activity> it, String cuisine) {
		this.it = it;
		this.cuisine = cuisine;
		this.searchNext();
	}
	
	private void searchNext() {
		while(it.hasNext()) {
			current = it.next();
			if ((current instanceof Restaurant) && (((Restaurant) current).getCuisine().equals(cuisine)))
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
