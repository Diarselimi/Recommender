package main;
import java.util.*;
import filter.*;

public class MovieRunnerWithFilters {
	
	public void printAverageRatings() 
	{
		ThirdRatings ratings = new ThirdRatings("./data/ratings.csv");
		System.out.println("Total raters are: "+ratings.getRaterSize());
		MovieDatabase.initialize("ratedmoviesfull.csv");
		
		System.out.println("Total movies in DB: "+MovieDatabase.size());
		
		ArrayList<Rating> ratingsAVG = ratings.getAverageRatings(35);
		Collections.sort(ratingsAVG);

		System.out.println("Total ratings"+ratingsAVG.size()+"\n ---------");
//		for(Rating rating: ratingsAVG) {
//			System.out.println("Avarage rating for "+MovieDatabase.getTitle(rating.getItem())+" is "+rating.getValue());
//		}
		
	}
	
	public void printAverageRatingsByYear() {
		Filter yearAfter = new YearAfterFilter(2000);
		ArrayList<Rating> ratings = getAverageRatingsByFilter(20, yearAfter);
		Collections.sort(ratings);
		
//		for(Rating rating: ratings) {
//			System.out.println("Avarage rating for "+MovieDatabase.getTitle(rating.getItem())+" is "+rating.getValue());
//		}
	}
	
	public void printAverageRatingsByGenre() {
		Filter filter = new GenreFilter("Comedy");
		ArrayList<Rating> ratings = getAverageRatingsByFilter(20, filter);
		Collections.sort(ratings);
		
//		for(Rating rating: ratings) {
//			System.out.println("Avarage rating for "+MovieDatabase.getTitle(rating.getItem())+" is "+rating.getValue()+" "+MovieDatabase.getGenres(rating.getItem()));
//		}
	}
	
	public void printAverageRatingsByMinutes() {
		Filter filter = new MinutesFilter(105, 135);
		ArrayList<Rating> ratings = getAverageRatingsByFilter(5, filter);
		Collections.sort(ratings);
		
//		for(Rating rating: ratings) {
//			System.out.println("Avarage rating for "+MovieDatabase.getTitle(rating.getItem())+" is "+rating.getValue()+" "+MovieDatabase.getMinutes(rating.getItem()));
//		}
	}
	
	public void printAverageRatingsByDirectors() {
		Filter filter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
		ArrayList<Rating> ratings = getAverageRatingsByFilter(4, filter);
		Collections.sort(ratings);
		
//		for(Rating rating: ratings) {
//			System.out.println("Avarage rating for "+MovieDatabase.getTitle(rating.getItem())+" is "+rating.getValue()+" "+MovieDatabase.getDirector(rating.getItem()));
//		}
	}
	
	public void printAverageRatingsByYearAfterAndGenre() {
		AllFilters fil = new AllFilters();
		fil.addFilter(new GenreFilter("Drama"));
		fil.addFilter(new YearAfterFilter(1990));
		ArrayList<Rating> ratings = getAverageRatingsByFilter(8, fil);
		Collections.sort(ratings);
		
//		for(Rating rating: ratings) {
//			System.out.println("Avarage rating for "+MovieDatabase.getTitle(rating.getItem())+" is "+rating.getValue()+" \n"+MovieDatabase.getGenres(rating.getItem()));
//		}
	}
	
	public void printAverageRatingsByDirectorsAndMinutes() {
		AllFilters fil = new AllFilters();
		fil.addFilter(new MinutesFilter(90, 180));
		fil.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
		ArrayList<Rating> ratings = getAverageRatingsByFilter(3, fil);
		Collections.sort(ratings);
		// Print the number of movies found, and for each movie, print its rating, its time length, and its title on one line, and all its directors on the next line.
//		for(Rating rating: ratings) {
//			System.out.println("Avarage rating for "+MovieDatabase.getTitle(rating.getItem())+" is "+rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem())+ " \n "+MovieDatabase.getDirector(rating.getItem()));
//		}
	}
	
	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
		
		ArrayList<Rating> output = new ArrayList<Rating>();
		ThirdRatings ratings = new ThirdRatings("./data/ratings.csv");
		System.out.println("Total raters are: "+ratings.getRaterSize());
		ArrayList<Rating> ratingsAVG = ratings.getAverageRatings(minimalRaters);
		System.out.println("Total movies in DB: "+MovieDatabase.size());
		
		
		for(Rating rating: ratingsAVG) {
			if(movies.contains(rating.getItem())) {
				output.add(rating);
			}
		}
		System.out.println("Total ratings are:"+output.size()+" \n -----------");
		
		return output;
		
	}
}
