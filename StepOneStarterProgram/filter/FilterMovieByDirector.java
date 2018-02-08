package filter;

import main.Movie;

public class FilterMovieByDirector implements MovieFilter {

	private String director;
	public FilterMovieByDirector(String director) {
		// TODO Auto-generated constructor stub
		this.director = director;
	}
	
	public boolean satisfies(Movie movie) {
		return movie.getDirector().contains(director);
	}

}
