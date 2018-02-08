package filter;

import main.Movie;

public class FilterMovieByGenre implements MovieFilter {

	private String genre;
	public FilterMovieByGenre(String genre) {
		// TODO Auto-generated constructor stub
		this.genre = genre;
	}
	
	public boolean satisfies(Movie movie) {
		return movie.getGenres().contains(genre);
	}
}	
