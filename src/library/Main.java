package library;
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
	private JScrollPane tagScrollPane = new JScrollPane(); 
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
		frame.getContentPane().add(tagScrollPane);
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
		textField.setBounds(4, 6, 592, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
	/**************************
	 ********Show Tags********
	 **************************/
	private void showTags()
	{
	   JPanel listOfTags = new JPanel(new GridLayout(1,0));
	   for (int i = 0; i < currentLibraryFile.tags.size(); i++)
	   {
		   JTextField tag = new JTextField(currentLibraryFile.tags.get(i));
		   listOfTags.add(tag);
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
		DefaultListModel<String> defaultListLibraryFile = new DefaultListModel<String>(); //change placement.
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
		frame.getContentPane().add(splitPane);
	}
}