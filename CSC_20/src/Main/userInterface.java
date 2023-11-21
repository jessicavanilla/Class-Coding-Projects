//Jessica Villanueva
package Main;

import java.io.IOException;
import dataManager.keyboardInput;

public class userInterface {
	public static void main (String[] args) throws IOException {

		keyboardInput s = new keyboardInput();
		
		//Opening statement
		System.out.println("Welcome to Filmation, the search query for movies!");
		System.out.println("Enter 'help' to see all available commands or type 'quit' to exit.");
		
		//prompt user
		String input = s.commandPrompt();
		
		//process input
		s.processInput(input);
		
		//close scanner class
		s.closeKeyboard();
		
	}
}