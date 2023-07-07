//Jessica Villanueva
package dataManager;

import java.io.IOException;
import java.util.Scanner;


public class keyboardInput {
	// Fields
	private Scanner keyb;
	int year;
	int runtime;
	Database db;
	
	// Constructor
	public keyboardInput() throws IOException{
		keyb = new Scanner(System.in);
		db = new Database("db.txt");
	}
	
	// Methods
	public String getKeyboardLine(){
		String input = keyb.nextLine();
		return input;
	}
	
	public String commandPrompt() throws IOException{
		keyboardInput s = new keyboardInput(); //create object
		System.out.println("Enter command > "); //prompt user
		String inp = s.getKeyboardLine(); //save input into variable
		return inp;
	}
	
	public void processInput(String inp) throws IOException{
		keyboardInput s = new keyboardInput();
		switch(inp.toLowerCase().trim()){
		case "help":
			System.out.println("Available commands: new entry, search by title, search by actor, search by director, ");
			System.out.println("search by year, search by runtime, delete entry, see all titles, or quit");
			String input = s.commandPrompt(); //prompt user again
			s.processInput(input); //process input
			break;
		case "new entry":
			//retrieve input for every field
			System.out.println("If field is empty, input an x ('x') instead.");
			System.out.println("If year or runtime is empty, input zero ('0') instead.");
			System.out.println("Enter title > ");
			String title = s.getKeyboardLine().trim();
			System.out.println("Enter actor 1 > ");
			String act1 = s.getKeyboardLine().trim();
			System.out.println("Enter actor 2 > ");
			String act2 = s.getKeyboardLine().trim();
			System.out.println("Enter director > ");
			String director = s.getKeyboardLine().trim();
			System.out.println("Enter year > ");
			String temp = s.getKeyboardLine().trim();
			try{ //in case letter is inputed
				year = Integer.parseInt(temp);
			} catch (NumberFormatException e){
				System.out.println("Failed to input valid number");
				String input2 = s.commandPrompt();
				s.processInput(input2);
				break;
			}
			System.out.println("Enter runtime in minutes > ");
			temp = s.getKeyboardLine().trim();
			try{ //in case letter is inputed
				runtime = Integer.parseInt(temp);
			} catch (NumberFormatException e){
				System.out.println("Failed to input valid number");
				String input2 = s.commandPrompt();
				s.processInput(input2);
				break;
			}
			
			//pass all inputs into movie class
			Movie m = new Movie(title, act1, act2, director, year, runtime);
			db.addEntry(m);
			
			//return to primary command
			input = s.commandPrompt();
			s.processInput(input);
			break;
		case "search by title":
			//prompt for title
			System.out.println("Enter title > ");
			String searchTitle = s.getKeyboardLine();
			
			//search through database for title
			db.searchByTitle(searchTitle);
			
			//return to primary command
			input = s.commandPrompt();
			s.processInput(input);
			break;
		case "search by actor":
			//prompt for actor
			System.out.println("Enter actor > ");
			String searchActor = s.getKeyboardLine();
			
			//search through database for director
			db.searchByActor(searchActor);
			
			//return to primary command
			input = s.commandPrompt();
			s.processInput(input);
			break;
		case "search by director":
			//prompt for director
			System.out.println("Enter director > ");
			String searchDirector = s.getKeyboardLine();
			
			//search through database for director
			db.searchByDirector(searchDirector);
			
			//return to primary command
			input = s.commandPrompt();
			s.processInput(input);
			break;
		case "search by year":
			//prompt for year
			System.out.println("Enter year > ");
			String searchYear = s.getKeyboardLine();
			
			//search through database for year
			try {
				db.searchByYear(Integer.parseInt(searchYear));
			} catch (NumberFormatException e){
				System.out.println("Invalid number");
			}
			
			//return to primary command
			input = s.commandPrompt();
			s.processInput(input);
			break;
		case "search by runtime":
			//prompt for runtime
			System.out.println("Enter runtime in minutes > ");
			String searchRuntime = s.getKeyboardLine();
			
			//search through database for runtime
			try {
				db.searchByRuntime(Integer.parseInt(searchRuntime));
			} catch (NumberFormatException e){
				System.out.println("Invalid number");
			}
			
			//return to primary command
			input = s.commandPrompt();
			s.processInput(input);
			break;
		case "delete entry":
			//prompt for title
			System.out.println("Enter title to delete > ");
			String deleteEntry = s.getKeyboardLine();
			
			//call delete entry method
			db.deleteEntry(deleteEntry);
			
			//return to primary command
			input = s.commandPrompt();
			s.processInput(input);
			break;
		case "see all titles":
			//call all title method
			db.allTitles();
			
			//return to primary command
			input = s.commandPrompt();
			s.processInput(input);
			break;
		case "quit":
			System.out.println("Thanks for stopping by! :)");
			break;
		default:
			System.out.println("Invalid command");
			input = s.commandPrompt(); //prompt user again
			s.processInput(input); //process input
		}
	}
	
	public String processDecision (String inp){
		if (inp.toLowerCase().trim().equals("y")){
			return "true";
		}else if (inp.toLowerCase().trim().equals("n")){
			return "false";
		}else {
			System.out.println("Invalid input.");
			return "fail";
		}
	}
	
	/* Call this method before you exit the program! Do NOT close the scanner object inside of getKeyboardLine method! */
	public void closeKeyboard(){
		keyb.close();
	}
}
