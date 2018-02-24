package main;

import java.util.ArrayList;
import java.util.Random;

import filter.RandomFilter;
import filter.TrueFilter;
import filter.YearAfterFilter;

public class RecommendationRunner implements Recommender {

	public ArrayList<String> getItemsToRate () {
		ArrayList<String> movies = new ArrayList<String>();
		ArrayList<String> list = MovieDatabase.filterBy(new YearAfterFilter(2005));
		int size = list.size();
		for(int k=0; k < 20; k++) {
			Random rand = new Random();
			int next = rand.nextInt(size);
			movies.add(list.get(next));
			list.remove(next);
			size--;
		}
		return movies;
	}
	
	public void printRecommendationsFor (String webRaterID) {
		String output = "";
		ForthRatings fRatings = new ForthRatings();
		ArrayList<Rating> ratings = fRatings.getSimilarRatings(webRaterID, 20, 5, new TrueFilter());
		int maxRecommendations = 20;
		int counter = 0;
		
		String movie_content = "<tr>\n" + 
				"<td class=\"text-left\">__title__</td>\n" + 
				"<td class=\"text-left\"><img src='__image__' width='100px'></td>\n" + 
				"<td class=\"text-left\">__length__</td>\n" + 
				"<td class=\"text-left\">__directors__</td>\n" + 
				"<td class=\"text-left\">__genre__</td>\n" + 
				"</tr>";
		for(Rating r: ratings) {
			output += movie_content;
			output = output.replace("__image__", MovieDatabase.getPoster(r.getItem()));
			output = output.replace("__title__", MovieDatabase.getTitle(r.getItem()));
			output = output.replace("__genre__", MovieDatabase.getGenres(r.getItem()));
			output = output.replace("__directors__", MovieDatabase.getDirector(r.getItem()));
			output = output.replace("__length__", String.valueOf(MovieDatabase.getMinutes(r.getItem())));
			counter ++;
			if(counter >= maxRecommendations) {
				break;
			}
		}
		System.out.print(getPage(output));
		
	}
	
