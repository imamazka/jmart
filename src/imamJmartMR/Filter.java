package imamJmartMR;
import java.util.ArrayList;

public class Filter {
	
	public ArrayList<PriceTag> filterPriceTag(PriceTag[] list, int value, boolean less) {
		
		ArrayList<PriceTag> priceTags = new ArrayList<PriceTag>();
		if(less == true) {
			for (int i=0; i<list.length; i++) {
				if(list[i].getAdjustedPrice() < (double)value) {
					priceTags.add(list[i]);
				}
				else
					continue;
			}
		}
		
		else {
			for (int i=0; i<list.length; i++) {
				if(list[i].getAdjustedPrice() >= (double)value) {
					priceTags.add(list[i]);
				}
				else
					continue;
			}
		}
		
		return priceTags;
	}
	
	public void filterProductRating(ArrayList<ProductRating> list, double value, boolean less) {
		
		if(less == true) {
			for (int i=0; i<list.size(); i++) {
				if(list.get(i).getAverage() < value) {
					list.remove(i);
				}
				else
					continue;
			}
		}
		
		else {
			for (int i=0; i<list.size(); i++) {
				if(list.get(i).getAverage() >= value) {
					list.remove(i);
				}
				else
					continue;
			}
		}
	}

}
