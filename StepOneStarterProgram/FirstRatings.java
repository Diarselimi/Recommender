import edu.duke.*;
import filter.*;

import java.util.*;
import org.apache.commons.csv.*;
import main.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        for (CSVRecord row: fr.getCSVParser()) {
            Movie mv = new Movie(
                row.get("id"), 
                row.get("title"), 
                row.get("year"), 
                row.get("genre"),
                row.get("director"),
                row.get("country"),
                row.get("poster"),
                Integer.parseInt(row.get("minutes"))
            );
            movies.add(mv);
        }
        
        
        return movies;
    }
    
    public ArrayList<Movie> filterMovies(ArrayList<Movie> movies, MovieFilter filter) {
    		ArrayList<Movie> out = new ArrayList<Movie>();
    		for(Movie m: movies) {
    			if(filter.satisfies(m)) {
    				out.add(m);
    			}
    		}
    		return out;
    }
    
    
    public void testLoadMovies() {
        ArrayList<Movie> movies = loadMovies("./data/ratedmoviesfull.csv");
        System.out.println("Total movies in the list are : "+movies.size());
        /*for(Movie m: movies) {
            System.out.println(m);
        }*/
    }
    
    public void testLoadMoviesByGenre() {
        ArrayList<Movie> movies = loadMovies("./data/ratedmovies_short.csv");
        MovieFilter f = new FilterMovieByGenre("Comedy");
        movies = filterMovies(movies, f);
        System.out.println("Total Comedy movies in the list are : "+movies.size());
        /*for(Movie m: movies) {
            System.out.println(m);
        }*/
    }
    
    public void testLoadMoviesByMinutes() {
        ArrayList<Movie> movies = loadMovies("./data/ratedmovies_short.csv");
        MovieFilter f = new FilterMovieByMinutes(150);
        movies = filterMovies(movies, f);
        System.out.println("Total movies in the list longer than 150min are : "+movies.size());
        /*for(Movie m: movies) {
            System.out.println(m);
        }*/
    }
    //fix this 
    public void testLoadMoviesByMaxPlaysDirectors() {
        ArrayList<Movie> movies = loadMovies("./data/ratedmovies_short.csv");
        MovieFilter f = new FilterMovieByMaxDirectors(movies);
        movies = filterMovies(movies, f);
        System.out.println("Total directors with max movies played are : "+movies.size());
        /*for(Movie m: movies) {
            System.out.println(m.getDirector());
        }*/
    }
    
    //=================== END OF MOVIES LOADERS ====================//
    
    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> raters = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);
        for (CSVRecord row: fr.getCSVParser()) {
            Rater rater = new Rater(row.get("rater_id"));
            int raterKey = indexOf(rater, raters);
            
            if(raterKey == -1) { //figure this out !!!
            		raters.add(rater);
            		raterKey = indexOf(rater, raters);
            }
            raters.get(raterKey).addRating(
            		row.get("movie_id"), 
            		Double.parseDouble(row.get("rating"))
            	);
        }
        
        
        return raters;
    }
    
    private int indexOf(Rater rater, ArrayList<Rater> raters) {
    		for (int k=0; k < raters.size(); k++) {
    			if(rater.getID().equals(raters.get(k).getID())) {
    				return k;
    			}
    		}
    		return -1;
    }
    
    public void testLoadRaters() {
    		ArrayList<Rater> raters = loadRaters("./data/ratings_short.csv");
    		System.out.println("Total number of raters : "+raters.size());
    		for(Rater rater: raters ) {
    			System.out.println(rater.getItemsRated().size()+ " total ratings");
    		}
    }
}
