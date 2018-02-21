import java.util.*;

import main.Rating;
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
	
	public void getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
		
	}
}
