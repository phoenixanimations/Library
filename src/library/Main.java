package library;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import org.apache.commons.io.FilenameUtils;

import com.sun.corba.se.spi.orb.StringPair;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.TextField;
import javax.swing.JSplitPane;

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
		JTextField textField = new JTextField();
		
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
		 ********Split Pane********
		 **************************/
		DefaultListModel<String> defaultListLibraryFile = new DefaultListModel<String>();
		catalog.forEach(f -> defaultListLibraryFile.addElement(f.name));
		
		JLabel previewImage = new JLabel();
		previewImage.setHorizontalAlignment(JLabel.CENTER);
		
		JList<String> jListLibraryFiles = new JList<String>(defaultListLibraryFile);
		jListLibraryFiles.addListSelectionListener(new ListSelectionListener() 
		{	
			public void valueChanged(ListSelectionEvent e) 
			{
				for (LibraryFile libraryFile : catalog) 
				{
					if (libraryFile.name.equals(jListLibraryFiles.getSelectedValue()))
					{
						BufferedImage imageIO = null;
						try
						{
							imageIO = ImageIO.read(libraryFile.file);
						}
						catch (Exception exception)
						{
							exception.printStackTrace();
						}
						ImageIcon renderLibraryFile = new ImageIcon(imageIO);
						previewImage.setIcon(renderLibraryFile);
					}	
				}
			}
		});
		
		JScrollPane textScrollPane = new JScrollPane(jListLibraryFiles);
		textScrollPane.setBounds(20, 189, 300, 179);
		
		JScrollPane imageScrollPane = new JScrollPane(previewImage);
		imageScrollPane.setBounds(20, 189, 300, 179);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,textScrollPane,previewImage);
		splitPane.setSize(588, 376);
		splitPane.setLocation(6, 68);
		splitPane.setDividerLocation(260);
		
		/**************************
		 *******Add to Frame*******
		 **************************/		
//		frame.getContentPane().add(choice);
//		frame.getContentPane().add(choiceTextField);
		frame.getContentPane().add(splitPane);
	}
}