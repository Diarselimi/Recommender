import main.Rating;

public class MovieRunnerAverage {
	
	public void printAverageRatings() 
	{
		SecondRatings ratings = new SecondRatings("./data/ratedmovies_short.csv", "./data/ratings_short.csv");
		System.out.println("Total movies are: "+ratings.getMovieSize()+" and total ratings: "+ratings.getRaterSize());
		for(Rating rating: ratings.getAverageRatings(3)) {
			System.out.println("Avarage rating for movie, "+ratings.getTitle(rating.getItem())+" is "+rating.getValue());
		}
	}
	
	
}
