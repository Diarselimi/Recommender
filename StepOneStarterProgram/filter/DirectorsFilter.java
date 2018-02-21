package filter;

import main.*;

public class DirectorsFilter implements Filter {
	String[] directors;
	public DirectorsFilter(String directors) {
		this.directors = directors.split(",");
	}
	
	@Override
	public boolean satisfies(String id) {
		for(String director: this.directors) {
			if(MovieDatabase.getDirector(id).contains(director)) {
				return true;
			}
		}
		return false;
	}

}
