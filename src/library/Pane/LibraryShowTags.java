package library.Pane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import library.File.LibraryFile;

public class LibraryShowTags extends JScrollPane
{
	private static final long serialVersionUID = -3942540669852789051L;
	public LibraryFile libraryFile;
		
	public void show (LibraryFile libraryFile)
	{
		JPanel listOfTags = new JPanel(new GridLayout(1, 0));
		for (String tag : libraryFile.tags)
		{
			JTextField jFieldTag = new JTextField(tag);
			jFieldTag.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					System.out.println(libraryFile.name);
					libraryFile.tags.remove(tag);
					libraryFile.tags.add(jFieldTag.getText());
					jFieldTag.setFocusable(false);
					jFieldTag.setFocusable(true);
				}
			});
			listOfTags.add(jFieldTag);
			setViewportView(listOfTags);
		}
	}
	
	public LibraryShowTags ()
	{
		super();
		setBounds(6, 44, 588, 48);
	}
}