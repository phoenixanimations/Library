package library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;


import org.apache.commons.io.FilenameUtils;



import javax.swing.AbstractAction;


import javax.swing.JTextField;
import java.awt.Choice;

public class Main 
{
	private JFrame frame;
	private JTextField textField;

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
		frame.setResizable(false);
		frame.setBounds(100, 100, 600, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textField ();
		buttonExample ();
		choiceExample ();
	}
	
	/**************************
	 ********Text Field********
	 **************************/
	private void textField ()
	{
		textField = new JTextField();
		textField.addActionListener(new AbstractAction() 
		{
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("Do search here");
			}
		});
		textField.setBounds(6, 6, 588, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
	
	/**************************
	 *********Buttons**********
	 **************************/
	private void buttonExample ()
	{
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(477, 416, 117, 28);
		btnNewButton.addActionListener(new ActionListener() 
		{
			/**************************
			 *********On Click*********
			 **************************/
			public void actionPerformed(ActionEvent e)
			{
//				try 
//				{
////					findFileExample();
//				} 
//				catch (IOException e1) 
//				{
//					e1.printStackTrace();
//				}
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnNewButton);
		
	}
	
	private void choiceExample () 
	{
		Choice choice = new Choice();
		choice.setBounds(16, 40, 117, 27);
		List<LibraryFile> catalog = new ArrayList<>();
		try
		{
			/**************************
			 ******Catalog Files*******
			 **************************/
			Files.walk(Paths.get("./img"))
				 .filter(Files::isRegularFile)
				 .filter(e -> e.toString().toLowerCase().contains(".jpeg") || 
					  	   	  e.toString().toLowerCase().contains(".jpg") ||
					  	   	  e.toString().toLowerCase().contains(".png") ||
					  	   	  e.toString().toLowerCase().contains(".tiff"))
				 .forEach(e -> catalog.add(new LibraryFile(FilenameUtils.getBaseName(e.toString()), e.toFile())));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catalog.forEach(c -> choice.add(c.name));
		choice.addItemListener(new ItemListener() 
		{
			public void itemStateChanged(ItemEvent e) 
			{
				catalog.forEach(lf -> compareStringToFile(choice.getSelectedItem(), lf.file.toString()));				
			}
		});
		frame.getContentPane().add(choice);		
	}
	
	private void compareStringToFile (String string, String file)
	{
		if (string.compareTo(FilenameUtils.getBaseName(file)) == 0)
		{
			System.out.println(string + " = " + file.toString());
		}
	}
}