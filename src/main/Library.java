package main;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.TextField;
import javax.swing.JSplitPane;

import library.File.LibraryFile;
import library.Pane.LibraryShowTags;
import library.Pane.LibraryTextScrollPane;
import library.Pane.LibraryTextTagsImagePane;
import library.TextField.LibrarySearchBar;


public class Library 
{
	private JFrame frame;
	private List<LibraryFile> catalog = new ArrayList<LibraryFile>();
	
	/**************************
	 **Launch the application**
	 **************************/
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					new Library();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**************************
	 **Create the application**
	 **************************/
	public Library() 
	{
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 600, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		compileLibrary ();
	}
		
	
	/**************************
	 ******Compile Library*****
	 **************************/
	private void compileLibrary () 
	{
		/**************************
		 ******Catalog Files*******
		 **************************/
		try
		{
			Files.walk(Paths.get("./img"))
				 .filter(Files::isRegularFile)
				 .filter(e -> e.toString().toLowerCase().contains(".jpeg") || 
					  	   	  e.toString().toLowerCase().contains(".jpg") ||
					  	   	  e.toString().toLowerCase().contains(".png") ||
					  	   	  e.toString().toLowerCase().contains(".tiff"))
				 .forEach(e -> catalog.add(new LibraryFile(FilenameUtils.getBaseName(e.toString()), e.toString(), FilenameUtils.getExtension(e.toString()))));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catalog.forEach(t -> t.tags.add("Default")); 
		
		/**************************
		 *******Add to Frame*******
		 **************************/
//		LibrarySearchBar librarySearchBar = new LibrarySearchBar(catalog);
//		LibraryAddTags libraryAddTags = new LibraryAddTags(catalog.get(0));
//		LibraryImagePane libraryImagePane = new LibraryImagePane(catalog.get(0).file.getPath());
//		LibraryTextScrollPane libraryTextScrollPane = new LibraryTextScrollPane(catalog);
//		libraryTextTagsImagePane.selectedLibraryFile
//		frame.getContentPane().add(librarySearchBar);
//		frame.getContentPane().add(libraryAddTags);
//		frame.getContentPane().add(libraryImagePane);
//		frame.getContentPane().add(libraryTextScrollPane);
		
		LibraryTextTagsImagePane libraryTextTagsImagePane = new LibraryTextTagsImagePane(catalog);
		frame.getContentPane().add(libraryTextTagsImagePane);
		
		
	}
}