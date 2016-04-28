package library.TextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import library.File.LibraryFile;

public class LibraryAddTags extends LibraryTextField 
{
	private static final long serialVersionUID = -3397994432011330467L;
	public LibraryAddTags(LibraryFile libraryFile) 
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				onActionListener(libraryFile);
			}
		});
		setBounds(139, 40, 132, 27);
	}
	
	private void onActionListener(LibraryFile libraryFile)
	{
		Boolean duplicateTag = false;
		for (String tag : libraryFile.tags) 
		{
			if (tag.equals(getText()))
			{
				duplicateTag = true;
			}
		}
		
		if (duplicateTag == false)
		{
			libraryFile.tags.add(getText());
		}
		onActionListener();
	}
}