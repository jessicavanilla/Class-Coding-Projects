//Jessica Villanueva
package dataManager;

public class Movie {
	// Fields
	private String title;
	private String actor1;
	private String actor2;
	private String director;
	private int year;
	private int runtimeMinutes;
	private String entry;
	
	// Constructor
	public Movie(String title, String actor1, String actor2, String director, int year, int runtimeMinutes){
		this.title = title;
		this.actor1 = actor1;
		this.actor2 = actor2;
		this.director = director;
		this.year = year;
		this.runtimeMinutes = runtimeMinutes;
		entry = title + "*" + actor1 + "*" + actor2 + "*" + director + "*" + year + "*" + runtimeMinutes;
	}

	// Methods
	public String getLine(){
		return entry;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getActor1(){
		return actor1;
	}
	
	public String getActor2(){
		return actor2;
	}
	
	public String getDirector(){
		return director;
	}
	
	public int getYear(){
		return year;
	}
	
	public int getRuntime(){
		return runtimeMinutes;
	}
	
	// Optional
	public boolean isActorInMovie(String actor){
		if (actor.toLowerCase().trim() == actor1.toLowerCase() || actor.toLowerCase().trim() == actor2.toLowerCase()){
			return true;
		} else {
			return false;
		}
	}
}
