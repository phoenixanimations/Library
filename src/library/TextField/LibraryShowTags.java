package library.TextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import library.LibraryFile;

public class LibraryShowTags extends JScrollPane
{
	private static final long serialVersionUID = -3942540669852789051L;
	private static JPanel createListOfTags (LibraryFile libraryFile)
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
					libraryFile.tags.remove(tag);
					libraryFile.tags.add(jFieldTag.getText());
					jFieldTag.transferFocus();
				}
			});
			listOfTags.add(jFieldTag);
		}
		return listOfTags;
	}
	
	public LibraryShowTags (LibraryFile libraryFile)
	{
		super(createListOfTags(libraryFile));
		setBounds(6, 44, 588, 48);
	}
}