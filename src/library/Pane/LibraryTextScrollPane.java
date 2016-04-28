package library.Pane;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import library.File.LibraryFile;

public class LibraryTextScrollPane extends JScrollPane
{
	private static final long serialVersionUID = -1356499724186239290L;
	public JList<String> textScroll = new JList<String>();
	public LibraryTextScrollPane (List<LibraryFile> sortedCatalog)
	{
		super();
		DefaultListModel<String> fileNames = new DefaultListModel<String>();
		sortedCatalog.forEach(c -> fileNames.addElement(c.name));
		textScroll = new JList<String>(fileNames);
		setViewportView(textScroll);
		setBounds(20, 189, 300, 179);
	}
}