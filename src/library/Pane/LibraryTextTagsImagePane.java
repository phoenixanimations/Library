package library.Pane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import library.File.LibraryFile;
import library.TextField.LibrarySearchBar;

public class LibraryTextTagsImagePane extends JSplitPane
{
	private static final long serialVersionUID = 9086829730005962851L;
	public LibraryTextTagsImagePane (List<LibraryFile> catalog)
	{
		LibrarySearchBar search = new LibrarySearchBar();
		JList <String> selectFiles = new JList<String>();
		LibraryImagePane image = new LibraryImagePane();	
		LibraryShowTags tags = new LibraryShowTags();
		search.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				search.onActionListener(catalog);
				String[] names = new String [search.getSortedCatalog().stream().map(c -> c.name).collect(Collectors.toList()).size()];
				search.getSortedCatalog().stream().map(c -> c.name).collect(Collectors.toList()).toArray(names);
				selectFiles.setListData(names);
				selectFiles.clearSelection();
			}
		});
		
		selectFiles.addListSelectionListener(new ListSelectionListener() 
		{	
			@Override
			public void valueChanged(ListSelectionEvent e) 
			{
				if (selectFiles.getSelectedIndex() < 0)
				{
					return;
				}
				LibraryFile selectedLibraryFile = search.getSortedCatalog().get(selectFiles.getSelectedIndex());
				String path = selectedLibraryFile.path;
				tags.show(selectedLibraryFile);
				image.labelImage.setIcon(new ImageIcon(path));
			}
		});
		
		JSplitPane imageTags = new JSplitPane(JSplitPane.VERTICAL_SPLIT,image,tags);
		imageTags.setDividerLocation(340);
		JSplitPane scrollImageTags = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(selectFiles),imageTags);		
		orientation = JSplitPane.VERTICAL_SPLIT;
		setTopComponent(search);
		setBottomComponent(scrollImageTags);
		updateUI();
	}
}