package library.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;



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
		search(xml);
	}
	
	public void code (XML xml, Queue queue)
	{	
		if (getText().contains("[Auto Tag]"))
		{
			String code = getText().split("]")[1];
			xml.getCatalog().forEach(l -> 
			{
				if (l.path.toLowerCase().contains(code.toLowerCase()))
				{
					xml.addTag(l.id, code);
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
		
		if (getText().contains("[Refresh]") || getText().contains("[R]") || getText().contains("[r]"))
		{
			xml.refresh();
			setText("Done");
		}
		
		if (getText().contains("[Blacklist]") || getText().contains("[Hide]") || getText().contains("[h]"))
		{
			String code = getText().split("]")[1];
			xml.addBlacklistTag(code);
		}
		
		if (getText().contains("[Remove Blacklist]") || getText().contains("[Remove Hide]"))
		{
			String code = getText().split("]")[1];
			xml.removeBlacklistTag(code);
		}
	}
	
	private void search (XML xml)
	{
		String searchTerm = getText() + xml.getBlacklistTag();
		List <String> seperate = Arrays.asList(searchTerm.split("(?=\\+)|(?=-)"));
		List <String> add = new ArrayList<String>();
		List <String> minus = new ArrayList<String>();
		
		for (String string : seperate)
		{
			if (string.contains("+")) { add.add(string.replace("+", "")); }
			else if (string.contains("-")) { minus.add(string.replace("-", "")); }
			else { add.add(string); }
		}

		xml.getCatalog().forEach(l -> { l.tags.forEach(t -> { add.forEach(a -> 
		{ 
			if (t.toLowerCase().equals(a.toLowerCase())) 
			{
				sortedCatalog.remove(l);
				sortedCatalog.add(l);
			} 
		});});});

		if (minus.size() > 0)
		{
			xml.getCatalog().forEach(l -> {l.tags.forEach(t -> { minus.forEach(m -> 
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
		return sortedCatalog.stream().sorted((l1,l2) -> l1.name.compareToIgnoreCase(l2.name)).collect(Collectors.toList());
	}
}