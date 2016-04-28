package library.TextScrollPane;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import library.File.LibraryFile;

public class LibraryTextScrollPane extends JScrollPane
{
	private static final long serialVersionUID = -1356499724186239290L;
	private static JList<String> CreateTextScroll (List<LibraryFile> catalog)
	{
		DefaultListModel<String> fileNames = new DefaultListModel<String>();
		catalog.forEach(c -> fileNames.addElement(c.name));
		JList<String> textScroll = new JList<String>(fileNames);
		return textScroll;
	}
	
	public LibraryTextScrollPane (List<LibraryFile> catalog)
	{
		super(CreateTextScroll(catalog));
		setBounds(20, 189, 300, 179);
	}
}
