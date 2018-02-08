package filter;
import java.util.ArrayList;
import main.*;

public class FilterByMax implements RaterFilter {
	private int max = 0;
	public FilterByMax(ArrayList<Rater> raters) {
		for(Rater r: raters) {
			if (max < r.numRatings()) {
				max = r.numRatings();
			}
		}
	}
	
	public boolean satisfies(Rater rater) {
		return rater.numRatings() == this.max;
	}
}
