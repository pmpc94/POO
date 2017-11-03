package listing;
import java.util.*;
import travel.*;
/**
 * Itera sobre hoteis que tenham preco inferior ao introduzido pelo utilizador.
 * @author Francisco Godinho / Pedro Carolina
 */
public class HotelPriceIterator implements Iterator<Activity> {
	
	private Iterator<Activity> it;
	private Activity current;
	private int price;
	
	public HotelPriceIterator(Iterator<Activity> it, int price) {
		this.it = it;
		this.price = price;
		this.searchNext();
	}
	
	
	private void searchNext() {
		while(it.hasNext()) {
			current = it.next();
			if ((current instanceof Hotel) && (((Hotel) current).getPrice() <= price))
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