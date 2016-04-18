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
import java.awt.TextArea;
import java.awt.TextField;

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
		choiceExample ();
	}
	
	/**************************
	 ********Text Field********
	 **************************/
	private List<LibraryFile> catalog = new ArrayList<>();	
	private void textField ()
	{
		textField = new JTextField();
		textField.addActionListener(new AbstractAction() 
		{
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) 
			{
				for (LibraryFile libraryFile : catalog) 
				{
					for (String libraryFileTags : libraryFile.tags) 
					{
						if (libraryFileTags.compareTo(textField.getText()) == 0)
						{
							System.out.println("I did it! " + libraryFile.name + "tagged with: " + libraryFileTags);
						}
					}
				}
				textField.setText("");
			}
		});
		frame.getContentPane().setLayout(null);
		textField.setBounds(6, 6, 588, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
	
	/**************************
	 ***********Choice*********
	 **************************/
	private void choiceExample () 
	{
		Choice choice = new Choice();
		choice.setBounds(16, 40, 117, 27);
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
		
		/**************************
		 ******Tag Text Field******
		 **************************/
		TextField choiceTextField = new TextField();
		choiceTextField.setBounds(139, 40, 132, 27);
		choiceTextField.addActionListener(new AbstractAction() 
		{
			private static final long serialVersionUID = 2L;
			public void actionPerformed(ActionEvent e) 
			{
				if (choiceTextField.getText().compareTo("") != 0)
				{
					for (LibraryFile libraryFile : catalog) 
					{
						String choiceString = choice.getSelectedItem();
						String libraryFileString = FilenameUtils.getBaseName(libraryFile.file.toString());
						if (choiceString.compareTo(libraryFileString) == 0)
						{
							libraryFile.tags.add(choiceTextField.getText());
							System.out.println(libraryFile.name + ": was tagged with: " + choiceTextField.getText());
							choiceTextField.setText("");
						}
					}
				}
			}
		});

		/**************************
		 *******Add to Frame*******
		 **************************/		
		frame.getContentPane().add(choice);
		frame.getContentPane().add(choiceTextField);
	}
}