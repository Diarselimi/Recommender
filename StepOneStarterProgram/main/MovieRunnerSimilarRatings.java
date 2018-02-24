package main;

import java.util.ArrayList;
import java.util.Collections;

import filter.AllFilters;
import filter.DirectorsFilter;
import filter.Filter;
import filter.GenreFilter;
import filter.MinutesFilter;
import filter.TrueFilter;
import filter.YearAfterFilter;

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
	
	public void printSimilarRatings() {
		ForthRatings fRatings = new ForthRatings();
		ArrayList<Rating> ratings = fRatings.getSimilarRatings("65", 20, 5, new TrueFilter());
		for(Rating r: ratings) {
			System.out.println("The movie recommended for you is:"+MovieDatabase.getTitle(r.getItem())+" with weight:"+r.getValue());
		}
	}
	
	public void printSimilarRatingsByGenre() {
		ForthRatings fRatings = new ForthRatings();
		ArrayList<Rating> ratings = fRatings.getSimilarRatings("65", 20, 5, new GenreFilter("Action"));
		for(Rating r: ratings) {
			System.out.println("The movie recommended for you is:"+MovieDatabase.getTitle(r.getItem())+" with weight:"+r.getValue());
		}
	}
	
	public void printSimilarRatingsByDirector() {
		ForthRatings fRatings = new ForthRatings();
		ArrayList<Rating> ratings = fRatings.getSimilarRatings("1034", 10, 3, new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone"));
		for(Rating r: ratings) {
			System.out.println("The movie recommended for you is:"+MovieDatabase.getTitle(r.getItem())+" with weight:"+r.getValue());
		}
	}
	
	public void printSimilarRatingsByGenreAndMinutes() {
		ForthRatings fRatings = new ForthRatings();
		AllFilters filters = new AllFilters();
		filters.addFilter(new GenreFilter("Adventure"));
		filters.addFilter(new MinutesFilter(100, 200));
		ArrayList<Rating> ratings = fRatings.getSimilarRatings("65", 10, 5, filters);
		for(Rating r: ratings) {
			System.out.println("The movie recommended for you is:"+MovieDatabase.getTitle(r.getItem())+" with weight:"+r.getValue());
		}
	}
	
	public void printSimilarRatingsByYearAfterAndMinutes() {
		ForthRatings fRatings = new ForthRatings();
		AllFilters filters = new AllFilters();
		filters.addFilter(new YearAfterFilter(2000));
		filters.addFilter(new MinutesFilter(80, 100));
		ArrayList<Rating> ratings = fRatings.getSimilarRatings("65", 10, 5, filters);
		for(Rating r: ratings) {
			System.out.println("The movie recommended for you is:"+MovieDatabase.getTitle(r.getItem())+" with weight:"+r.getValue());
		}
	}
}
