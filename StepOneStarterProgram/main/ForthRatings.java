package main;
import java.util.ArrayList;
import java.util.Collections;

import filter.Filter;
import filter.MinimumRatingsFilter;
import filter.TrueFilter;

public class ForthRatings {
	    
	    public ForthRatings() {
	    		RaterDatabase.initialize("ratings.csv");
	    		MovieDatabase.initialize("ratedmoviesfull.csv");
	    	}
	    
		public ArrayList<Rating> getAverageRatings(int minimalRaters) {
			ArrayList<Rating> moviesAvgRatings = new ArrayList<Rating>();
			ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
			
			for(String movieId: movies) {
				double avgRating = getAverageByID(movieId, minimalRaters);
				if(avgRating > 0.0) {
					Rating rating = new Rating(movieId, avgRating);
					moviesAvgRatings.add(rating);
				}
			}
			
			return moviesAvgRatings;
		}
		
		private double getAverageByID(String movieId, int minimalRaters) {
			double avg = 0.0;
			ArrayList<Double> ratings = new ArrayList<Double>(); 
			for(Rater rater: RaterDatabase.getRaters()) {
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
		
		public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
			
			ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
			ArrayList<Rating> output = new ArrayList<Rating>();
			
			System.out.println("Total raters are: "+RaterDatabase.size());
			ArrayList<Rating> ratingsAVG = getAverageRatings(minimalRaters);
			System.out.println("Total movies in DB: "+MovieDatabase.size());
			
			
			for(Rating rating: ratingsAVG) {
				if(movies.contains(rating.getItem())) {
					output.add(rating);
				}
			}
			System.out.println("Total ratings are:"+output.size()+" \n -----------");
			
			return output;
			
		}
		
		public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) 
		{
			ArrayList<Rating> list = new ArrayList<Rating>();
			ArrayList<Rating> similarities = getSimilarities(id);
			
			for(String movieId: MovieDatabase.filterBy(new TrueFilter())) {
				// find the total ratings for this movie 
				double weighted = 0;
				int counter = 0;
				for(int k=0; k < numSimilarRaters; k++) {
					Rating r = similarities.get(k);// i have here, raterID and weight
					if(RaterDatabase.getRater(r.getItem()).hasRating(movieId)) {
						weighted += RaterDatabase.getRater(r.getItem()).getRating(movieId) * r.getValue();
						counter ++;
					}
				}
				double avg= weighted/counter;
				if(avg> 0 && counter >= minimalRaters) {
					list.add(new Rating(movieId, avg));
				}
			}
			Collections.sort(list, Collections.reverseOrder());
			return list;
		}
		
		private ArrayList<Rating> getSimilarities(String id) {
			ArrayList<Rating> list = new ArrayList<Rating>();
			Rater me = RaterDatabase.getRater(id);
			for(Rater r: RaterDatabase.getRaters()) {
				if(!r.getID().equals(id)) {
					Rating dotProduct = dotProduct(me, r);
					if(dotProduct.getValue() > 0) {
						list.add(dotProduct);
					}
				}
			}
			Collections.sort(list, Collections.reverseOrder());
			
			return list;
		}
		
		private Rating dotProduct(Rater me, Rater r) {
			double product = 0;
			for(String movie: me.getItemsRated()) {
				if(r.getRating(movie) > 0) {
					product += (r.getRating(movie) - 5) * (me.getRating(movie) - 5);
				}
			}
			return new Rating(r.getID(), product);
		}
}
