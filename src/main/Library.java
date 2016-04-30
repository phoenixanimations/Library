package main;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import library.File.LibraryFile;
import library.Pane.LibraryTextTagsImagePane;


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
		frame.setBounds(100, 100, 600, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
//		try
//		{
//			Files.walk(Paths.get("./img"))
//				 .filter(Files::isRegularFile)
//				 .filter(e -> e.toString().toLowerCase().contains(".jpeg") || 
//					  	   	  e.toString().toLowerCase().contains(".jpg") ||
//					  	   	  e.toString().toLowerCase().contains(".png") ||
//					  	   	  e.toString().toLowerCase().contains(".tiff"))
//				 .forEach(e -> catalog.add(new LibraryFile(FilenameUtils.getBaseName(e.toString()), e.toString(), FilenameUtils.getExtension(e.toString()))));
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//		catalog.forEach(t -> t.tags.add("Default")); 
		
		/**************************
		 *******Add to Frame*******
		 **************************/
//		LibraryTextTagsImagePane libraryTextTagsImagePane = new LibraryTextTagsImagePane(catalog);
//		frame.getContentPane().add(libraryTextTagsImagePane);		
	}
}