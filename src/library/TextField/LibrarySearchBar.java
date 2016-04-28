package library.TextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import library.File.LibraryFile;

public class LibrarySearchBar extends LibraryTextField
{
	private static final long serialVersionUID = -481221882331525417L;
	private List<LibraryFile> sortedCatalog = new ArrayList<LibraryFile>();

	public LibrarySearchBar (List<LibraryFile> catalog)
	{
		addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				onActionListener(catalog);
			}
		});
		
		setBounds(4, 6, 592, 28);
		setColumns(10);
	}
	
	protected void onActionListener (List<LibraryFile> catalog)
	{
		onActionListener();
		sortedCatalog.clear();
		for (LibraryFile libraryFile : catalog) 
		{
			for (String tag : libraryFile.tags)
			{
				if (tag.equals(getLastString()))
				{
					sortedCatalog.add(libraryFile);
				}
			}
		}
	}
	
	public List<LibraryFile> getSortedCatalog() 
	{
		return sortedCatalog;
	}
}