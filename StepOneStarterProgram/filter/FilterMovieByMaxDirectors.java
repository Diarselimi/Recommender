package filter;

import java.util.*;
import main.Movie;

public class FilterMovieByMaxDirectors implements MovieFilter {

	private HashMap<String, Integer> directorPlays;
	private ArrayList<String> directorsWithMaximumPlays = new ArrayList<String>();
	
	public FilterMovieByMaxDirectors(ArrayList<Movie> movies) {
		directorPlays = new HashMap<String, Integer>(); // init dirextorPlays
		int maxPlays = 1; //number of max plays
		
		for(Movie m: movies) {
			String[] tempDirectors = m.getDirector().split(",");
			for(String direct: tempDirectors) {
				int num = updatePlaysForDirector(direct.trim());
				if(num > maxPlays) maxPlays = num;
			}
		}
		findDirectorsWithMaximumPlays(maxPlays);
	}

	private int updatePlaysForDirector(String direct) {
		directorPlays.put(direct.trim(), directorPlays.get(direct.trim()) != null?directorPlays.get(direct.trim())+1:1);
		return directorPlays.get(direct);
	}

	private void findDirectorsWithMaximumPlays(int maxPlays) {
		for(String key: directorPlays.keySet()) {
			if( directorPlays.get(key) == maxPlays ) {
				directorsWithMaximumPlays.add(key);
			}
		}
	}
	
	public boolean satisfies(Movie movie) {
		String[] dirs = movie.getDirector().split(",");
		for(String dir: dirs) {
			if (this.directorsWithMaximumPlays.contains(dir.trim())) {
				return true;
			}
		}
		return false;
	}

}
