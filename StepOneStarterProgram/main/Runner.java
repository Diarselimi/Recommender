package main;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		FirstRatings fr = new FirstRatings();
//		fr.testLoadMovies();
//		fr.testLoadMoviesByMinutes();
//		fr.testLoadMoviesByGenre();
//		fr.testLoadRaters();
//		fr.testFilterRaterByRater();
//		fr.testGetMaxRatingsRater();
//		fr.testLoadMoviesByMaxPlaysDirectors();
		
//		fr.testLoadRaters();
		
//		MovieRunnerAverage mra = new MovieRunnerAverage();
//		mra.printAverageRatings();
//		mra.getAverageRatingOneMovie();
		
		MovieRunnerWithFilters mwf = new MovieRunnerWithFilters();
//		mwf.printAverageRatings();
//		mwf.printAverageRatingsByYear();
//		mwf.printAverageRatingsByGenre();
//		mwf.printAverageRatingsByMinutes();
//		mwf.printAverageRatingsByDirectors();
//		mwf.printAverageRatingsByYearAfterAndGenre();
//		mwf.printAverageRatingsByDirectorsAndMinutes();
		MovieRunnerSimilarRatings mrs = new MovieRunnerSimilarRatings();
//		mrs.printAverageRatings();
//		mrs.printSimilarRatings();
//		mrs.printSimilarRatingsByGenre();
//		mrs.printSimilarRatingsByDirector();
//		mrs.printSimilarRatingsByGenreAndMinutes();
//		mrs.printSimilarRatingsByYearAfterAndMinutes();
		
		RecommendationRunner runn = new RecommendationRunner();
		runn.printRecommendationsFor("71");
	}

}
