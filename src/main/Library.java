package main;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import library.Pane.LibraryTextTagsImagePane;
import system.XML;

public class Library 
{
	private JFrame frame = new JFrame();
	private XML xmlLibraryCatalog;
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
					Library library = new Library();
					library.frame.setBounds(100, 100, 600, 472);
					library.frame.setVisible(true);
					library.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		xmlLibraryCatalog = new XML();
		frame.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {}
			
			@Override
			public void windowIconified(WindowEvent e) {}
			
			@Override
			public void windowDeiconified(WindowEvent e) {}
			
			@Override
			public void windowDeactivated(WindowEvent e) {}
			
			@Override
			public void windowClosing(WindowEvent e) 
			{
				xmlLibraryCatalog.saveXML();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {}
			
			@Override
			public void windowActivated(WindowEvent e) {}
		});
		compileLibrary ();
	}
	
	/**************************
	 ******Compile Library*****
	 **************************/
	private void compileLibrary () 
	{
		/**************************
		 *******Add to Frame*******
		 **************************/
		LibraryTextTagsImagePane libraryTextTagsImagePane = new LibraryTextTagsImagePane(xmlLibraryCatalog);
		frame.getContentPane().add(libraryTextTagsImagePane);
	}
}