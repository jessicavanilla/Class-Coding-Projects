//Jessica Villanueva
package fileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class fileRead {
	// Fields
	private ArrayList<String> lines;
	
	// Constructor
	public fileRead(String filename) throws IOException{
		lines = new ArrayList<>();
		BufferedReader fr = new BufferedReader (new FileReader(filename));
		String l;
		while ((l = fr.readLine()) != null){
			lines.add(l);
		}
		fr.close();
	}
	
	// Methods
	public int getNumberOfLines(){
		return lines.size();
	}
	
	public String getLine(int index){
		if (index >= lines.size()){
			return "Out of bounds";
		}
		else {
			return lines.get(index);
		}
	}
}
