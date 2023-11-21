//Jessica Villanueva
package dataManager;

import java.io.IOException;
import java.util.ArrayList;
import fileManager.fileRead;
import fileManager.fileWrite;
import java.util.StringTokenizer;


public class Database {
	// Fields
	private static ArrayList<Movie> movies;
	private static String filename;
	StringTokenizer st, st2;
	keyboardInput s;
	static Movie m;
	boolean found = false;
	
	// Constructor
	public Database(String filename) throws IOException{
		Database.filename = filename;
		movies = new ArrayList<>();
		fileRead fr = new fileRead(filename);
		for (int i = 0; i < fr.getNumberOfLines(); i++){
			String raw = fr.getLine(i);
			st = new StringTokenizer(raw, "*");
			String t = st.nextToken();
			String a1 = st.nextToken();
			String a2 = st.nextToken();
			String d = st.nextToken();
			int y = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			m = new Movie(t, a1, a2, d, y, r);
			movies.add(m);
		}
	}
	
	// Methods
	public void addEntry(Movie newEntry) throws IOException{
		//check if title is valid
		if(newEntry.getTitle().length() > 2){
			movies.add(newEntry);					//add to arraylist
			fileWrite fw = new fileWrite(filename);
			for (int i = 0; i < movies.size(); i++){
				fw.writeLine(movies.get(i).getLine()); //write into text file
			}
			fw.saveFile();		//close file
			System.out.println("Successfully added entry into database.");
		} else {
			System.out.println("Not a valid title. Failed to add to database.");
		}
	}
	
	public void searchByTitle(String title){
		found = false;
		for (int i = 0; i < movies.size(); i++){
			if (movies.get(i).getTitle().toLowerCase().equals(title.toLowerCase().trim())){
				st2 = new StringTokenizer(movies.get(i).getLine(), "*"); //parse through array list entries
				st2.nextToken(); //skip title token
				//print found title details
				System.out.println("Actors: " + st2.nextToken() + ", " + st2.nextToken());
				System.out.println("Director: " + st2.nextToken());
				System.out.println("Year: " + st2.nextToken());
				System.out.println("Runtime: " + st2.nextToken() + " minutes");
				found = true; //update boolean
				break;
			}
		}
		
		if (found == false){
			System.out.println(title + " not found.");
		}
	}
	
	public void searchByActor(String actor){
		found = false;
		System.out.println("Title(s): ");
		for (int i = 0; i < movies.size(); i++){
			if (movies.get(i).getActor1().toLowerCase().equals(actor.toLowerCase().trim()) || movies.get(i).getActor2().toLowerCase().equals(actor.toLowerCase().trim())){
				st2 = new StringTokenizer(movies.get(i).getLine(), "*"); //parse through array list entries
				System.out.println(st2.nextToken()); //print title
				found = true; //update boolean
			}
		}
		
		if (found == false){
			System.out.println("None found for that actor");
		}
	}
	
	public void searchByDirector(String director){
		found = false;
		System.out.println("Title(s): ");
		for (int i = 0; i < movies.size(); i++){
			if (movies.get(i).getDirector().toLowerCase().equals(director.toLowerCase().trim())){
				st2 = new StringTokenizer(movies.get(i).getLine(), "*"); //parse through array list entries
				System.out.println(st2.nextToken()); //print title
				found = true; //update boolean
			}
		}
		
		if (found == false){
			System.out.println("None found for that director");
		}
	}
	
	public void searchByYear(int year){
		found = false;
		System.out.println("Title(s): ");
		for (int i = 0; i < movies.size(); i++){
			if (movies.get(i).getYear() == year){
				st2 = new StringTokenizer(movies.get(i).getLine(), "*"); //parse through array list entries
				System.out.println(st2.nextToken()); //print title
				found = true; //update boolean
			}
		}
		
		if (found == false){
			System.out.println("None found for that year");
		}
	}
	
	public void searchByRuntime(int runtime){
		found = false;
		System.out.println("Title(s): ");
		for (int i = 0; i < movies.size(); i++){
			if (movies.get(i).getRuntime() == runtime){
				st2 = new StringTokenizer(movies.get(i).getLine(), "*"); //parse through array list entries
				System.out.println(st2.nextToken()); //print title
				found = true; //update boolean
			}
		}
		
		if (found == false){
			System.out.println("None found for that runtime");
		}
	}
	
	public void deleteEntry (String title) throws IOException{
		found = false;
		s = new keyboardInput();
		for (int i = 0; i < movies.size(); i++){
			if (movies.get(i).getTitle().toLowerCase().equals(title.toLowerCase().trim())){
				searchByTitle(title); //print title details
				System.out.println("Are you sure this is the entry you wish to delete? (Enter y or n)");
				String input = s.getKeyboardLine(); //prompt user; retrieve input
				String del = s.processDecision(input); //process decision
				
				if (del == "true"){
					st2 = new StringTokenizer(movies.get(i).getLine(), "*"); //parse through line
					movies.remove(i); //pass movie object into array list to remove
					
					//rewrite database file without entry
					fileWrite fw2 = new fileWrite(filename);
					for (int j = 0; j < movies.size(); j++){
						fw2.writeLine(movies.get(j).getLine()); //write into text file
					}
					fw2.saveFile();		//close file
					
					System.out.println("Successfully deleted entry.");
					found = true;
					break;
				} else if (del == "false"){ //if input was no
					System.out.println("Cancelled deletion of entry.");
				}
			}
		}
		
		if(found == false){ //if title was not found
			System.out.println("Title not found. Failed to delete entry.");
		}
	}
	
	public void allTitles () {
		System.out.println("All current available titles:");
		
		//iterate through all lines of arraylist
		for (int i = 0; i < movies.size(); i++){
			System.out.println(movies.get(i).getTitle());
		}
	}
}
