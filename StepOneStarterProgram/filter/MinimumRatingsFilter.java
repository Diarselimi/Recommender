package filter;

import main.*;

public class MinimumRatingsFilter implements Filter {
	private int minimumRatings;
	
	public MinimumRatingsFilter(int minimum) {
		minimumRatings = minimum;
	}
	
	@Override
	public boolean satisfies(String id) {
		int ratingsFound = 0;
		for(Rater r: RaterDatabase.getRaters()) {
			if(r.hasRating(id)) {
				ratingsFound ++;
			}
			if(ratingsFound >= minimumRatings) {
				return true;
			}
		}
		return false;
	}

}
