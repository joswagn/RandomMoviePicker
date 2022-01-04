/*
 * Joshua Wagner
 * 12/17/2019
 * This app is to pick a random movie so i don't have to 
 */
package movie;

import java.io.IOException;

public class MoviePicker {

	public static void main(String args[]) throws IOException {
		MoviePickerGui mpg = new MoviePickerGui();
		mpg.setVisible(true);
	}
}