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
    
    public ArrayList<String> filterMoviesByDirector(ArrayList<Movie> allMovies) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        HashMap<String, Integer> directorMovies= new HashMap<String, Integer>();
        for (Movie mv: allMovies) {
            String[] directors = mv.getDirector().split(",");
            try {
                for(String director: directors) {
                    if(directorMovies.containsKey(director.trim())) {
                        int plays = directorMovies.get(director.trim()) + 1;
                        directorMovies.put(director.trim(), plays);
                    } else {
                        directorMovies.put(director.trim(), 1);
                    }   
                } 
            } catch (Exception e) {
                //directorMovies.put(director.trim(), 1);
            }
        }
        int maxMoviesPlayed = 0;
        for(int k=0; k < directorMovies.size(); k++) {
            if (directorMovies.get(k) > maxMoviesPlayed) {
                maxMoviesPlayed = directorMovies.get(k);
            }
        }
        ArrayList<String> directorsMaxPlays = new ArrayList<String>();
        for(String key: directorMovies.keySet()) {
            if(directorMovies.get(key) == maxMoviesPlayed) {
                directorsMaxPlays.add(key);
            }
        }
        return directorsMaxPlays;
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
    
    public void testLoadMoviesByDirectors() {
        ArrayList<Movie> movies = loadMovies("./data/ratedmovies_short.csv");
        MovieFilter f = new FilterMovieByDirector();
        movies = filterMovies(movies, f);
        System.out.println("Total directors with max movies played are : "+movies.size());
        /*for(Movie m: movies) {
            System.out.println(m);
        }*/
    }
}
