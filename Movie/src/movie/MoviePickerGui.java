/*
 * Joshua Wagner
 * 12/19/2019
 */
package movie;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;


@SuppressWarnings("serial")
public class MoviePickerGui extends JFrame implements ActionListener {

	private JButton next, open, quit;
	private JLabel movieTitle;
	private JPanel buttonPanel, titlePanel;
	private JSplitPane topBotSplit;
	private JFileChooser fileChooser;
	private Font font = new Font("serif", Font.BOLD, 50);
	private FileHandler fileHandler;

	
	public MoviePickerGui(){
		this.setSize(1200,350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Random Movie Picker v4 -------- " +
				"Created By Joshua Wagner");
		
		initialize();
	}
	
	private void initialize(){
		setupRootDirectory();
		setupButtons();
		setupLabels();
		setupPanels();
		setupSplitPane();
		next();
	}
	
	private void setupRootDirectory() {
		fileChooser = new JFileChooser();
		
		JOptionPane.showMessageDialog(null, "Please select the file where your movies are located.");
		
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int flag = fileChooser.showOpenDialog(this);
		
		if(flag == JFileChooser.CANCEL_OPTION){
			System.exit(0);
		}
		
		File file = fileChooser.getSelectedFile();
		System.out.print(file.toString());
		fileHandler  = new FileHandler(file.toString());
	}
	
	private void setupButtons() {
		next = new JButton("Nope");
		open = new JButton("Watch it!");
		quit = new JButton("Exit");
		
		next.addActionListener(this);
		open.addActionListener(this);
		quit.addActionListener(this);
	}
	
	private void setupLabels() {
		movieTitle = new JLabel();
		movieTitle.setFont(font);
	}
	
	private void setupPanels(){
		buttonPanel = new JPanel(new GridLayout(1,3));
		buttonPanel.add(open);
		buttonPanel.add(next);
		buttonPanel.add(quit);
		
		titlePanel = new JPanel();
		titlePanel.add(movieTitle);
	}
	
	private void setupSplitPane() {
		topBotSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titlePanel, buttonPanel);
		topBotSplit.setDividerLocation(250);
		
		this.add(topBotSplit);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == next)
			next();

		else if(e.getSource() == open)
			open();

		else if(e.getSource() == quit)
			System.exit(0);
	}
	
	private void next() {
            int skipp = 0;
		try {
			fileHandler.chooseRandomMovie();
			
			movieTitle.setText(fileHandler.setRandomMovieTitle() + "?");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "File does not exist");
		} catch (IllegalArgumentException e1){
			JOptionPane.showMessageDialog(null, "No more movies to select, repopulating list");
			try {
				fileHandler.readInFiles();
				//next();
			} catch (IOException e2) {
				JOptionPane.showMessageDialog(null, "Could not repopulate, files do not exist");
			}
		}
	}
	
	private void open() {
		try {
			if(Desktop.isDesktopSupported()){
				fileHandler.openMovie();
				System.exit(0);
			}
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "File does not exist");
		}
	}
	
}