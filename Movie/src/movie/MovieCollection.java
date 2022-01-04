/*
 * Joshua Wagner
 * 12/17/2019
 * this program is to add movies to an array for the file handler class
 */
package movie;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class MovieCollection {

	private ArrayList<File> fileList;

	public MovieCollection() {
		fileList = new ArrayList<File>();
	}

	public void addMovie(File file) {
		fileList.add(file);
	}

	public File randomize() {
		Random generator = new Random();
		File file = fileList.remove(generator.nextInt(fileList.size()));
		return file;
	}
}