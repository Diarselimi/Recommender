package main;

import java.util.ArrayList;
import java.util.Collections;

import filter.Filter;
import filter.GenreFilter;

public class MovieRunnerSimilarRatings {
	
	public void printAverageRatings() 
	{
		ForthRatings ratings = new ForthRatings();
		
		ArrayList<Rating> ratingsAVG = ratings.getAverageRatings(35);
		Collections.sort(ratingsAVG);

		System.out.println("Total ratings"+ratingsAVG.size()+"\n ---------");
//		for(Rating rating: ratingsAVG) {
//			System.out.println("Avarage rating for "+MovieDatabase.getTitle(rating.getItem())+" is "+rating.getValue());
//		}
		
	}
	
	public void printAverageRatingsByGenre() {
		ForthRatings fRatings = new ForthRatings();
		Filter filter = new GenreFilter("Comedy");
		ArrayList<Rating> ratings = fRatings.getAverageRatingsByFilter(20, filter);
		Collections.sort(ratings);
		
		System.out.println("Total ratings found: "+ratings.size());
//		for(Rating rating: ratings) {
//			System.out.println("Avarage rating for "+MovieDatabase.getTitle(rating.getItem())+" is "+rating.getValue()+" "+MovieDatabase.getGenres(rating.getItem()));
//		}
	}
}
