package main;

/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import main.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
	public SecondRatings(String firstFile, String secondFile) {
		FirstRatings first = new FirstRatings();
		myMovies = first.loadMovies(firstFile);	
		myRaters = first.loadRaters(secondFile);
	}	
	
	public int getMovieSize() {
		return myMovies.size();
	}
	
	public int getRaterSize(){
		return myRaters.size();
	}
	
	public String getTitle(String id) {
		for(Movie movie: myMovies) {
			if(movie.getID().equals(id)) {
				return movie.getTitle();
			}
		}
		return "The movie ID:"+id+" was not found!";
	}
	
	public String getID(String title) {
		for(Movie movie: myMovies) {
			if(movie.getTitle().contains(title)) {
				return movie.getID();
			}
		}
		return "NO SUCH TITLE.";
	}
	
	public ArrayList<Rating> getAverageRatings(int minimalRaters) {
		ArrayList<Rating> moviesAvgRatings = new ArrayList<Rating>();
		
		for(Movie movie: myMovies) {
			double avgRating = getAverageByID(movie.getID(), minimalRaters);
			if(avgRating > 0.0) {
				Rating rating = new Rating(movie.getID(), avgRating);
				moviesAvgRatings.add(rating);
			}
		}
		
		return moviesAvgRatings;
	}
	
	private double getAverageByID(String movieId, int minimalRaters) {
		double avg = 0.0;
		ArrayList<Double> ratings = new ArrayList<Double>(); 
		for(Rater rater: myRaters) {
			if(rater.getItemsRated().contains(movieId)) {
				ratings.add(rater.getRating(movieId));
			}
		}
		
		if(ratings.size() >= minimalRaters) {
			for(Double rating: ratings) {
				avg += rating;
			}
			avg = avg / ratings.size();
		}
		
		return avg;
	}
}