	private String getPage(String movies) {
		String page = "\n" + 
				"\n" + 
				"<style>\n" + 
				"    @import url(https://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100);\n" + 
				"\n" + 
				"body {\n" + 
				"  background-color: #3e94ec;\n" + 
				"  font-family: \"Roboto\", helvetica, arial, sans-serif;\n" + 
				"  font-size: 16px;\n" + 
				"  font-weight: 400;\n" + 
				"  text-rendering: optimizeLegibility;\n" + 
				"}\n" + 
				"\n" + 
				"div.table-title {\n" + 
				"   display: block;\n" + 
				"  margin: auto;\n" + 
				"  max-width: 600px;\n" + 
				"  padding:5px;\n" + 
				"  width: 100%;\n" + 
				"}\n" + 
				"\n" + 
				".table-title h3 {\n" + 
				"   color: #333;\n" + 
				"   font-size: 30px;\n" + 
				"   font-weight: 400;\n" + 
				"   font-style:normal;\n" + 
				"   font-family: \"Roboto\", helvetica, arial, sans-serif;\n" + 
				"   text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);\n" + 
				"   text-transform:uppercase;\n" + 
				"}\n" + 
				"\n" + 
				"\n" + 
				"/*** Table Styles **/\n" + 
				"\n" + 
				".table-fill {\n" + 
				"  background: white;\n" + 
				"  border-radius:3px;\n" + 
				"  border-collapse: collapse;\n" + 
				"  height: 320px;\n" + 
				"  margin: auto;\n" + 
				"  max-width: 600px;\n" + 
				"  padding:5px;\n" + 
				"  width: 100%;\n" + 
				"  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);\n" + 
				"  animation: float 5s infinite;\n" + 
				"}\n" + 
				" \n" + 
				"th {\n" + 
				"  color:#D5DDE5;;\n" + 
				"  background:#1b1e24;\n" + 
				"  border-bottom:4px solid #9ea7af;\n" + 
				"  border-right: 1px solid #343a45;\n" + 
				"  font-size:23px;\n" + 
				"  font-weight: 100;\n" + 
				"  padding:24px;\n" + 
				"  text-align:left;\n" + 
				"  text-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);\n" + 
				"  vertical-align:middle;\n" + 
				"}\n" + 
				"\n" + 
				"th:first-child {\n" + 
				"  border-top-left-radius:3px;\n" + 
				"}\n" + 
				" \n" + 
				"th:last-child {\n" + 
				"  border-top-right-radius:3px;\n" + 
				"  border-right:none;\n" + 
				"}\n" + 
				"  \n" + 
				"tr {\n" + 
				"  border-top: 1px solid #C1C3D1;\n" + 
				"  border-bottom-: 1px solid #C1C3D1;\n" + 
				"  color:#666B85;\n" + 
				"  font-size:16px;\n" + 
				"  font-weight:normal;\n" + 
				"  text-shadow: 0 1px 1px rgba(256, 256, 256, 0.1);\n" + 
				"}\n" + 
				" \n" + 
				"tr:hover td {\n" + 
				"  background:#4E5066;\n" + 
				"  color:#FFFFFF;\n" + 
				"  border-top: 1px solid #22262e;\n" + 
				"}\n" + 
				" \n" + 
				"tr:first-child {\n" + 
				"  border-top:none;\n" + 
				"}\n" + 
				"\n" + 
				"tr:last-child {\n" + 
				"  border-bottom:none;\n" + 
				"}\n" + 
				" \n" + 
				"tr:nth-child(odd) td {\n" + 
				"  background:#EBEBEB;\n" + 
				"}\n" + 
				" \n" + 
				"tr:nth-child(odd):hover td {\n" + 
				"  background:#4E5066;\n" + 
				"}\n" + 
				"\n" + 
				"tr:last-child td:first-child {\n" + 
				"  border-bottom-left-radius:3px;\n" + 
				"}\n" + 
				" \n" + 
				"tr:last-child td:last-child {\n" + 
				"  border-bottom-right-radius:3px;\n" + 
				"}\n" + 
				" \n" + 
				"td {\n" + 
				"  background:#FFFFFF;\n" + 
				"  padding:20px;\n" + 
				"  text-align:left;\n" + 
				"  vertical-align:middle;\n" + 
				"  font-weight:300;\n" + 
				"  font-size:18px;\n" + 
				"  text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);\n" + 
				"  border-right: 1px solid #C1C3D1;\n" + 
				"}\n" + 
				"\n" + 
				"td:last-child {\n" + 
				"  border-right: 0px;\n" + 
				"}\n" + 
				"\n" + 
				"th.text-left {\n" + 
				"  text-align: left;\n" + 
				"}\n" + 
				"\n" + 
				"th.text-center {\n" + 
				"  text-align: center;\n" + 
				"}\n" + 
				"\n" + 
				"th.text-right {\n" + 
				"  text-align: right;\n" + 
				"}\n" + 
				"\n" + 
				"td.text-left {\n" + 
				"  text-align: left;\n" + 
				"}\n" + 
				"\n" + 
				"td.text-center {\n" + 
				"  text-align: center;\n" + 
				"}\n" + 
				"\n" + 
				"td.text-right {\n" + 
				"  text-align: right;\n" + 
				"}\n" + 
				"\n" + 
				"</style>\n" + 
				"<div class=\"table-title\">\n" + 
				"<h3>Recommended Movies</h3>\n" + 
				"</div>\n" + 
				"<table class=\"table-fill\">\n" + 
				"<thead>\n" + 
				"<tr>\n" + 
				"<th class=\"text-left\">Title</th>\n" + 
				"<th class=\"text-left\">Image</th>\n" + 
				"<th class=\"text-left\">Length</th>\n" + 
				"<th class=\"text-left\">Directors</th>\n" + 
				"<th class=\"text-left\">Genre</th>\n" + 
				"</tr>\n" + 
				"</thead>\n" + 
				"<tbody class=\"table-hover\">\n" + 
				"\n" + 
				movies+
				"\n" + 
				"</tbody>\n" + 
				"</table>\n" + 
				"  \n" + 
				"\n" + 
				"  </body>";
		return page;
		
	}
}
