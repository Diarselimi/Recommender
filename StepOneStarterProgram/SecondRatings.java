
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
}
