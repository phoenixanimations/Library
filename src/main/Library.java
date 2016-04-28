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
import library.*;
import library.TextField.LibrarySearch;


public class Library 
{
	private JFrame frame;
	private JList<String> jListLibraryFiles;
	private LibraryFile currentLibraryFile;
	private JScrollPane tagScrollPane = new JScrollPane(); 
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
		splitTagExample ();
		frame.getContentPane().add(tagScrollPane);
	}
		
	/**************************
	 ********Show Tags********
	 **************************/
	private void showTags()
	{
	   JPanel listOfTags = new JPanel(new GridLayout(1,0));
	   
	   for (String tag : currentLibraryFile.tags) 
	   {
		   JTextField jFieldTag = new JTextField(tag);
		   jFieldTag.addActionListener(new ActionListener() 
		   {
				public void actionPerformed(ActionEvent e) 
				{
					currentLibraryFile.tags.remove(tag);
					currentLibraryFile.tags.add(jFieldTag.getText());
					jFieldTag.setFocusable(false);
				}
		   });
		   listOfTags.add(jFieldTag); 
	   }
	   	frame.remove(tagScrollPane);
	    tagScrollPane = new JScrollPane(listOfTags);
	    frame.getContentPane().add(tagScrollPane);
		tagScrollPane.setBounds(6, 44, 588, 48);
		tagScrollPane.repaint();
		tagScrollPane.revalidate();
	}
	
	/**************************
	 *********Split Tag********
	 **************************/
	private void splitTagExample () 
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
				 .forEach(e -> catalog.add(new LibraryFile(FilenameUtils.getBaseName(e.toString()), e.toFile())));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catalog.forEach(t -> t.tags.add("Default")); 
		
		/**************************
		 ********Split Pane********
		 **************************/
		DefaultListModel<String> defaultListLibraryFile = new DefaultListModel<String>();
		
		JLabel previewImage = new JLabel();
		previewImage.setHorizontalAlignment(JLabel.CENTER);
		
		jListLibraryFiles = new JList<String>(defaultListLibraryFile);
		jListLibraryFiles.addListSelectionListener(new ListSelectionListener() 
		{	
			public void valueChanged(ListSelectionEvent e) 
			{
				for (LibraryFile libraryFile : catalog) 
				{
					if (libraryFile.name.equals(jListLibraryFiles.getSelectedValue()))
					{
						currentLibraryFile = libraryFile;
						showTags();
						
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
				if (currentLibraryFile == null)
				{
					choiceTextField.getText();
					choiceTextField.setText("");
					return;
				}
				if (choiceTextField.getText().equals("") == false)
				{
					Boolean notAlreadyTagged = true;
					for (String tag : currentLibraryFile.tags) 
					{
						if (tag.equals(choiceTextField.getText()))
						{
							notAlreadyTagged = false;
						}
					}
					if (notAlreadyTagged)
					{
						currentLibraryFile.tags.add(choiceTextField.getText());
						choiceTextField.setText("");
						showTags();
					}
				}
			}
		});
	
		/**************************
		 ********Text Field********
		 **************************/
		LibrarySearch librarySearchBar = new LibrarySearch();
		
		/**************************
		 *******Scroll Panes*******
		 **************************/
		JScrollPane textScrollPane = new JScrollPane(jListLibraryFiles);
		textScrollPane.setBounds(20, 189, 300, 179);
		
		JScrollPane imageScrollPane = new JScrollPane(previewImage);
		imageScrollPane.setBounds(20, 189, 300, 179);
		
		JSplitPane imageAndTagPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,previewImage,choiceTextField);
		imageAndTagPane.setDividerLocation(310);
		imageAndTagPane.setEnabled(false);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,textScrollPane,imageAndTagPane);
		splitPane.setSize(588, 350);
		splitPane.setLocation(6, 94);
		splitPane.setDividerLocation(260);
		
		/**************************
		 *******Add to Frame*******
		 **************************/
		frame.getContentPane().add(librarySearchBar);
		frame.getContentPane().add(splitPane);
	}
}