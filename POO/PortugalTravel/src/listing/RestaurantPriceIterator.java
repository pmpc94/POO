package listing;
import java.util.*;
import travel.*;
/**
 * Itera sobre restaurantes que tenham o mesmo escalao de preco que o valor introduzido pelo utilizador.
 * @author Francisco Godinho / Pedro Carolina
 */
public class RestaurantPriceIterator implements Iterator<Activity> {
	
	private Iterator<Activity> it;
	private Activity current;
	private String price;
	
	public RestaurantPriceIterator(Iterator<Activity> it, String price) {
		this.it = it;
		this.price = price;
		this.searchNext();
	}
	
	private void searchNext() {
		while(it.hasNext()) {
			current = it.next();
			if ((current instanceof Restaurant) && (((Restaurant) current).getPrice().equals(price)))
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
