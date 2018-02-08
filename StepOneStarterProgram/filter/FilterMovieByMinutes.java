package filter;
import main.*;

public class FilterMovieByMinutes implements MovieFilter {

	private int minutes;
	public FilterMovieByMinutes(int min) {
		// TODO Auto-generated constructor stub
		minutes = min;
	}
	
	public boolean satisfies(Movie movie) {
		return movie.getMinutes() > minutes;
	}

}
