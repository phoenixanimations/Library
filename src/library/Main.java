package library;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Main 
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
					Main window = new Main();
					window.frame.setVisible(true);
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
	public Main() 
	{
		initialize();
	}

	/**************************
	 **Initialize Application**
	 **************************/
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		buttonExample ();
	}
	
	/**************************
	 *********Buttons**********
	 **************************/
	private void buttonExample ()
	{
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() 
		{
			/**************************
			 *********On Click*********
			 **************************/
			public void actionPerformed(ActionEvent e)
			{
				try 
				{
					findFileExample();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(6, 6, 135, 29);
		frame.getContentPane().add(btnNewButton);
	}
	
	private void findFileExample () throws IOException
	{
		List<LibraryFile> catalog = new ArrayList<LibraryFile>();
		LibraryFile newLibraryFile = new LibraryFile();
		try
		{
			Files.walk(Paths.get("./img"))
				 .filter(Files::isRegularFile)
				 .filter(e -> e.toString().toLowerCase().contains(".jpeg") || 
					  	   	  e.toString().toLowerCase().contains(".jpg") ||
					  	   	  e.toString().toLowerCase().contains(".png") ||
					  	   	  e.toString().toLowerCase().contains(".tiff"));
//Write out what you want to do with this.
//Find out how many files need to be cataloged and sort each one.
//				 .forEach(e -> newfi);
//				 .forEach(e -> newLibraryFile.file = );
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
//		catalog.forEach(c-> System.out.println(c.file.toString()));
//		fileExtensions.forEach(f -> System.out.println(f));
//		Desktop.getDesktop().open(fileExtensions.get(0));
	}
}