package library;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
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

public class Main 
{
	private JFrame frame;
	private JList<String> jListLibraryFiles;
	private LibraryFile currentLibraryFile;
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
		splitTagExample ();
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
	 ********Show Tags********
	 **************************/
//	DefaultListModel<TextField> showCurrentTags = new DefaultListModel<TextField>();
//	catalog.forEach(f -> f.tags.forEach(t -> showCurrentTags.addElement(new TextField(t))));
//	JList<TextField> jListTagsTextField = new JList<TextField>(showCurrentTags);
//	JScrollPane scrollCurrentTags = new JScrollPane(jListTagsTextField);
//	scrollCurrentTags.setBounds(10, 60, 584, -24);
	//Action listener.
	private void showTags()
	{
//		if (currentLibraryFile == null) return;
		
		
	    /*
	      JPanel panel = new JPanel(new GridLayout(0,1));
  JRadioButton myRadio;
  for(int i = 0; i<100; i++){
      myRadio = new JRadioButton("text" + i);
      panel.add(myRadio);
   }
  JScrollPane scrollPane = new JScrollPane(panel);
	    
	     */
		
		
		
	   JPanel listOfTags = new JPanel(new GridLayout(1,0));
	   for (int i = 0; i<100; i++)
	   {
		   JTextField tag = new JTextField("hi");
		   listOfTags.add(tag);
	   }
	    
	    JScrollPane tagScrollPane = new JScrollPane(listOfTags);
	    tagScrollPane.setBounds(10, 39, 584, 47);
	    frame.getContentPane().add(tagScrollPane);	
	    frame.repaint();
	}
	
	/**************************
	 *********Split Tag********
	 **************************/
	//Should be as soon as something is tagged it goes away. Things in the viewbox are either showing a list of untagged items, or the tagged items you want.  
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
		catalog.forEach(f -> defaultListLibraryFile.addElement(f.name));
		
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
							String allTags = "Tagged with: ";
							for (String tags : libraryFile.tags) 
							{
								allTags += tags + ", ";
							}
							System.out.println(allTags);
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
				if (choiceTextField.getText().compareTo("") != 0)
				{
					for (LibraryFile libraryFile : catalog) 
					{
						String splitTagString = jListLibraryFiles.getSelectedValue();
						String libraryFileString = FilenameUtils.getBaseName(libraryFile.file.toString());
						if (splitTagString.compareTo(libraryFileString) == 0)
						{
							libraryFile.tags.add(choiceTextField.getText());
							System.out.println(libraryFile.name + ": was tagged with: " + choiceTextField.getText());
							choiceTextField.setText("");
						}
					}
				}
			}
		});
		
		JScrollPane textScrollPane = new JScrollPane(jListLibraryFiles);
		textScrollPane.setBounds(20, 189, 300, 179);
		
		JScrollPane imageScrollPane = new JScrollPane(previewImage);
		imageScrollPane.setBounds(20, 189, 300, 179);
		
		JSplitPane imageAndTagPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,previewImage,choiceTextField);
		imageAndTagPane.setDividerLocation(333);
		imageAndTagPane.setEnabled(false);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,textScrollPane,imageAndTagPane);
		splitPane.setSize(588, 350);
		splitPane.setLocation(6, 94);
		splitPane.setDividerLocation(260);

		
		/**************************
		 *******Add to Frame*******
		 **************************/
		frame.getContentPane().add(splitPane);
	}
}