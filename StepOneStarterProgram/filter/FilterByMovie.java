package filter;
import main.*;

public class FilterByMovie implements iRaterFilter {
	private String movie;
	public FilterByMovie(String movie) {
		this.movie = movie;
	}
	
	public boolean satisfies(Rater rater) {
		return rater.hasRating(movie);
	}

}
