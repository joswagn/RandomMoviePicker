/*
 * Joshua Wagner
 * 02/19/2019
 * This app is to make sure the files in the class work properly for the movie picker class
 */
package movie;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileHandler {

	private MovieCollection movieCollection;
	private File file;
	private String movieWatch, rootDir;

	public FileHandler(String rootDir) {
		this.rootDir = rootDir;
		movieCollection = new MovieCollection();
		try {
			readInFiles();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File does not exist");
		}
	}

	public void readInFiles() throws IOException {
		checkPath();
	}

	private void checkPath() throws IOException {
		File filename = new File(rootDir); 
		if (!filename.exists()) {
			JOptionPane.showMessageDialog(null, rootDir + " - No such file/directory exists");
			System.exit(0);
		}
		rootlist(rootDir); 
	}

	private void rootlist(String fname) throws IOException {
		File filenames = new File(fname); 
		if (!filenames.isDirectory()) { 
			movieCollection.addMovie(filenames); 
			return;
		}

		String filelists[] = filenames.list();
		for (int i = 0; i < filelists.length; i++) { 
			rootlist(fname + File.separator + filelists[i]); 
		}
	}

	public void chooseRandomMovie() throws IOException {
		file = movieCollection.randomize();
	}
	
	public String setRandomMovieTitle(){
		movieWatch = file.getName();
		trimTitle();
		return movieWatch;
	}
	
	public void openMovie() throws IOException{
		if (Desktop.isDesktopSupported()) {
			Desktop.getDesktop().open(file);
		}
	}
	
	private void trimTitle(){	
		char[] titleArray = movieWatch.toCharArray();

		for(int i=0; i<titleArray.length; i++){
			if(titleArray[i] == '[' || titleArray[i] == '(' ){
				movieWatch = movieWatch.substring(0, i);
				break;
			}
                        if(titleArray[i] == '.'){
                            movieWatch = movieWatch.replace('.', ' ');
                            break;
                        }
                       
		}  
	}
}
