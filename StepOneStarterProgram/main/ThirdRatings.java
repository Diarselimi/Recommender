package main;
import java.util.ArrayList;

import filter.TrueFilter;

public class ThirdRatings {
	    private ArrayList<Rater> myRaters;
	    
	    public ThirdRatings() {
	    	  this("ratings.csv");
	    	}
	    
		public ThirdRatings(String filename) {
			FirstRatings first = new FirstRatings();	
			myRaters = first.loadRaters(filename);
		}	
		
		
		public int getRaterSize(){
			return myRaters.size();
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
