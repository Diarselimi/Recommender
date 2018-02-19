import java.util.*;

import main.Rating;

public class MovieRunnerAverage {
	
	public void printAverageRatings() 
	{
		SecondRatings ratings = new SecondRatings("./data/ratedmoviesfull.csv", "./data/ratings.csv");
		System.out.println("Total movies are: "+ratings.getMovieSize()+" and total ratings: "+ratings.getRaterSize());
		ArrayList<Rating> ratingsAVG = ratings.getAverageRatings(12);
		
		for(Rating rating: ratingsAVG) {
			System.out.println("Avarage rating for movie, "+ratings.getTitle(rating.getItem())+" is "+rating.getValue());
		}
	}
	
	public void getAverageRatingOneMovie() {
		SecondRatings ratings = new SecondRatings("./data/ratedmoviesfull.csv", "./data/ratings.csv");
		for(Rating rating: ratings.getAverageRatings(12)) {
			if(ratings.getTitle(rating.getItem()).equals("Spring Breakers")) {
				System.out.println("Avarage rating for movie, "+ratings.getTitle(rating.getItem())+" is "+rating.getValue());
			}
		}
		
	}
	
	
}
