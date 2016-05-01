package main;
import java.awt.EventQueue;
import javax.swing.JFrame;

import library.Pane.LibraryTextTagsImagePane;
import xml.XML;

public class Library 
{
	private JFrame frame;
	
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
		XML xmlLibraryCatalog = new XML();
		/**************************
		 *******Add to Frame*******
		 **************************/
		LibraryTextTagsImagePane libraryTextTagsImagePane = new LibraryTextTagsImagePane(xmlLibraryCatalog);
		frame.getContentPane().add(libraryTextTagsImagePane);		
	}
}