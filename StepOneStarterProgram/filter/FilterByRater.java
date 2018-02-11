package filter;
import main.*;

public class FilterByRater implements iRaterFilter {
	private String raterId;
	public FilterByRater(String raterId) {
		this.raterId = raterId;
	}
	
	public boolean satisfies(Rater rater) {
		return rater.getID().equals(raterId);
	}
}
