package main;
import java.util.*;

import filter.*;

public class MovieRunnerWithFilters {
	
	public void printAverageRatings() 
	{
		ThirdRatings ratings = new ThirdRatings("./data/ratings_short.csv");
		System.out.println("Total raters are: "+ratings.getRaterSize());
		MovieDatabase.initialize("ratedmovies_short.csv");
		
		System.out.println("Total movies in DB: "+MovieDatabase.size());
		
		ArrayList<Rating> ratingsAVG = ratings.getAverageRatings(1);
		Collections.sort(ratingsAVG);
		
		for(Rating rating: ratingsAVG) {
			System.out.println("Avarage rating for "+MovieDatabase.getTitle(rating.getItem())+" is "+rating.getValue());
		}
		
	}
	
	public void printAverageRatingsByYear() {
		Filter yearAfter = new YearAfterFilter(2000);
		ArrayList<Rating> ratings = getAverageRatingsByFilter(1, yearAfter);
		Collections.sort(ratings);
		
		for(Rating rating: ratings) {
			System.out.println("Avarage rating for "+MovieDatabase.getTitle(rating.getItem())+" is "+rating.getValue());
		}
	}
	
	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
		ArrayList<Rating> output = new ArrayList<Rating>();
		ThirdRatings ratings = new ThirdRatings("./data/ratings_short.csv");
		ArrayList<Rating> ratingsAVG = ratings.getAverageRatings(minimalRaters);
		MovieDatabase.initialize("ratedmovies_short.csv");
		ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
		
		for(Rating rating: ratingsAVG) {
			if(movies.contains(rating.getItem())) {
				output.add(rating);
			}
		}
		
		return output;
		
	}
}