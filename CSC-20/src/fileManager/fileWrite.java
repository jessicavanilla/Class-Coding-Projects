/* READ THIS!: The idea behind this class is that we save the "writeBuffer" in memory but do not actually write the file to disk until someone calls the 
 * "saveFile" method. The reason for this is for performance and to prevent keeping an open file pointer (which is poor form and risky) */
//Jessica Villanueva

package fileManager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class fileWrite {
	// Fields
	private ArrayList<String> writeBuffer;
	private String filename;
	private FileWriter fw;
	
	// Constructor
	public fileWrite(String filename){
		this.filename = filename;			// Save filename for later
		writeBuffer = new ArrayList<>();
	}
	
	//Methods
	public void writeLine(String newLine){
		writeBuffer.add(newLine);
	}
	
	public void saveFile() throws IOException{
		try {
			fw = new FileWriter(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < writeBuffer.size(); i++){
			fw.write(writeBuffer.get(i));
			fw.write("\n");
		}
		fw.close();
	}
}
