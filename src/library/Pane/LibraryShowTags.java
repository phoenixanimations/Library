package library.Pane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import library.File.LibraryFile;
import system.XML;

public class LibraryShowTags extends JScrollPane
{
	private static final long serialVersionUID = -3942540669852789051L;
	public LibraryFile libraryFile;
	private XML xmlCatalog;
	
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
					xmlCatalog.removeTag(libraryFile.id, tag);
					if (!jFieldTag.getText().equals(""))
					{
						xmlCatalog.addTag(libraryFile.id, jFieldTag.getText());
					}
					jFieldTag.setFocusable(false);
					jFieldTag.setFocusable(true);
				}
			});
			listOfTags.add(jFieldTag);			
		}
		JTextField jFieldNewTag = new JTextField();
		jFieldNewTag.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Boolean duplicateTag = false;
				for (String tag : libraryFile.tags) 
				{
					if (tag.toLowerCase().equals(jFieldNewTag.getText().toLowerCase()))
					{
						duplicateTag = true;
					}
				}
				
				if (!jFieldNewTag.getText().equals("") && !duplicateTag)
				{
					String newTag = jFieldNewTag.getText().toLowerCase();
					newTag = Character.toString(newTag.charAt(0)).toUpperCase() + newTag.substring(1);
					xmlCatalog.addTag(libraryFile.id, newTag);
					show(libraryFile);
				}
				else 
				{
					jFieldNewTag.setText("");
				}
			}
		});
		listOfTags.add(jFieldNewTag);
		setViewportView(listOfTags);
	}
	
	public LibraryShowTags (XML assignXML)
	{
		super();
		xmlCatalog = assignXML;
		setBounds(6, 44, 588, 48);
	}
}