package main;
import java.util.ArrayList;

import filter.Filter;
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
		
		private ArrayList<Rating> dotProduct(Rater me, Rater r) {
			ArrayList<Rating> dotProduct = new ArrayList<Rating>();
			for(String movie: me.getItemsRated()) {
				if(r.getRating(movie) > 0) {
					double product = (r.getRating(movie) - 5) * (me.getRating(movie) - 5);
					dotProduct.add(new Rating(movie, product));
				}
			}
			return dotProduct;
		}
}
