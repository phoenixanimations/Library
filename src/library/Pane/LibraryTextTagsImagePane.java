package library.Pane;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import library.File.LibraryFile;

public class LibraryTextTagsImagePane extends JSplitPane
{
	private static final long serialVersionUID = 9086829730005962851L;
	public LibraryFile selectedLibraryFile = null;
	public LibraryTextTagsImagePane (List<LibraryFile> sortedCatalog)
	{
		selectedLibraryFile = sortedCatalog.get(0);
		LibraryTextScrollPane scroll = new LibraryTextScrollPane(sortedCatalog);
		LibraryImagePane image = new LibraryImagePane();	
		LibraryShowTags tags = new LibraryShowTags();
		scroll.textScroll.addListSelectionListener(new ListSelectionListener() 
		{	
			@Override
			public void valueChanged(ListSelectionEvent e) 
			{
				selectedLibraryFile = sortedCatalog.get(scroll.textScroll.getSelectedIndex());
				String path = selectedLibraryFile.path;
				tags.show(selectedLibraryFile);
				image.labelImage.setIcon(new ImageIcon(path));
			}
		});
		
		JSplitPane imageTags = new JSplitPane(JSplitPane.VERTICAL_SPLIT,image,tags);
		imageTags.setDividerLocation(300);
		setLeftComponent(scroll);
		setRightComponent(imageTags);
		setBounds(6, 94, 588, 350);
	}
}
