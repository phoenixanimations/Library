package library.TextField;

import java.util.ArrayList;
import java.util.List;

import library.File.LibraryFile;

public class LibrarySearchBar extends LibraryTextField
{
	private static final long serialVersionUID = -481221882331525417L;
	private List<LibraryFile> sortedCatalog = new ArrayList<LibraryFile>();

	public LibrarySearchBar ()
	{	
		setBounds(4, 6, 592, 28);
		setColumns(10);
	}
	
	public void onActionListener (List<LibraryFile> catalog)
	{
		onActionListener();
		sortedCatalog.clear();
		for (LibraryFile libraryFile : catalog) 
		{
			for (String tag : libraryFile.tags)
			{
				if (tag.equals(getLastString()) && !getLastString().equals(""))
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