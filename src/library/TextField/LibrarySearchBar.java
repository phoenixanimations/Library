package library.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import system.Queue;

import library.File.LibraryFile;
import system.XML;

public class LibrarySearchBar extends LibraryTextField
{
	private static final long serialVersionUID = -481221882331525417L;
	private List<LibraryFile> sortedCatalog = new ArrayList<LibraryFile>();

	public LibrarySearchBar ()
	{	
		setBounds(4, 6, 592, 28);
		setColumns(10);
	}
	
	
	public void onActionListener (XML xml, Queue queue)
	{
		sortedCatalog.clear();
		code(xml, queue);
		search(xml.getCatalog());
	}
	
	public void code (XML xml, Queue queue)
	{
		if (getText().contains("[Auto Tag]"))
		{
			String autoTag = getText().split("]")[1];
			xml.getCatalog().forEach(l -> 
			{
				if (l.path.toLowerCase().contains(autoTag.toLowerCase()))
				{
					xml.addTag(l.id, autoTag);
				}
			});
		}
		
		if (getText().contains("[Clear Queue]") || getText().contains("[Clear Que]"))
		{
			queue.clear();
		}
		
		if (getText().contains("[Open Queue]"))
		{
			try 
			{
				queue.openFiles();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	private void search (List<LibraryFile> catalog)
	{
		List <String> seperate = Arrays.asList(getText().split("(?=\\+)|(?=-)"));
		List <String> add = new ArrayList<String>();
		List <String> minus = new ArrayList<String>();
		
		for (String string : seperate)
		{
			if (string.contains("+")) { add.add(string.replace("+", "")); }
			else if (string.contains("-")) { minus.add(string.replace("-", "")); }
			else { add.add(string); }
		}

		catalog.forEach(l -> { l.tags.forEach(t -> { add.forEach(a -> 
		{ 
			if (t.toLowerCase().equals(a.toLowerCase())) 
			{
				sortedCatalog.remove(l);
				sortedCatalog.add(l);
			} 
		});});});

		if (minus.size() > 0)
		{
			catalog.forEach(l -> {l.tags.forEach(t -> { minus.forEach(m -> 
			{
				if (t.toLowerCase().equals(m.toLowerCase()))
				{
					sortedCatalog.remove(l);					
				}
			});});});
		}
	}

	public List<LibraryFile> getSortedCatalog() 
	{
		return sortedCatalog;
	}
}